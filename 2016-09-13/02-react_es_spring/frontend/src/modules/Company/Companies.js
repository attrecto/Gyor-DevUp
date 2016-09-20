import React, { Component } from 'react';
import { connect } from 'react-redux';

import { fetchCompaniesList } from './CompanyActions';
import CompaniesList from './components/CompaniesList';

class Companies extends Component {
  constructor(props, context) {
    super(props, context);
  }

  componentWillMount() {
    this.props.dispatch(fetchCompaniesList());
  }

  render() {
    return (
      <section>
        <h1>List of companies</h1>
        <CompaniesList companies={this.props.companies} />
      </section>
    )
  }
};

const mapStateToProps = (state) => ({
  companies: state.company.list
})

export default connect(mapStateToProps)(Companies);
