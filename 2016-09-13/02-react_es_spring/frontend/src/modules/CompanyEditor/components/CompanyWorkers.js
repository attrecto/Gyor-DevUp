import React, { Component } from 'react';

import CompanyWorker from '../../Company/components/CompanyWorker';

export default class CompanyWorkers extends Component {
  constructor(props, context) {
    super(props, context);
  }

  render() {
    const { workers, onEdit, onDelete, onApprove, onReject } = this.props;

    const createWorker = worker => <CompanyWorker
      key={worker.id}
      worker={worker}
      onEdit={onEdit}
      onDelete={onDelete}
      onApprove={onApprove}
      onReject={onReject}
      own={true}
    />;

    return (
      <section>
        <h2>Workers</h2>
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
            {(workers) ? workers.map(createWorker) : null}
          </tbody>
        </table>
      </section>
    )
  }
};
