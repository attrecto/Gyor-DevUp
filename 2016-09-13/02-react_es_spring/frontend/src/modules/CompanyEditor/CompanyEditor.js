import React, { Component, PropTypes } from 'react';
import { connect } from 'react-redux';
import { reset } from 'redux-form';

import CompanyInfo from '../Company/components/CompanyInfo';
import CompanyWorkers from './components/CompanyWorkers';
import WorkerForm from './WorkerForm';

import {
  addWorker,
  updateWorker,
  deleteWorker,
  startEdit,
  cancelEdit,
  fetchMyWorkers,
  approveReservation,
  rejectReservation,
} from './CompanyEditorActions';

import {
  fetchMe
} from '../User/UserActions';

class CompanyEditor extends Component {
  constructor(props, context) {
    super(props, context);

    this.saveWorker = this.saveWorker.bind(this);
    this.deleteWorker = this.deleteWorker.bind(this);
    this.startEditWorker = this.startEditWorker.bind(this);
    this.cancelEdit = this.cancelEdit.bind(this);
    this.rejectReservation = this.rejectReservation.bind(this);
    this.approveReservation = this.approveReservation.bind(this);
  }

  componentWillMount() {
    this.props.dispatch(fetchMe());
    this.props.dispatch(fetchMyWorkers());
  }

  cancelEdit() {
    this.props.dispatch(cancelEdit());
  }

  startEditWorker(worker) {
    this.props.dispatch(startEdit(worker));
  }

  saveWorker(worker) {    
    const { company, edit, isEdit } = this.props;
    if (isEdit === true) {
      this.props.dispatch(updateWorker(company.id, edit.id, worker));  
    } else {
      this.props.dispatch(addWorker(company.id, worker));
    }
    this.props.dispatch(cancelEdit());
  }

  deleteWorker(worker) {
    const { company } = this.props;
    if (window.confirm('Are you sure?')) {
      this.props.dispatch(deleteWorker(company.id, worker.id));
    }
  }

  approveReservation(id, workerId) {
    this.props.dispatch(approveReservation(id, workerId))
  }

  rejectReservation(id, workerId) {
    this.props.dispatch(rejectReservation(id, workerId));
  }

  render() {
    const { company, workers } = this.props;

    return (
      <section>
        <CompanyInfo company={company} />
        <CompanyWorkers
          workers={workers} 
          company={company}
          onEdit={this.startEditWorker}
          onDelete={this.deleteWorker}
          onApprove={this.approveReservation}
          onReject={this.rejectReservation}
        />
        <WorkerForm 
          onSave={this.saveWorker}
          onCancel={this.cancelEdit}
        />
      </section>
    )
  }
}

const mapStateToProps = (state) => ({
  isEdit: state.editor.isEdit,
  edit: state.editor.edit,
  workers: state.editor.workers,
  company: state.user.company,
});

export default connect(mapStateToProps)(CompanyEditor);
