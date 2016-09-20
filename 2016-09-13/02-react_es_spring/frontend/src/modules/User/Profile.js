import React, { Component } from 'react';
import { connect } from 'react-redux';

import { fetchMe } from './UserActions';
import Company from './components/Company';

class Profile extends Component {
  constructor(props, context) {
    super(props, context);
  }

  componentWillMount() {
    if (!this.props.company) {
      this.props.dispatch(fetchMe());
    }
  }

  render() {
    const { name, email, company } = this.props;
    return (
      <section>
        <h1>{name}</h1>
        <h2>{email}</h2>
        {company && <Company company={company} />}
      </section>
    )
  }
}

const mapStateToProps = (state) => ({
  name: state.user.name,
  email: state.user.email,
  company: state.user.company,
});

export default connect(mapStateToProps)(Profile);
