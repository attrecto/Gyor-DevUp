import { all } from 'redux-saga/effects';

import * as firebase from 'firebase';
import config from './firebase.config'
firebase.initializeApp(config);

// References that the application will use
const database = firebase.database();
export const feedRef = database.ref('/feed');
export const notiRef = database.ref('/notif');
export const auth = firebase.auth();

export const GoogleAuthProvider = () => {
  return new firebase.auth.GoogleAuthProvider();
}

export const FacebookAuthProvider = () => {
  return new firebase.auth.FacebookAuthProvider();
}

export const TwitterAuthProvider = () => {
  return new firebase.auth.TwitterAuthProvider();
}

auth.signIn = (provider) => auth.signInWithPopup(provider);

feedRef.sendMessage = (message) => feedRef.push(message);

feedRef.likeItem = (id, userId) => feedRef.child(id).child('likes').push(userId);

notiRef.notifyUser = (userId, noti) => notiRef.child(userId).push(noti);

notiRef.removeNoti = (userId, notiId) => notiRef.child(userId).child(notiId).remove();

// Saga-s all around application
import feedSaga from './modules/Feed/feed.saga';
import postSaga from './modules/Post/post.saga';
import authSaga from './modules/Auth/auth.saga';
import notifSaga from './modules/AppBar/notifi.saga';

export default function* root() {
  yield all([
    feedSaga(),
    postSaga(),
    authSaga(),
    notifSaga(),
  ]);
}
