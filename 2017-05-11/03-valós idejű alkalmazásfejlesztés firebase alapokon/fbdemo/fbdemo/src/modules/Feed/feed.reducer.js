import {
  FEED_CHILD_ADDED,
  FEED_CHILD_CHANGED,
  FEED_CHILD_REMOVED,
} from './feed.saga';

const defaultState = {
  items: [],
};

const items = (state = [], action) => {
  const { type, payload } = action;
  return state.map((item) => {
    if (item.id !== payload.id) {
      return item;
    }

    switch (type) {
      case FEED_CHILD_CHANGED:
        return { ...item, ...payload };
      default:
        return item;
    }
  });
}

const remove = (state = [], id) =>
  state.filter(item => item.id !== id);

export default (state = defaultState, action) => {
  const { type, payload } = action;
  switch (type) {
    case FEED_CHILD_ADDED:
      return { ...state, items: [payload, ...state.items] };
    case FEED_CHILD_REMOVED:
      return { ...state, items: remove(state.items, payload.id) };
    case FEED_CHILD_CHANGED:
      return { ...state, items: items(state.items, action) };
    default:
      return state;
  }
}
