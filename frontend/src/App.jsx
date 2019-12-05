import { hot } from "react-hot-loader";
import React, { Component } from "react";
import Recommender from "./Recommender";
import "./App.scss";

class App extends Component {
  constructor(params) {
    super(params);
  }

  render() {
    return (
      <div className="App">
        <Recommender />
      </div>
    );
  }
}

export default hot(module)(App);
