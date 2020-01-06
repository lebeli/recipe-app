import React, { Component } from "react";
import RecommenderImage from "./RecommenderImage";
import RecommenderHeader from "./RecommenderHeader";
import RecommenderReloadButton from "./RecommenderReloadButton";
import wolfImage from "./resources/wolf.jpg";
import LasagneImage from "./resources/lasagne.jpg";
import "./Recommender.scss";

class Recommender extends Component {
  constructor(props) {
    super(props);
    this.state = {
      image: LasagneImage
    };

    this.changeImage = this.changeImage.bind(this);
  }

  changeImage() {
    this.setState({
      image: wolfImage
    });
  }

  render() {
    return (
      <div className="Recommender" id="Container">
        <div className="RelativeParent">
          <RecommenderHeader />
          <RecommenderReloadButton onClick={this.changeImage} />
          <RecommenderImage
            image={this.state.image}
            onClick={this.props.goToDetails}
          />
        </div>
      </div>
    );
  }
}

export default Recommender;
