import React, { Component } from 'react';
import { hot } from 'react-hot-loader';
import './App.scss';
import Recommender from './Recommender';

class App extends Component {
  render() {
    return (
      <div className="App">
        <Recommender />
      </div>
    );
  }
}

export default hot(module)(App);
