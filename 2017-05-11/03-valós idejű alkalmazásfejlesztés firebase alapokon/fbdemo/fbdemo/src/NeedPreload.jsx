import React, { Component } from 'react';
import { connect } from 'react-redux';

export default (Wrapped) => {
  class PreloadCmp extends Component {
    render() {
      const { ready, ...rest } = this.props;
      if (ready === true) {
        return <Wrapped {...rest} />
      } else {
        return <div>Loading...</div>
      }
    }
  }

  const mapStateToProps = state => ({
    ready: state.auth.ready
  });

  return connect(mapStateToProps)(PreloadCmp);
}