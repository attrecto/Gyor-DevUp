import {
  MESSAGE_SENT,
} from './post.saga';

const defaultState = { sent: false };

export default (state = defaultState, action) => {
  const { type } = action;
  switch(type) {
    case MESSAGE_SENT:
      return { sent: true };
    default:
      return state;
  }
}
