import React, { Component } from 'react';
import { connect } from 'react-redux';
import { logout } from './UserActions';

class Logout extends Component {
  constructor(props, context) {
    super(props, context);

    this.logout = this.logout.bind(this);
  }

  logout() {
    this.props.dispatch(logout());
  }

  render() {
    return (
      <a href="javascript: void(0);" onClick={this.logout}>Logout</a>
    )
  }
}

export default connect()(Logout);
