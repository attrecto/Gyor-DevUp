import React, { Component } from 'react';
import PropTypes from 'prop-types';

const UiHeader = () => (
  <h4 className="ui header">
    <i className="write icon"></i>
    <div className="content">
      Write something!
  </div>
  </h4>
);

export default class extends Component {
  static propTypes = {
    onSend: PropTypes.func.isRequired,
  };

  state = { value: '', error: false, done: false };

  handleChange = (e) => {
    this.setState({
      value: e.target.value,
      error: false,
      done: false,
    });
  }

  handleSubmit = () => {
    const { value } = this.state;
    const { onSend } = this.props;
    if (!value.length) {
      return this.error();
    }
    this.done();
    onSend(value);
  }

  error = () => this.setState({ error: true });
  done = () => this.setState({ done: true, value: '' });

  render() {
    const { value, error, done } = this.state;
    const placeholder = 'Write a message or dnd a file';
    return (
      <div className="post ui raised segment">
        <UiHeader />
        <div className="ui form">
          <div className="field">
            <textarea
              rows="3"
              value={value}
              placeholder={placeholder}
              onChange={this.handleChange}>
            </textarea>
          </div>
          <button
            onClick={this.handleSubmit}
            className="ui primary button">
            <i className="icon send"></i>
            Fire Up
        </button>
        </div>
        {error && <span className='error'>You have to write something!</span>}
        {done && <span className='done'>Your message sent!</span>}
      </div>
    );
  }
}
