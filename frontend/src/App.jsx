import { hot } from "react-hot-loader";
import React, { Component } from "react";
import AddRecipe from "./AddRecipe";
import Details from "./Details";
import Header from "./Header";
import Filter from "./Filter";
import Footer from "./Footer";
import Recommender from "./Recommender";
import "./App.scss";

class App extends Component {
  constructor(params) {
    super(params);
    this.state = {
      pageNumber: 0,
      recipeName: "Lasagne",
      time: "45min"
    };

    this.goToDetails = this.goToDetails.bind(this);
    this.handleGoBack = this.handleGoBack.bind(this);
  }

  goToDetails() {
    this.setState({
      pageNumber: 1
    });
  }

  handleGoBack() {
    this.setState({
      pageNumber: 0
    });
  }

  render() {
    const pageNumber = this.state.pageNumber;
    let content;
    if (pageNumber == 0) {
      content = (
        <Recommender
          goToDetails={this.goToDetails}
          recipeName={this.state.recipeName}
          time={this.state.time}
        />
      );
    } else {
      content = (
        <Details
          handleGoBack={this.handleGoBack}
          recipeName={this.state.recipeName}
          time={this.state.time}
        />
      );
    }
    return (
      <div className="App">
        <Header />
        <Filter />
        {content}
        <Footer />
      </div>
    );
  }
}

export default hot(module)(App);
