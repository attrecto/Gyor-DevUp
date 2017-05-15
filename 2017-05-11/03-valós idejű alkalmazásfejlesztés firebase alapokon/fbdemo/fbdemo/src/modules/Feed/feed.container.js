import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Feed } from './components';

import { FEED_ITEM_LIKE_REQUEST } from './feed.saga'; 

class FeedContainer extends Component {
  onHotHandler = (id) => {
    this.props.dispatch({
      type: FEED_ITEM_LIKE_REQUEST,
      payload: { itemId: id },
    });
  }

  render() {
    const { items } = this.props;
    return <Feed onHot={this.onHotHandler} items={items} />
  }
}

const mapStateToProps = state => ({
  items: state.feed.items,
});

export default connect(mapStateToProps)(FeedContainer);
