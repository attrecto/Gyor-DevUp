import React from 'react';
import ReactDOM from 'react-dom';
import polyfill from 'babel-polyfill';
import { AppContainer } from 'react-hot-loader';

import 'milligram/dist/milligram.min.css';

/**
 * Import the stylesheet you want used! Here we just reference
 * the main SCSS file we have in the styles directory.
 */
import './styles/main.scss';

/**
 * Both configureStore and Root are required conditionally.
 * See configureStore.js and Root.js for more details.
 */
import { configureStore } from './store/configureStore';
import { Root } from './containers/Root';
import { getTokenPayload } from './util/auth';

const appDefaultState = {}

const payload = getTokenPayload();
if (payload) {
  appDefaultState.user = {
    authenticated: true,
    name: payload.name,
    authorities: payload.authorities,
    signInError: false,
    company: null,
    companyId: payload.company_id,
  }
} else {
  appDefaultState.user = {
    authenticated: false,
    signInError: false,
    authorities: [],
    company: null,
    name: '',
  }
}

const store = configureStore(appDefaultState);

renderWithHotReload(Root);

// Hot Module Replacement API
if (module.hot) {
  module.hot.accept('./containers/Root', () => {
    const RootElement = require('./containers/Root').Root;
    renderWithHotReload(RootElement);
  });
}

function renderWithHotReload(RootElement) {
  ReactDOM.render(
    <AppContainer>
      <RootElement store={store} />
    </AppContainer>,
    document.getElementById('root')
  );
}
