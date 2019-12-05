import React, { Component } from 'react';

class RecommenderImage extends Component {
  constructor(props) {
    super(props);
    this.state = {
      teaserImage: this.props.teaserImage,
    }
  }

  render() {
    return (
      <div className="TeaserImage">
        <img src={teaserImage} alt="finished dish" width="100%" height="100%" />
      </div>
    );
  }
}

export default RecommenderImage;
