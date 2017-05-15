import { call, put } from 'redux-saga/effects';
import moment from 'moment';

import { feedRef } from '../../../saga';

import {
  MESSAGE_SENT,
  MESSAGE_REQUEST,
  postMessage
} from '../post.saga';

const fakeTime = moment().toISOString();
const itemFb = {
  user: {
    email: 'test@domain.com',
    avatar: 'avatar src',
  },
  text: 'Something',
  created: fakeTime,
};

const action = {
  type: MESSAGE_REQUEST,
  payload: itemFb,
};

describe('Post Saga', () => {
  let generator;

  beforeEach(() => {
    generator = postMessage(action);
  })

  describe('postMessage saga', () => {
    it('calls firebase api for sending message', () => {
      expect(generator.next().value).toEqual(
        call([feedRef, feedRef.push], itemFb)
      );

      expect(generator.next().value).toEqual(
        put({ type: MESSAGE_SENT, payload: itemFb })
      );
    })
  });
});
