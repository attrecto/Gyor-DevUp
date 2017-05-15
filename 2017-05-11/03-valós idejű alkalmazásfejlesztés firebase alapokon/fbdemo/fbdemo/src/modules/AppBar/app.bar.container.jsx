import React, { Component } from 'react';
import { connect } from 'react-redux';

import { AppBar } from './components';
import { SIGN_OUT_REQUEST } from '../Auth/auth.saga';
import { NOTIFICATION_READED } from './notifi.saga';

class AppBarContainer extends Component {
  handleOnSignOut = () => {
    this.props.dispatch({
      type: SIGN_OUT_REQUEST,
    });
  }

  handleOnNotificationReaded = (id) => {
    this.props.dispatch({
      type: NOTIFICATION_READED,
      payload: id
    });
  }

  componentDidMount() {
    window.$('.ui.dropdown').dropdown();
  }

  render() {
    return (
      <AppBar
        onNotiReaded={this.handleOnNotificationReaded}
        notifications={this.props.notifications}
        onSignOut={this.handleOnSignOut}
      />
    );
  }
}

const mapStateToProps = (state) => ({
  notifications: state.notifications.items,
})

export default connect(mapStateToProps)(AppBarContainer);
