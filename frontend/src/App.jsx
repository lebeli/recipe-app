import { hot } from 'react-hot-loader';
import React, { Component } from 'react';
import Header from './Header';
import Filter from './Filter';
import './App.scss';


class App extends Component {
  constructor(params) {
    super(params);
    this.state = {
      ingredients: [
        'Gurke', 'Tomate', 'Zwiebel', 'Knoblauch', 'Ziegenkäse', 'Gouda', 'Erdnüsse',
      ],
    };
  }

  render() {
    const { ingredients } = this.state;
    return (
      <div className="App">
        <Header />
        <Filter ingredients={ingredients} />
      </div>
    );
  }
}

export default hot(module)(App);
