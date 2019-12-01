import { hot } from 'react-hot-loader';
import React, { Component } from 'react';
import Filter from './Filter';
import './App.scss';


class App extends Component {
  constructor(params) {
    super(params);
    this.state = {
      ingredients: [
        'Gurke', 'Tomate', 'Zwiebel',
      ],
    };
  }

  render() {
    const { ingredients } = this.state;
    return (
      <div className="App">
        <Filter className="Filter" ingredients={ingredients} />
      </div>
    );
  }
}

export default hot(module)(App);
