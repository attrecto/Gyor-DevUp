import React, { Component } from 'react';

export default class SignInForm extends Component {
  constructor(props, context) {
    super(props, context);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleSubmit(e) {
    e.preventDefault();

    const username = this.refs.username.value;
    const password = this.refs.password.value;
    const company = this.refs.company.value || '';

    if (username.length && password.length) {
      this.props.onSignIn({ username, password, company });
    }
  }

  render() {
    return (
      <section>
        <form onSubmit={this.handleSubmit}>
          <fieldset>
            <label htmlFor="email">Email address</label>
            <input type="email" ref="username" placeholder="Email address" />

            <label htmlFor="password">Password</label>
            <input type="password" ref="password" placeholder="Password" />

            <label htmlFor="company">Company</label>
            <input type="text" ref="company" placeholder="Company" />

            <input className="btn btn-primary" type="submit" value="Login" />
          </fieldset>
        </form>
      </section>
    )
  }
}
