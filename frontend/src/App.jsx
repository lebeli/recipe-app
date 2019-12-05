import { hot } from "react-hot-loader";
import React, { Component } from "react";
import Header from "./Header";
import Filter from "./Filter";
import "./App.scss";

class App extends Component {
  constructor(params) {
    super(params);
  }

  render() {
    return (
      <div className="App">
        <Header />
        <Filter />
      </div>
    );
  }
}

export default hot(module)(App);
