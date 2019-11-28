import React, { Component } from 'react';
import { hot } from 'react-hot-loader';
import Filter from './Filter';
import './App.scss';


class App extends Component {
  render() {
    return (
      <div className="App">
        <Filter className="Filter" />
      </div>
    );
  }
}

export default hot(module)(App);
