import React, { Component } from 'react';

class RecommenderImage extends Component {
  render() {
    const teaserImage = this.props;

    return (
      <div className="TeaserImage">
        <img src={teaserImage} alt="finished dish" width="100%" height="100%" />
      </div>
    );
  }
}

export default RecommenderImage;
