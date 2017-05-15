import React, { Component } from 'react';
import PropTypes from 'prop-types';

import './Auth.css';

class Auth extends Component {
  static propTypes = {
    onAuthFacebook: PropTypes.func.isRequired,
    onAuthGoogle: PropTypes.func.isRequired,
    onAuthTwitter: PropTypes.func.isRequired,
    onAuthEmailPass: PropTypes.func.isRequired,
  };

  render() {
    const {
      onAuthFacebook,
      onAuthGoogle,
      onAuthTwitter
    } = this.props;

    return (
      <div className="ui segment auth">
        <h4 className="ui header">
          <i className="sign in icon"></i>
          <div className="content">
            Sign In!
          </div>
        </h4>
        <button className="ui fluid facebook button" onClick={onAuthFacebook}>
          <i className="facebook icon"></i>
          Facebook
        </button>
        <button className="ui fluid google button" onClick={onAuthGoogle}>
          <i className="google icon"></i>
          Google
        </button>
        <button className="ui fluid twitter button" onClick={onAuthTwitter}>
          <i className="twitter icon"></i>
          Twitter
        </button>
      </div>
    );
  }
}

export default Auth;
