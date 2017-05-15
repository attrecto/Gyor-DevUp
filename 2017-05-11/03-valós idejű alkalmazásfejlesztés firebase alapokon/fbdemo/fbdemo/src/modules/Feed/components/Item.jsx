import React from 'react';
import PropTypes from 'prop-types';
import Avatar from './Avatar';
import Message from './Message';

const Item = (props) => (
  <div className="item event">
    <Avatar src={props.user.avatar} />
    <div className="content">
      <div className="summary">
        <a>{props.user.email}</a> posted this message
        <div className="date">
          {props.created}
        </div>
      </div>
      <Message text={props.text} />
      <div className="meta">
        <a className="hot like" onClick={() => props.onHot(props.id)}>
          <i className="like icon"></i> Hot {(props.likes) ? Object.keys(props.likes).length: null}
        </a>
      </div>
    </div>
  </div>
);

Item.propTypes = {
  created: PropTypes.string.isRequired,
  id: PropTypes.string.isRequired,
  text: PropTypes.string.isRequired,
  onHot: PropTypes.func.isRequired,
  user: PropTypes.shape({
    email: PropTypes.string,
    avatar: PropTypes.string.isRequired,
  }),
}

export default Item;
