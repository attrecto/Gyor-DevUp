import React, { Component, PropTypes } from 'react';
import { connect } from 'react-redux';

import Navbar from '../Navbar/Navbar';

import './alert.scss';

const Alert = ({text}) => <div className='alert'>{text}</div>;

export class App extends Component {
  render() {
    const { children, fetchError, authenticated, authorities } = this.props;
    return (
      <div className="wrapper">
        {fetchError && <Alert text='Something goes really wrong...' /> }
        <Navbar 
          authenticated={authenticated}
          authorities={authorities} 
        />
        <section className="container">
            { children }
        </section>
      </div>
    );
  }
}

App.propTypes = {
  children: PropTypes.object.isRequired,
  dispatch: PropTypes.func.isRequired
};

const mapStateToProps = (state) => ({
  authenticated: state.user.authenticated,
  authorities: state.user.authorities,
  fetchError: state.app.fetchError,
});

export default connect(mapStateToProps)(App);
