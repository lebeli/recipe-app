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
          onClick={this.props.onClick}
        />
      </div>
    );
  }
}

export default RecommenderImage;
