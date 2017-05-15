import React from 'react';
import PropTypes from 'prop-types';

const Notification = ({ sender, id, onClick }) => (
  <a
    className="item"
    onClick={() => onClick(id)}
  >
    {sender.email} Liked you post
  </a>
)

const AppBar = ({ onSignOut, onNotiReaded, notifications }) => (
  <div className="ui top menu">
    <div className="header item">
      Firebase Demo Feed
    </div>
    <div
      className="ui dropdown item"
    >
      Notifications
      <div className="floating ui red label">{notifications.length}</div>
      <i className="ui dropdown icon"></i>
      <div className="menu">
        {notifications.map(noti => (
          <Notification
            key={noti.id}
            onClick={onNotiReaded}
            {...noti}
          />
        ))}
      </div>
    </div>
    <a
      className="item"
      onClick={onSignOut}
    >
      Sign Out
    </a>
  </div>
);

AppBar.propTypes = {
  onSignOut: PropTypes.func.isRequired,
  onNotiReaded: PropTypes.func.isRequired,
  notifications: PropTypes.array.isRequired,
};

export default AppBar;
