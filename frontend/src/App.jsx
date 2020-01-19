import { hot } from "react-hot-loader";
import React, { Component } from "react";
import AddRecipe from "./AddRecipe";
import Details from "./Details";
import Header from "./Header";
import Filter from "./Filter";
import Footer from "./Footer";
import Recommender from "./Recommender";
import ToastImage from "./images/toast.jpg";
import LasagneImage from "./images/lasagne.jpg";
import "./App.scss";

class App extends Component {
  constructor(params) {
    super(params);
    this.state = {
      pageNumber: 0,
      image: LasagneImage,
      recipeName: "Lasagne",
      duration: "45min"
    };

    this.goToDetails = this.goToDetails.bind(this);
    this.handleGoBack = this.handleGoBack.bind(this);
    this.changeRecipe = this.changeRecipe.bind(this);
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

  changeRecipe() {
    this.setState({
      image: ToastImage,
      recipeName: "Toast",
      duration: "20min"
    });
  }

  render() {
    const pageNumber = this.state.pageNumber;
    let content;
    if (pageNumber == 0) {
      content = (
        <div>
          <Filter />
          <Recommender
            goToDetails={this.goToDetails}
            image={this.state.image}
            recipeName={this.state.recipeName}
            duration={this.state.duration}
            changeRecipe={this.changeRecipe}
          />
          <AddRecipe />
        </div>
      );
    } else {
      content = (
        <Details
          handleGoBack={this.handleGoBack}
          image={this.state.image}
          recipeName={this.state.recipeName}
          duration={this.state.duration}
        />
      );
    }
    return (
      <div className="App">
        <Header />
        {content}
        <Footer />
      </div>
    );
  }
}

export default hot(module)(App);
