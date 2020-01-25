import React, { Component } from "react";
import "./AddRecipe.scss";
import { Grid, Button } from "@material-ui/core";
import img from "./images/add_recipe.jpg";
import AddRecipeForm from "./AddRecipeForm";

class AddRecipe extends Component {
  constructor(params) {
    super(params);

    this.state = {
      showForm: false
    };

    this.openForm = this.openForm.bind(this);
    this.closeForm = this.closeForm.bind(this);
  }

  openForm() {
    this.setState({
      showForm: true
    });
  }

  closeForm() {
    this.setState({
      showForm: false
    });
  }

  render() {
    return (
      <div className="Addrecipe">
        <Grid container spacing={5} id="add_recipe_container">
          <React.Fragment>
            <Grid item sm={12} md={7} id="add_recipe_description">
              <h2 id="add_recipe_headline">Foodies aufgepasst!</h2>
              <p>
                Wir von FOODBABY sind stets darum bemüht euch täglich neue
                Rezepte zu präsentieren, um euch nicht immer mit demselben Fraß
                zu langweilen. Und auf dieser Mission könnt ihr uns nun
                behilflich sein. Grabt die altbewährten Rezepte von Oma aus und
                tut der FOODBABY Community etwas Gutes indem ihr auf den Button
                unter diesem Text klickt und das Formular ausfüllt.
              </p>
              <p>
                Bei der Wahl eines Namens für euer Rezept sind euch natürlich
                keine Grenzen gesetzt. Kleiner Tipp am Rande, Rezepte mit einer
                Alliteration im Namen werden häufiger angezeigt, als Rezepte mit
                langweiligem Namen ;) Auch ein schön inszeniertes Stillleben des
                Gerichtes sollte nicht fehlen. Also gebt euch Mühe!
              </p>
              <div id="add_recipe_button_container">
                <Button
                  id="open_form_button"
                  color="secondary"
                  variant="contained"
                  onClick={this.openForm}
                >
                  Rezept hinzufügen
                </Button>
              </div>
              {this.state.showForm && (
                <AddRecipeForm closeForm={this.closeForm} />
              )}
            </Grid>
            <Grid item sm={12} md={5} id="add_recipe_image_container">
              <img width="100%" src={img} />
            </Grid>
          </React.Fragment>
        </Grid>
      </div>
    );
  }
}

export default AddRecipe;
