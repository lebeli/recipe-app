import { hot } from "react-hot-loader";
import React, { Component } from "react";
import Header from "./Header";
import Filter from "./Filter";
import Footer from "./Footer";
import "./App.scss";
import AddRecipe from "./AddRecipe";

class App extends Component {
  constructor(params) {
    super(params);
  }

  render() {
    return (
      <div className="App">
        <Header />
        <Filter />
        <AddRecipe />
        <Footer />
      </div>
    );
  }
}

export default hot(module)(App);
