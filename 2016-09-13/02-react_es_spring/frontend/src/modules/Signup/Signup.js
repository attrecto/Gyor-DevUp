import React, { Component, PropTypes } from 'react';
import { connect } from 'react-redux';
import { signup } from './SignupActions';
import SignupForm from './components/SignupForm';

export class SignupContainer extends Component {
  constructor(props, context) {
    super(props, context);
  }

  handleSignup(user) {
    this.props.dispatch(signup(user));
  }

  render() {
    return (
      <SignupForm
        isFetching={this.props.isFetching}
        error={this.props.error}
        handleSignup={(u) => this.handleSignup(u) }
        />
    )
  }
}

SignupContainer.propTypes = {
  dispatch: PropTypes.func.isRequired,
  isFetching: PropTypes.bool.isRequired,
  error: PropTypes.object
}

function mapStateToProps(state) {
  return {
    isFetching: state.signup.isFetching,
    error: state.signup.error
  }
}

export default connect(
  mapStateToProps
)(SignupContainer);
