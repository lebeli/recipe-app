import { hot } from "react-hot-loader";
import React, { Component } from "react";
import Header from "./Header";
import Footer from "./Footer";
import Details from "./Details";
import "./App.scss";

class App extends Component {
  constructor(params) {
    super(params);
  }

  render() {
    return (
      <div className="App">
        <Header />
        <Details />
        <Footer />
      </div>
    );
  }
}

export default hot(module)(App);
