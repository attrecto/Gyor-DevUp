import { push } from 'react-router-redux';

export const SIGN_UP_REQUEST = 'SIGN_UP_REQUEST';
export const SIGN_UP_FAIL = 'SIGN_UP_FAIL';
export const SIGN_UP_SUCCESS = 'SIGN_UP_SUCCESS';
export const SIGN_UP = 'SIGN_UP';

const baseUrl = '/api';

export function signUpRequest() {
  return { type: SIGN_UP_REQUEST };
};

export function signUpFail(err) {
  return { type: SIGN_UP_FAIL, err };
};

export function signUpSuccess(user) {
  return { type: SIGN_UP_SUCCESS, user };
};

export function signup(user) {
  return (dispatch) => {
    dispatch(signUpRequest());
    fetch(`${baseUrl}/signup`, {
      method: 'POST',
      body: JSON.stringify(user),
      headers: new Headers({
        'Content-Type': 'application/json'
      })
    })
    .then(res => {
      if (!res.ok) {
        return res.json().then(body => {
          const fieldErrors = body.fieldErrors;
          dispatch(signUpFail(fieldErrors));
          return Promise.reject(body);
        })
      }
      dispatch(signUpSuccess(user));
      dispatch(push('/signup/success'));
    })
    .catch(err => {
      console.log(err);
    })
  };
}
