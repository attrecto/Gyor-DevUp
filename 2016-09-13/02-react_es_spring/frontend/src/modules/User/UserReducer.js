import {
  SIGN_IN_REQUEST,
  SIGN_IN_SUCCESS,
  SIGN_IN_FAIL,
  ME_FETCHED,
  MY_WORKERS,
  LOGOUT,
} from './UserActions';

const defaultState = {
  authenticated: false,
  signInError: false,
  authorities: [],
  company: null,
  companyId: null,
  name: '',
};

export default function user(state = defaultState, action) {
  switch (action.type) {
    case SIGN_IN_REQUEST:
      return { ...state, isFetching: true };
    case SIGN_IN_FAIL:
      return { ...state, isFetching: false, signInError: true };
    case SIGN_IN_SUCCESS:
      return { 
        ...state,
        isFetching: false,
        authenticated: true,
        signInError: false,
        companyId: action.payload.company_id,
        name: action.payload.name,
        authorities: action.payload.authorities,
      };
    case LOGOUT:
      return {
        ...state,
        ...defaultState,
      };
    case ME_FETCHED:
      const company = action.body.companies[0];
      const email = action.body.user.email;
      return {
        ...state,
        company,
        email,
      }
    default:
      return state;
  }
}
