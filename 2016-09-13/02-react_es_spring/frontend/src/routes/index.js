import React from 'react';
import { Router, Route, IndexRoute } from 'react-router';
import App from '../modules/App/App';
import NotFoundView from '../components/NotFoundView';
import { hashHistory } from 'react-router';

import { Signup, SignupSuccess } from '../modules/Signup';
import { Company, Companies } from '../modules/Company';
import { SignIn, Profile } from '../modules/User';
import { CompanyEditor } from '../modules/CompanyEditor';
import { SignupRequests } from '../modules/SignupRequests';

import { isAuthenticated, logout } from '../util/auth';

const requireAuth = (nextState, replace) => {
  if (!isAuthenticated()) {
    replace({
      pathname: '/signin'
    })
  } 
}

export default () => {
  return (
    <Router history={hashHistory}>
      <Route path="/" component={App}>
        <IndexRoute component={Profile} onEnter={requireAuth} />
        <Route path="signin" component={SignIn} />
        <Route path="signup" component={Signup} />
        <Route path="signup/success" component={SignupSuccess} />
        <Route path="signup/requests" onEnter={requireAuth} component={SignupRequests} />
        <Route path="companies" onEnter={requireAuth} component={Companies} />
        <Route path="mycompany" onEnter={requireAuth} component={CompanyEditor} />
        <Route path="companies/:id/:name" onEnter={requireAuth} component={Company} />
        <Route path="*" component={NotFoundView} />
      </Route>
    </Router>
  );
};
