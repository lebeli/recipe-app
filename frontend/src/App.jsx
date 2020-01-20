import { hot } from "react-hot-loader";
import React, { Component } from "react";
import AddRecipe from "./AddRecipe";
import Details from "./Details";
import Header from "./Header";
import Filter from "./Filter";
import Footer from "./Footer";
import Recommender from "./Recommender";
import ToastImage from "./images/toast.jpg";
import LasagneImage from "./images/lasagne.jpg";
import "./App.scss";

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      recipe: {},
      image: "/api/images/5.jpg",
      pageNumber: 0,
      breakfast: false,
      lunch: false,
      dinner: false,
      vegetarian: false,
      vegan: false,
      longTime: false,
      shortTime: false
    };

    this.goToDetails = this.goToDetails.bind(this);
    this.handleGoBack = this.handleGoBack.bind(this);
    this.changeRecipe = this.changeRecipe.bind(this);
    this.updateRecipe = this.updateRecipe.bind(this);
  }

  componentDidMount() {
    console.log("componentDidMount");
    // initial recipe fetch
    const options = {
      method: "GET",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
        breakfast: true,
        lunch: true,
        dinner: true,
        vegetarian: true,
        vegan: true,
        longTime: true,
        shortTime: true
      }
    };

    fetch("/api/recipes", options)
      .then(response => response.json())
      .then(response => {
        this.setState({
          recipe: response[0]
        });

        var imageUrl = response[0].image;

        // TODO: raus
        if (response[0].image.includes("localhost")) {
          imageUrl = imageUrl.substring(9);
        }

        fetch(imageUrl, {
          method: "GET"
        })
          .then(response => response.blob())
          .then(response => {
            this.setState({
              image: URL.createObjectURL(response)
            });
          })
          .catch(error => {
            console.log("fetch image - error");
            // Error-Handling
          });
      })
      .catch(error => {
        console.log("fetch recipes - error");
        // Error-Handling
      });
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
    shortTime
  ) {
    this.setState(
      {
        breakfast: breakfast,
        lunch: lunch,
        dinner: dinner,
        vegetarian: vegetarian,
        vegan: vegan,
        longTime: longTime,
        shortTime: shortTime
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
        "Content-Type": "application/json",
        breakfast: this.state.breakfast,
        lunch: this.state.lunch,
        dinner: this.state.dinner,
        vegetarian: this.state.vegetarian,
        vegan: this.state.vegan,
        longTime: this.state.longTime,
        shortTime: this.state.shortTime
      }
    };

    fetch("/api/recipes", options)
      .then(response => response.json())
      .then(response => {
        //TODO: Nachher kommt nur noch ein Rezept zurück und muss weitergegeben werden
        this.setState({
          recipe: response[0]
        });

        var imageUrl = response[0].image;

        // TODO: raus
        if (response[0].image.includes("localhost")) {
          imageUrl = imageUrl.substring(9);
        }
        fetch(imageUrl, {
          method: "GET"
        })
          .then(response => response.blob())
          .then(response => {
            this.setState({
              image: URL.createObjectURL(response)
            });
          })
          .catch(error => {
            console.log("fetch image - error");
            // Error-Handling
          });
      })
      .catch(error => {
        console.log("updateState - fetch - error: " + error);
        // TODO: Text "no matching recipe"
      });
  }

  render() {
    const pageNumber = this.state.pageNumber;
    let content;
    if (pageNumber == 0) {
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
    } else {
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
