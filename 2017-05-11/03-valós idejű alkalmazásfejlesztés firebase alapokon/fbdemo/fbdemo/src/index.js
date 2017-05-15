import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import { BrowserRouter as Router } from 'react-router-dom';

import store from './store';

import PrivateRoute from './PrivateRoute';
import OnlyStrangers from './OnlyStrangers';
import NeedPreload from './NeedPreload';

import App from './scenes/App';
import Auth from './scenes/Auth';

const Routes = NeedPreload(() => (
  <Router forceRefresh={true}>
    <div>
      <PrivateRoute exact path="/" component={App} />
      <OnlyStrangers path="/auth" component={Auth} />
    </div>
  </Router>
));

ReactDOM.render(
  <Provider store={store}>
    <Routes />
  </Provider>,
  document.getElementById('root')
);
