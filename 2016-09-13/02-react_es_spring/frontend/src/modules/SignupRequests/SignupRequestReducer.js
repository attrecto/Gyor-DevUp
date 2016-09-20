import { SIGNUP_REQUESTS, APPROVED, REJECTED } from './SignupRequestActions';

const defaultState = {
  elements: []
};

export default function signupRequests(state = defaultState, action) {
  switch(action.type) {
    case SIGNUP_REQUESTS:
      return { elements: action.body.elements };
    case APPROVED:
    case REJECTED:
      const id = action.payload.id;
      return { elements: state.elements.filter(e => e.user.id !== id) }
    default:
      return state;
  }
}
