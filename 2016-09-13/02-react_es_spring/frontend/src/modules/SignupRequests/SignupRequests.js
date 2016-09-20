import React, { Component } from 'react';
import { connect } from 'react-redux';

import { fetchSignupRequests, approve, reject } from './SignupRequestActions';
import RequestList from './components/RequestList';

class SignupRequests extends Component {
  constructor(props, context) {
    super(props, context);

    this.approve = this.approve.bind(this);
    this.reject = this.reject.bind(this);
  }

  componentWillMount() {
    this.props.dispatch(fetchSignupRequests());
  }

  approve(user) {
    this.props.dispatch(approve(user));
  }

  reject(user) {
    this.props.dispatch(reject(user));
  }

  render() {
    return (
      <section>
        <h1>Signup requests</h1>
        <RequestList 
          elements={this.props.elements}
          approve={this.approve}
          reject={this.reject}
        />
      </section>
    )
  }
}

const mapStateToProps = (state) => ({
  elements: state.signupRequests.elements,
});

export default connect(mapStateToProps)(SignupRequests);
