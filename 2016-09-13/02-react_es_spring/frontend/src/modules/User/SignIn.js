import React, { Component } from 'react';
import { connect } from 'react-redux';

import { signIn } from './UserActions';
import SignInForm from './components/SignInForm';

class SignIn extends Component {
  constructor(props, context) {
    super(props, context);
    this.signIn = this.signIn.bind(this);
  }

  signIn(credentials) {
    console.log(credentials);
    this.props.dispatch(signIn(credentials));
  }

  render() {
    const error = (this.props.signInError) ? <div className="error">Sign In Fail</div> : null;

    return (
      <section>
        {error}
        <SignInForm onSignIn={this.signIn} />
      </section>
    )
  }
}

const mapStateToProps = (state) => {
  const { isFetching, authenticated, signInError } = state.user;

  return {
    isFetching,
    authenticated,
    signInError
  };
};

export default connect(mapStateToProps)(SignIn);
