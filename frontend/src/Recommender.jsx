import React, { Component } from "react";
import RecommenderImage from "./RecommenderImage";
import RecommenderHeader from "./RecommenderHeader";
import RecommenderReloadButton from "./RecommenderReloadButton";
import ToastImage from "./images/toast.jpg";
import LasagneImage from "./images/lasagne.jpg";
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
      image: TostImage
    });
  }

  render() {
    return (
      <div className="Recommender" id="Container">
        <div className="RelativeParent">
          <RecommenderHeader
            recipeName={this.props.recipeName}
            time={this.props.time}
          />
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
