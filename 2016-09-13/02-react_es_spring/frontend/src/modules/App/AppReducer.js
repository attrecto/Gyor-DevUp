import { FETCH_START, FETCH_SUCCESS, FETCH_ERROR } from './AppActions';

const defaultState = {
  isFetching: false,
  fetchError: null,
};

export default function app(state = defaultState, action) {
  switch (action.type) {
    case FETCH_START:
      return { isFetching: true, fetchError: null };
    case FETCH_SUCCESS:
      return { isFetching: false, fetchError: null };
    case FETCH_ERROR:
      return { isFetching: false, fetchError: action.error };
    default:
      return state;
  }
}