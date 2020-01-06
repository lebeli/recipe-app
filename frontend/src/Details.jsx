import React, { Component } from "react";
import BackButton from "./BackButton";
import Ingredients from "./Ingredients";
import Instructions from "./Instructions";
import RecommenderHeader from "./RecommenderHeader";
import RecommenderImage from "./RecommenderImage";
import LasagneImage from "./resources/lasagne.jpg";
import "./Details.scss";

class Details extends Component {
  constructor(props) {
    super(props);
    this.state = {
      pageNumber: 1,
      image: LasagneImage
    };
  }

  render() {
    return (
      <div className="Details">
        <RecommenderImage image={this.state.image} />
        <div className="DetailedInfo">
          <BackButton
            className="BackButton"
            handleGoBack={this.props.handleGoBack}
          />
          <RecommenderHeader />
          <Ingredients />
          <Instructions />
        </div>
      </div>
    );
  }
}

export default Details;
