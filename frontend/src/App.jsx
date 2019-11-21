import React, { Component } from "react";
import { hot } from "react-hot-loader";
import "./App.scss";
import Recommender from "./Recommender.jsx";

class App extends React.Component {
  render() {
    return (
      <div className="App">
        <h1> Hello, World! </h1>
          <Recommender />
      </div>
    );
  }
}

export default hot(module)(App);
