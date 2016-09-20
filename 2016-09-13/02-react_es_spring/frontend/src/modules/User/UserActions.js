import {
  FETCH_START,
  FETCH_SUCCESS,
  fetchError,
} from '../App/AppActions';

import { push } from 'react-router-redux';
import { storeToken, destroyToken, FETCH_AUTH } from '../../util/auth';

export const SIGN_IN_REQUEST = 'SIGN_IN_REQUEST';
export const SIGN_IN_SUCCESS = 'SIGN_IN_SUCCESS';
export const SIGN_IN_FAIL = 'SIGN_IN_FAIL';
export const ME_FETCHED = 'ME_FETCHED';
export const LOGOUT = 'LOGOUT';

const baseUrl = '/api';

function signInRequest() {
  return { type: SIGN_IN_REQUEST };
}

function signInFail() {
  return { type: SIGN_IN_FAIL };
}

function signInSuccess(payload) {
  return { type: SIGN_IN_SUCCESS, payload };
}

export function logout() {
  destroyToken();
  return (dispatch) => {
    dispatch({ type: LOGOUT });
    dispatch(push('/signin'));
  };
}

export function fetchMe() {
  return {
    [FETCH_AUTH]: {
      endpoint: '/users/me',
      config: {
        method: 'GET',
      },
      types: {
        request: FETCH_START,
        success: ME_FETCHED,
        fail: fetchError
      }
    }
  }
}

export function signIn(credentials) {
  return (dispatch) => {
    dispatch(signInRequest());
    return fetch(`${baseUrl}/login`, {
      method: 'POST',
      body: JSON.stringify(credentials),
      headers: new Headers({
        'Content-Type': 'application/json'
      })
    }).then(res => {
      if (!res.ok) {
        dispatch(signInFail());
        return Promise.reject();
      } else {
        return res.json();
      }
    }).then(body => {
      const payload = storeToken(body.token);
      dispatch(signInSuccess(payload));
      dispatch(push('/'));
    }).catch((err) => {
      console.log(err);
      console.log('login error');
    })
  }
}
