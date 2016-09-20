import { combineReducers } from 'redux';

import app from '../modules/App/AppReducer';
import signup from '../modules/Signup/SignupReducer';
import user from '../modules/User/UserReducer';
import company from '../modules/Company/CompanyReducer';
import editor from '../modules/CompanyEditor/CompanyEditorReducer';
import signupRequests from '../modules/SignupRequests/SignupRequestReducer';

/* Added routeReducer from react-router-redux */
/* This captures routes as state */
import { routeReducer as routing } from 'react-router-redux';

import { reducer as form } from 'redux-form';

/**
 * combineReducers is important to understand. As your app might grow in size
 * and complexity, you will likely begin to split your reducers into separate
 * functions - with each one managing a separate slice of the state! This helper
 * function from 'redux' simply merges the reducers.
 *
 * If you're transitioning from Flux, you will notice we only use one store, but
 * instead of relying on multiple stores to manage diff parts of the state, we use
 * various reducers and combine them.
 *
 * More info: http://rackt.org/redux/docs/api/combineReducers.html
 */

const rootReducer = combineReducers({
  app,
  user,
  signup,
  company,
  routing,
  form,
  editor,
  signupRequests,
});

export default rootReducer;
