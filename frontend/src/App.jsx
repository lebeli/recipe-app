import React, { Component } from 'react';
import { hot } from 'react-hot-loader';
import './App.scss';
import Filter from './Filter';

class App extends Component {
  render() {
    return (
      <div className="App">
        <h1> Hello, World!</h1>
        <Filter className="Filter" />
      </div>
    );
  }
}

export default hot(module)(App);
