import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Item from './Item';

class Feed extends Component {
  static propTypes = {
    onHot: PropTypes.func.isRequired,
    items: PropTypes.arrayOf(PropTypes.shape({
      created: PropTypes.string.isRequired,
      id: PropTypes.string.isRequired,
      text: PropTypes.string.isRequired,
      user: PropTypes.shape({
        email: PropTypes.string,
        avatar: PropTypes.string.isRequired,
      })
    })).isRequired,
  }

  onHotHandler = (id) => {
    this.props.onHot(id);
  }

  render() {
    const { items } = this.props;
    return (
      <div className="ui segment feed">
        {items.map((item) => (
          <Item onHot={this.onHotHandler} key={item.id} {...item} />
        ))}
      </div>
    );
  }
}

export default Feed;
