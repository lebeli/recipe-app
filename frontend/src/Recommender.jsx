import React, { Component } from "react";
import RecommenderImage from "./RecommenderImage";
import RecommenderHeader from "./RecommenderHeader";
import RecommenderReloadButton from "./RecommenderReloadButton";
import "./Recommender.scss";

class Recommender extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div className="Recommender" id="Container">
        <div className="RelativeParent">
          <RecommenderHeader
            recipeName={this.props.recipeName}
            duration={this.props.duration}
          />
          <RecommenderReloadButton onClick={this.props.changeRecipe} />
          <RecommenderImage
            image={this.props.image}
            onClick={this.props.goToDetails}
          />
        </div>
      </div>
    );
  }
}

export default Recommender;
