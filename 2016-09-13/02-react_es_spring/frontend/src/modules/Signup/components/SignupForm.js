import React, { PropTypes, Component } from 'react';

class Input extends Component {
  constructor(props) {
    super(props);
  }

  get value() {
    return this.refs.input.value;
  }

  render() {
    const { label, name, type } = this.props;
    return (
      <div>
        <label htmlFor={name}>{label}</label>
        <input id={name} type={type} ref="input"/>
      </div>
    )
  };
}

export default class SignupForm extends Component {
  constructor(props, context) {
    super(props, context);
  }

  onSubmit(event) {
    event.preventDefault();

    const user = {
      email: this.refs.email.value,
      name: this.refs.name.value,
      password: this.refs.password.value,
    };

    const company = {
      identifier: this.refs.companyId.value,
      name: this.refs.companyName.value,
      contact: {
        name: this.refs.contactName.value,
        email: this.refs.contactEmail.value,
        phone: this.refs.contactPhone.value
      }
    };

    const signup = { user, company };
    this.props.handleSignup(signup);
  }

  render() {
    const { error, isFetching } = this.props;
    return (
      <section>
        <form name="signup" onSubmit={(e) => this.onSubmit(e)}>
          <Input label="Email address" name="email" type="email" ref="email" />
          <Input label="User name" name="name" type="text" ref="name" />
          <Input label="Password" name="password" type="password" ref="password" />
          <Input label="Password Confirm" name="password-confirm" type="password" ref="password-confirm" />
          <Input label="Company ID" name="companyId" type="text" ref="companyId" />
          <Input label="Company Name" name="companyName" type="text" ref="companyName" />
          <Input label="Company Contact name" name="contactName" type="text" ref="contactName" />
          <Input label="Company Contact email" name="contactEmail" type="text" ref="contactEmail" />
          <Input label="Company Contact phone" name="contactPhone" type="text" ref="contactPhone" />
          <input type="submit" value="Submit registartion" />
        </form>
      </section>
    )
  }
};

SignupForm.propTypes = {
  error: PropTypes.object,
  isFetching: PropTypes.bool.isRequired,
  handleSignup: PropTypes.func.isRequired
}
