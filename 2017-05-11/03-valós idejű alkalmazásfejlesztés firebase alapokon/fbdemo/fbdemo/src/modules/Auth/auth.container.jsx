import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Auth } from './components';

import {
  AUTH_REQUEST_FB,
  AUTH_REQUEST_GL,
  AUTH_REQUEST_TWT,
} from './auth.saga';

class AuthContainer extends Component {
  handleAuthFacebook = () => {
    /*this.props.dispatch({
      type: AUTH_REQUEST_FB,
    });*/
  }

  handleAuthGoogle = () => {
    this.props.dispatch({
      type: AUTH_REQUEST_GL,
    });
  }

  handleAuthTwitter = () => {
   /* this.props.dispatch({
      type: AUTH_REQUEST_TWT,
    });*/
  }
  
  render() {
    return (
      <Auth
        onAuthFacebook={this.handleAuthFacebook}
        onAuthGoogle={this.handleAuthGoogle}
        onAuthTwitter={this.handleAuthTwitter}
        onAuthEmailPass={() => {}}
      />
    );
  }
}

export default connect()(AuthContainer);
