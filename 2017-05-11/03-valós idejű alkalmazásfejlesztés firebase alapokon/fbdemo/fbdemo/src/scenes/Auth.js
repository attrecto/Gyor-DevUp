import React, { Component } from 'react';
import Auth from '../modules/Auth';

class AuthScene extends Component {
  render() {
    return (
      <div className="App">
        <div className="ui two column centered grid">
          <div className="two column row">
            <div className="column">
              <Auth />
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default AuthScene;
