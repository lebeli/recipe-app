import { hot } from "react-hot-loader";
import React, { Component } from "react";
import AddRecipe from "./AddRecipe";
import Details from "./Details";
import Header from "./Header";
import Filter from "./Filter";
import Footer from "./Footer";
import Recommender from "./Recommender";
import "./App.scss";
import EmptyPlate from "./images/empty_plate.jpg";

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      errorImage: EmptyPlate,
      recipe: {},
      image: EmptyPlate,
      pageNumber: 0,
      breakfast: false,
      lunch: false,
      dinner: false,
      vegetarian: false,
      vegan: false,
      longTime: false,
      shortTime: false,
      chosen_ingredients: []
    };

    this.goToDetails = this.goToDetails.bind(this);
    this.handleGoBack = this.handleGoBack.bind(this);
    this.changeRecipe = this.changeRecipe.bind(this);
    this.updateRecipe = this.updateRecipe.bind(this);
  }

  componentDidMount() {
    // initial recipe fetch
    this.getRecipe();
  }

  goToDetails() {
    this.setState({
      pageNumber: 1
    });
  }

  handleGoBack() {
    this.setState({
      pageNumber: 0
    });
  }

  changeRecipe() {
    this.getRecipe();
  }

  updateRecipe(
    breakfast,
    lunch,
    dinner,
    vegetarian,
    vegan,
    longTime,
    shortTime,
    chosen_ingredients
  ) {
    this.setState(
      {
        breakfast: breakfast,
        lunch: lunch,
        dinner: dinner,
        vegetarian: vegetarian,
        vegan: vegan,
        longTime: longTime,
        shortTime: shortTime,
        chosen_ingredients: chosen_ingredients
      },
      () => {
        this.getRecipe();
      }
    );
  }

  getRecipe() {
    const options = {
      method: "GET",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      }
    };

    var params = {
      breakfast: this.state.breakfast,
      lunch: this.state.lunch,
      dinner: this.state.dinner,
      vegetarian: this.state.vegetarian,
      vegan: this.state.vegan,
      longTime: this.state.longTime,
      shortTime: this.state.shortTime,
      ingredients: this.state.chosen_ingredients
    };

    var requestParams = Object.keys(params)
      .map(key => key + "=" + params[key])
      .join("&");

    fetch("/api/recipes" + "?" + requestParams, options)
      .then(response => response.json())
      .then(response => {
        //TODO: Nachher kommt nur noch ein Rezept zurück und muss weitergegeben werden

        if (response) {
          this.setState({
            recipe: response
          });
          var imageUrl = response.image;

          fetch("/api/images/" + imageUrl, {
            method: "GET"
          })
            .then(response => response.blob())
            .then(response => {
              if (response) {
                this.setState({
                  pageNumber: 0,
                  image: URL.createObjectURL(response)
                });
              }
            })
            .catch(() => {
              this.setState({
                pageNumber: 1000
              });
            });
        } else {
          this.setState({
            pageNumber: 1000
          });
        }
      })
      .catch(() => {
        this.setState({
          pageNumber: 1000
        });
      });
  }

  render() {
    const pageNumber = this.state.pageNumber;
    let content;
    if (pageNumber == 0 && this.state.recipe) {
      content = (
        <div>
          <Filter updateRecipe={this.updateRecipe} />
          <Recommender
            goToDetails={this.goToDetails}
            image={this.state.image}
            recipeName={this.state.recipe.name}
            duration={this.state.recipe.totalTime}
            changeRecipe={this.changeRecipe}
          />
          <AddRecipe />
        </div>
      );
    } else if (pageNumber == 1 && this.state.recipe) {
      content = (
        <Details
          handleGoBack={this.handleGoBack}
          image={this.state.image}
          recipeName={this.state.recipe.name}
          duration={this.state.recipe.totalTime}
          ingredients={this.state.recipe.ingredients}
          instructions={this.state.recipe.instructions}
        />
      );
    } else {
      content = (
        <div>
          <Filter updateRecipe={this.updateRecipe} />
          <div id="error_container">
            <div className="error_image">
              <img src={this.state.errorImage} alt="Kein Rezept gefunden" />
            </div>
            <div id="error_text">
              Wir haben leider kein passendes Rezept gefunden.
            </div>
          </div>
          <AddRecipe />
        </div>
      );
    }
    return (
      <div className="App">
        <Header />
        {content}
        <Footer />
      </div>
    );
  }
}

export default hot(module)(App);
