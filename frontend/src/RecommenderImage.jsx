import React, { Component } from "react";

class RecommenderImage extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div className="TeaserImage">
        <img
          src={this.props.image}
          alt="finished dish"
          width="100%"
          height="100%"
          onClick={this.props.onClick}
        />
      </div>
    );
  }
}

export default RecommenderImage;
