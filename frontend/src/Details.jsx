import React, { Component } from "react";
import BackButton from "./BackButton";
import Ingredients from "./Ingredients";
import Instructions from "./Instructions";
import "./Details.scss";

class Details extends Component {
  constructor(props) {
    super(props);
    this.state = {
      pageNumber: 1
    };
  }

  render() {
    return (
      <div className="Details">
        <BackButton handleGoBack={this.props.handleGoBack} />
        <Ingredients />
        <Instructions />
      </div>
    );
  }
}

export default Details;
