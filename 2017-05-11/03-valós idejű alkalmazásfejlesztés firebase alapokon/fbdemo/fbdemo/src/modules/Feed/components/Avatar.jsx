import React from 'react';
import PropTypes from 'prop-types';

const Avatar = ({ src }) => (
  <div className="label">
    <img className="avatar" src={src} alt="Avatar" />
  </div>
);

Avatar.propTypes = {
  src: PropTypes.string.isRequired,
};

export default Avatar;
