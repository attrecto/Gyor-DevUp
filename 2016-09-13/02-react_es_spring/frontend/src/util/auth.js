const decodeToken = require('jwt-decode');

export function isAuthenticated() {
  return !!localStorage.token;
}

export function storeToken(token) {
  localStorage.token = token;
  return decodeToken(token);
}

export function destroyToken() {
  if (isAuthenticated()) {
    delete localStorage.token;
  }
}

export function getToken() {
  if (isAuthenticated()) {
    return localStorage.token;
  }
}

export function getTokenPayload() {
  if (isAuthenticated()) {
    return decodeToken(localStorage.token);
  }
}

const BASE_URL = '/api';

function callApi(endpoint, config) {
  const token = getToken();
  if (!token) {
    throw 'No token!';
  }

  const authHeader = new Headers({ 
    ...config.headers,
    'Authorization': `Bearer ${token}`,
    'Content-Type': 'application/json'
  });

  config.headers = authHeader;

  return fetch(`${BASE_URL}${endpoint}`, config)
    .then(response => {
      const status = response.status;
      const statusText = response.statusText;

      if (!response.ok) {
        return response.json().then(
          body => Promise.reject(body),
          () => Promise.reject({
            message: statusText,
            statusText,
            status
          })
        );
      }

      return response.text()
        .then(text => ({ text, status }));
    })
    .then(({ text, status }) => {
      if (text.length) {
        return JSON.parse(text);
      }

      return status;
    });
};

export const FETCH_AUTH = Symbol('Fetch Auth');

export default store => next => action => {
  const fetchAuth = action[FETCH_AUTH];

  if (!fetchAuth) {
    return next(action);
  }

  const { endpoint, config, types, payload } = fetchAuth;
  const { request, success, fail } = types;

  if (typeof request === 'function') {
    const act = request();
    next(act);
  } else {
    next({ type: request, endpoint });
  }

  return callApi(endpoint, config).then(
    (body) => {
      if (typeof success === 'function') {
        const act = success(body);
        return next(act);
      }

      const act = { type: success, body };

      if (payload) {
        act.payload = payload;
      }

      next(act);
    },
    (err) => {
      if (typeof fail === 'function') {
        const act = fail(err);
        return next(act);
      }

      next({
        type: fail,
        error: err,
        message: err.message || 'Error happend',
      })
    }
  );
};
