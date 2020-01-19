import React, { Component } from "react";
import BackButton from "./BackButton";
import Ingredients from "./Ingredients";
import Instructions from "./Instructions";
import RecommenderHeader from "./RecommenderHeader";
import RecommenderImage from "./RecommenderImage";
import LasagneImage from "./images/lasagne.jpg";
import "./Details.scss";

class Details extends Component {
  constructor(props) {
    super(props);
    this.state = {
      pageNumber: 1,
      ingredients: [
        {
          name: "flour",
          typeAmount: "300g"
        },
        {
          name: "cucumber",
          typeAmount: "1"
        }
      ],
      instructions: [
        "Banannaaaaa",
        "Gruuuu!",
        "Halloosn viawhbfiaw hfeiuwbfhbwe diqwbdhbvwei lfhea iugf46griwa zdegksg fuishfiu gfzsgdfjz sfv,jhbs< fhksbc. kjsbdvjk bydskjvf sudhv ousgczs h!!"
      ]
    };
  }

  render() {
    return (
      <div className="Details">
        <RecommenderImage image={this.props.image} />
        <div className="DetailedInfo">
          <div className="TextInfo">
            <BackButton
              className="BackButton"
              handleGoBack={this.props.handleGoBack}
            />
            <RecommenderHeader
              recipeName={this.props.recipeName}
              duration={this.props.duration}
            />
            <Ingredients ingredients={this.state.ingredients} />
            <Instructions instructions={this.state.instructions} />
          </div>
          <div className="FullImage">
            <img src={this.props.image} alt="finished dish" />
          </div>
        </div>
      </div>
    );
  }
}

export default Details;
