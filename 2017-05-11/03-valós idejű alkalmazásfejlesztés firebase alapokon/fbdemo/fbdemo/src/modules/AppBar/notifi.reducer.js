import {
  NOTIFICATION_ADDED,
  NOTIFICATION_REMOVED,
} from './notifi.saga';

const defaultState = {
  items: [],
};

export default (state = defaultState, action) => {
  const {type, payload} = action;
  switch(type) {
    case NOTIFICATION_ADDED:
      return {items: [payload, ...state.items]};
    case NOTIFICATION_REMOVED:
      return {items: state.items.filter(n => n.id !== payload.id)};
    default:
      return state;
  }
}
