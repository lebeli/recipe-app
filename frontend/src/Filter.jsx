import React, { Component, useState } from "react";
import ExpansionPanel from "@material-ui/core/ExpansionPanel";
import ExpansionPanelSummary from "@material-ui/core/ExpansionPanelSummary";
import ExpansionPanelDetails from "@material-ui/core/ExpansionPanelDetails";
import ExpandMoreIcon from "@material-ui/icons/ExpandMore";
import Typography from "@material-ui/core/Typography";
import Box from "@material-ui/core/Box";
import ToggleButtons from "./ToggleButtons";
import Tags from "./Tags";
import "./Filter.scss";

class Filter extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      ingredients: [
        "Gurke",
        "Tomate",
        "Zwiebel",
        "Knoblauch",
        "Ziegenkäse",
        "Gouda",
        "Erdnüsse"
      ],
      breakfast: false,
      lunch: false,
      dinner: false,
      vegetarian: false,
      vegan: false,
      fast: false,
      i_have_time: false,
      chosen_ingredients: []
    };

    this.updateState = this.updateState.bind(this);
  }

  componentDidMount() {
    const options = {
      method: "GET",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      }
    };

    fetch("/api/ingredients", options)
      .then(response => response.json())
      .then(response => {
        var ingredientsArray = [];
        for (let ingredient of response) {
          ingredientsArray.push(ingredient.name);
        }
        this.setState({
          ingredients: ingredientsArray
        });
      })
      .catch(error => {
        this.setState({
          ingredients: ["Keine Zutaten zur Auswahl"]
        });
      });
  }

  shouldComponentUpdate() {
    console.log("it should");
    return true;
  }

  calculateActiveFilters() {
    let active = 0;
    if (this.state.breakfast == true) {
      active = active + 1;
    }
    if (this.state.lunch == true) {
      active = active + 1;
    }
    if (this.state.dinner == true) {
      active = active + 1;
    }
    if (this.state.vegetarian == true) {
      active = active + 1;
    }
    if (this.state.vegan == true) {
      active = active + 1;
    }
    if (this.state.fast == true) {
      active = active + 1;
    }
    if (this.state.i_have_time == true) {
      active = active + 1;
    }

    active = active + this.state.chosen_ingredients.length;
    return <span>{active} Filter aktiv</span>;
  }

  updateState(name, val) {
    this.setState(
      {
        [name]: val
      },
      () => {
        this.props.updateRecipe(
          this.state.breakfast,
          this.state.lunch,
          this.state.dinner,
          this.state.vegetarian,
          this.state.vegan,
          this.state.i_have_time,
          this.state.fast
        );
      }
    );
  }

  render() {
    return (
      <div className="Filter">
        <ExpansionPanel square id="filter-panel">
          <Box justifyContent="center">
            <Box>
              <ExpansionPanelSummary
                expandIcon={<ExpandMoreIcon id="search-expand-icon" />}
                aria-controls="filter-panel-content"
                id="filter-panel-header-summery"
              >
                <Typography component="span" id="filter-header">
                  Suche eingrenzen ({this.calculateActiveFilters()})
                </Typography>
              </ExpansionPanelSummary>
            </Box>
          </Box>
          <Box display="flex" justifyContent="center" m={1} p={1}>
            <Box p={1}>
              <ExpansionPanelDetails>
                <Typography component="span">
                  <ToggleButtons
                    breakfast={this.state.breakfast}
                    lunch={this.state.lunch}
                    dinner={this.state.dinner}
                    vegetarian={this.state.vegetarian}
                    vegan={this.state.vegan}
                    fast={this.state.fast}
                    i_have_time={this.state.i_have_time}
                    chosen_ingredients={this.state.chosen_ingredients}
                    updateState={this.updateState}
                  />
                  <Tags
                    ingredients={this.state.ingredients}
                    chosen_ingredients={this.state.chosen_ingredients}
                    updateState={this.updateState}
                  />
                </Typography>
              </ExpansionPanelDetails>
            </Box>
          </Box>
        </ExpansionPanel>
      </div>
    );
  }
}

export default Filter;
