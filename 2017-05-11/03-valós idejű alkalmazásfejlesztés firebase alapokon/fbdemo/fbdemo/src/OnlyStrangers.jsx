import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect, Route } from 'react-router-dom';

class OnlyStrangers extends Component {
  render() {
    const { component: Component, signed, ...rest } = this.props;
    return (
      <Route {...rest} render={props => (
        signed === false ? (
          <Component {...props} />
        ) : (
            <Redirect to={{
              pathname: '/',
              state: { from: props.location }
            }} />
          )
      )} />)
  }
}

const mapStateToProps = state => ({
  signed: state.auth.signed
});

export default connect(mapStateToProps)(OnlyStrangers);
