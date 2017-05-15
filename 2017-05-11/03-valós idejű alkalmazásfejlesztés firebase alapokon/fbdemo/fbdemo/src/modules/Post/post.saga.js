import { call, put, takeLatest } from 'redux-saga/effects';
import { feedRef } from '../../saga';

export const MESSAGE_REQUEST = 'MESSAGE_REQUEST';
export const MESSAGE_SENT = 'MESSAGE_SENT';

function* watchMessageRequest() {
  yield takeLatest(MESSAGE_REQUEST, postMessage);
}

export function* postMessage(action) {
  yield call([feedRef, feedRef.push], action.payload);
  yield put({ type: MESSAGE_SENT,  payload: action.payload});
}

export default function* post() {
  yield watchMessageRequest();
}
