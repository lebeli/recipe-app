import { hot } from "react-hot-loader";
import React, { Component } from "react";
import Header from "./Header";
import Filter from "./Filter";
import Footer from "./Footer";
import Details from "./Details";
import "./App.scss";

class App extends Component {
  constructor(params) {
    super(params);
    this.state = {
      pageNumber: 1
    };
  }

  render() {
    const pageNumber = this.state.pageNumber;
    let content;
    if (pageNumber == 0) {
      content = <Filter />;
    } else {
      content = <Details />;
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
