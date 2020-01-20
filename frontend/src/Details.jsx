import React, { Component } from "react";
import BackButton from "./BackButton";
import Ingredients from "./Ingredients";
import Instructions from "./Instructions";
import RecommenderHeader from "./RecommenderHeader";
import RecommenderImage from "./RecommenderImage";
import LasagneImage from "./images/lasagne.jpg";
import { Grid, Button } from "@material-ui/core";
import AccessTimeIcon from "@material-ui/icons/AccessTime";
import "./Details.scss";

class Details extends Component {
  constructor(props) {
    super(props);
    this.state = {
      pageNumber: 1,
      ingredients: props.ingredients,
      instructions: props.instructions
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
            <Grid container id="details_header">
              <Grid item id="details_recipe_name">
                {this.props.recipeName}
              </Grid>
              <Grid item id="details_duration">
                <AccessTimeIcon />
                {this.props.duration}
              </Grid>
            </Grid>
            {/* <RecommenderHeader
              recipeName={this.props.recipeName}
              duration={this.props.duration}
            /> */}
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
