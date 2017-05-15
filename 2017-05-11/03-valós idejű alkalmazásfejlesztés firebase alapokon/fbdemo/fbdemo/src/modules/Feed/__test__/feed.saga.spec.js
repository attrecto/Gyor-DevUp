jest.mock('../../../saga');

import { all, takeEvery } from 'redux-saga/effects';
import createSagaMiddleware from 'redux-saga'
import  configureMockStore from 'redux-mock-store';
import moment from 'moment';
import { feedRef } from '../../../saga';

import feed, { 
  feedChildAddedChannel,
  feedChildChangedChannel,
  feedChildRemovedChannel, 
  FEED_CHILD_ADDED,
  FEED_CHILD_CHANGED,
  FEED_CHILD_REMOVED,
} from '../feed.saga';

const fakeTime = moment().toISOString();

const itemFb = {
  user: {
    email: 'test@domain.com',
    avatar: 'avatar src',
  },
  text: 'Something',
  created: fakeTime,
};

const itemExpected = {
  user: {
    email: 'test@domain.com',
    avatar: 'avatar src',
  },
  text: 'Something',
  created: fakeTime,
  id: 'itemid',
};

describe('feed saga', () => {
  describe('child_changed', () => {
    it('feed changed channel working', (done) => {
      const chan = feedChildChangedChannel();
      
      chan.take((item) => {
        expect(item).toEqual(itemExpected);
        done();
      });

      feedRef.childChanged(itemFb);
      chan.close();
    })
  })

  describe('child_removed', () => {
    it('feed removed channel working', (done) => {
      const chan = feedChildRemovedChannel();

      chan.take((item) => {
        expect(item).toEqual({ id: 'itemid' });
        done();
      })

      feedRef.childRemoved('itemid');
      chan.close();
    })
  })

  describe('child_added', () => {
    it('feed child added channel working', (done) => {
      const chan = feedChildAddedChannel();

      chan.take((item) => {
        expect(item).toEqual(itemExpected);
        done();
      });

      feedRef.childAdded(itemFb);
      chan.close();
    });
  });

  describe('main saga', () => {
    const addedSpy = jest.fn();
    const changedSpy = jest.fn();
    const removedSpy = jest.fn();

    const testWorker = function* testWorker() {
      yield takeEvery(FEED_CHILD_ADDED, addedSpy);
      yield takeEvery(FEED_CHILD_CHANGED, changedSpy);
      yield takeEvery(FEED_CHILD_REMOVED, removedSpy);
    }

    const testRoot = function *testRoot() {
      yield all([
        testWorker(),
        feed(),
      ]);
    }

    const sagaMiddleware = createSagaMiddleware();
    const mockStore = configureMockStore([sagaMiddleware]);
    mockStore({});

    sagaMiddleware.run(testRoot);

    feedRef.childAdded(itemFb);
    feedRef.childChanged(itemFb);
    feedRef.childRemoved('itemid');

    expect(addedSpy).toBeCalledWith({
      type: FEED_CHILD_ADDED,
      payload: itemExpected,
    });
    
    expect(changedSpy).toBeCalledWith({
      type: FEED_CHILD_CHANGED,
      payload: itemExpected,
    });
    
    expect(removedSpy).toBeCalledWith({
      type: FEED_CHILD_REMOVED,
      payload: { id: 'itemid' },
    });
  });
});
