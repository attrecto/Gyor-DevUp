jest.mock('../../../saga');

import { all, takeEvery, call } from 'redux-saga/effects';
import createSagaMiddleware from 'redux-saga'
import configureMockStore from 'redux-mock-store';
import { 
  auth,
  GoogleAuthProvider,
  FacebookAuthProvider,
  TwitterAuthProvider,
} from '../../../saga';

import authSaga, {
  authStateChangedChannel,
  authFacebook,
  authGoogle,
  authTwitter,
  USER_SIGN_IN,
  USER_SIGN_OUT,
} from '../auth.saga';

const fakeUser = {
  email: 'test@domain.com',
  photoURL: 'avatar.src'
};

const expectedUser = {
  email: 'test@domain.com',
  avatar: 'avatar.src',
};

describe('Auth Saga', () => {
  it('authStateChangedChannel working', (done) => {
    const chan = authStateChangedChannel();

    chan.take((action) => {
      expect(action).toEqual({
        type: USER_SIGN_IN,
        payload: expectedUser,
      });

      chan.take((action) => {
        expect(action).toEqual({
          type: USER_SIGN_OUT,
        });
        done();
      })
    });

    auth.simulateAuthStateChange(fakeUser);
    auth.simulateAuthStateChange(null);

    chan.close();
  })

  it('authFacebook saga calls facebook auth from firebase', () => {
    const iterator = authFacebook();
    expect(iterator.next().value).toEqual(call(
      auth.signIn, FacebookAuthProvider(),
    ));
  });

  it('authTwitter saga calls twitter auth from firebase', () => {
    const iterator = authTwitter();
    expect(iterator.next().value).toEqual(call(
      auth.signIn, TwitterAuthProvider()
    ));
  });

  it('authGoogle saga calls google auth from firebase', () => {
    const iterator = authGoogle();
    expect(iterator.next().value).toEqual(call(
      auth.signIn, GoogleAuthProvider()
    ));
  });

  describe('Main saga', () => {
    it('Authentication flow working', () => {
      const signInSpy = jest.fn();
      const signOutSpy = jest.fn();

      const testWorker = function* testWorker() {
        yield takeEvery(USER_SIGN_IN, signInSpy);
        yield takeEvery(USER_SIGN_OUT, signOutSpy);
      };

      const testRoot = function* testRoow() {
        yield all([
          testWorker(),
          authSaga(),
        ]);
      }

      const sagaMiddleware = createSagaMiddleware();
      const mockStore = configureMockStore([sagaMiddleware]);
      mockStore({});

      sagaMiddleware.run(testRoot);

      auth.simulateAuthStateChange(fakeUser);
      auth.signOut();

      expect(signInSpy).toBeCalledWith({
        type: USER_SIGN_IN,
        payload: expectedUser,
      });

      expect(signOutSpy).toBeCalledWith({
        type: USER_SIGN_OUT,
      });
    });
  });
});
