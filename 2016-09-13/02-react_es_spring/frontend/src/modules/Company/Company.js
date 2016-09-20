import React, { Component } from 'react';
import { connect } from 'react-redux';

import { fetchWorkers, reserveWorker} from './CompanyActions';
import { fetchMe } from '../User/UserActions';
import CompanyWorker from './components/CompanyWorker';

class Company extends Component {
  constructor(props, context) {
    super(props, context);
    this.reserveWorker = this.reserveWorker.bind(this);
  }

  componentWillMount() {
    const companyId = this.props.params.id;
    this.props.dispatch(fetchWorkers(companyId));
    if (!this.props.bookingTo) {
      this.props.dispatch(fetchMe());
    }
  }

  reserveWorker(reservation) {
    const bookingTo = this.props.bookingTo;
    this.props.dispatch(reserveWorker(reservation, bookingTo));
  }

  render() {
    const workers = this.props.workers;
    const name = this.props.params.name;

    const createWorker = (worker) => <CompanyWorker
      key={worker.id}
      worker={worker}
      own={false}
      onReserve={this.reserveWorker}
      />;

    return (
      <section>
        <h2>Workers of {name}</h2>
        <table>
          <thead>
            <tr>
              <th>Name</th>
              <th>Biography</th>
              <th>Reservations</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {workers && workers.map(createWorker)}
          </tbody>
        </table>
      </section>
    )
  }
}

const mapStateToProps = (state) => ({
  workers: state.company.workers,
  bookingTo: state.user.company,
});

export default connect(mapStateToProps)(Company);
