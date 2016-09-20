import React, { Component } from 'react';
import { Field, reduxForm } from 'redux-form';
import { connect } from 'react-redux';

class WorkerForm extends Component {
  render() {
    const { handleSubmit, onSave, editing } = this.props;
    return (
      <section>
        {(editing) ? <h2>Edit worker</h2> : <h2>Create worker</h2>}
        <form onSubmit={handleSubmit(onSave)}>
          <fieldset>
            <label htmlFor="name">Name</label>
            <Field name="name" component="input" type="text" placeholder="Worker name" />

            <label htmlFor="Bio">Bio</label>
            <Field name="biography" component="textarea" />
          </fieldset>
          <fieldset>
            <input className="button-primary" type="submit" value="Save" />
            {editing && 
              <input className="button-error" type="button" value="Cancel" onClick={this.props.onCancel} />}
          </fieldset>
        </form>
      </section>
    )
  }
}

WorkerForm = reduxForm({
  form: 'workerForm'
})(WorkerForm);

WorkerForm = connect(
  state => ({
    initialValues: state.editor.edit,
    editing: state.editor.isEdit
  })
)(WorkerForm);

export default WorkerForm;
