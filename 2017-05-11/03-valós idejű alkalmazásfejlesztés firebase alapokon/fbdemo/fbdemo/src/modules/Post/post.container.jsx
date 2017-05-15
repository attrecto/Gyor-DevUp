import React, { Component } from 'react';
import { connect } from 'react-redux';
import moment from 'moment';

import { Post } from './components';
import { MESSAGE_REQUEST } from './post.saga';

class PostContainer extends Component {
  handleSend = (text) => {
    // Demo payload before auth
    const created = moment().toISOString();
    const { user } = this.props;
    const payload = { user, text, created };
    this.props.dispatch({ type: MESSAGE_REQUEST, payload });
  }

  render() {
    return <Post onSend={this.handleSend} />;
  }
}

const mapStateToProps = state => ({
  sent: state.post.sent,
  user: state.auth.user,
});

export default connect(mapStateToProps)(PostContainer);
