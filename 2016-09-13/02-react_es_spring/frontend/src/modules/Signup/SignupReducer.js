import { SIGN_UP_REQUEST, SIGN_UP_FAIL, SIGN_UP_SUCCESS } from './SignupActions';

const defaultState = {
    user: null,
    err: null,
    isFetching: false,
    success: false,
};
 
export default function signup(state = defaultState, action) {
    switch(action.type) {
        case SIGN_UP_REQUEST: 
            return { ...state, isFetching: true };
        case SIGN_UP_FAIL: 
            return { ...state, isFetching: false, err: action.err };
        case SIGN_UP_SUCCESS:
            return { ...state, isFetching: false, success: true };
        default:
            return state;
    }
}