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

class Filter extends Component {
  constructor(params) {
    super(params);
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
      fruehstueck: false,
      mittagessen: false,
      abendessen: false,
      vegetarisch: false,
      vegan: false,
      schnell: false,
      ich_habe_zeit: false,
      chosen_ingredients: []
    };

    this.updateState = this.updateState.bind(this);
  }

  calculateActiveFilters() {
    let active = 0;
    if (this.state.fruehstueck == true) {
      active = active + 1;
    }
    if (this.state.mittagessen == true) {
      active = active + 1;
    }
    if (this.state.abendessen == true) {
      active = active + 1;
    }
    if (this.state.vegetarisch == true) {
      active = active + 1;
    }
    if (this.state.vegan == true) {
      active = active + 1;
    }
    if (this.state.schnell == true) {
      active = active + 1;
    }
    if (this.state.ich_habe_zeit == true) {
      active = active + 1;
    }

    active = active + this.state.chosen_ingredients.length;
    return <span>{active} Filter aktiv</span>;
  }

  updateState(name, val) {
    this.setState({
      [name]: val
    });
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
                    fruehstueck={this.state.fruehstueck}
                    mittagessen={this.state.mittagessen}
                    abendessen={this.state.abendessen}
                    vegetarisch={this.state.vegetarisch}
                    vegan={this.state.vegan}
                    schnell={this.state.schnell}
                    ich_habe_zeit={this.state.ich_habe_zeit}
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
