export const FETCH_ERROR = "FETCH_ERROR";
export const FETCH_START = "FETCH_START";
export const FETCH_SUCCESS = "FETCH_SUCCESS";

import { push } from 'react-router-redux';
import { logout } from '../User/UserActions';

const UNAUTHORIZED = 401;

export function fetchStart() {
  return { type: FETCH_START };
};

export function fetchSuccess() {
  return { type: FETCH_SUCCESS };
};

export function fetchError(err) {
  return (dispatch) => {
    dispatch({ type: FETCH_ERROR, err });
    if (err.status && err.status === UNAUTHORIZED) {
      dispatch(logout());
    }
  }
};