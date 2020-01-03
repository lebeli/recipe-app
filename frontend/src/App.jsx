import { hot } from "react-hot-loader";
import React, { Component } from "react";
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
      pageNumber: 0
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
      content = <Recommender goToDetails={this.goToDetails} />;
    } else {
      content = <Details handleGoBack={this.handleGoBack} />;
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
