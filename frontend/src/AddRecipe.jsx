import React, { Component } from "react";
import "./AddRecipe.scss";
import {
  Box,
  Grid,
  Button,
  FormControlLabel,
  Radio,
  FormLabel
} from "@material-ui/core";
import CloseIcon from "@material-ui/icons/Close";
import AddIcon from "@material-ui/icons/Add";
import TextField from "@material-ui/core/TextField";
import RadioGroup from "@material-ui/core/RadioGroup";
import PhotoIcon from "@material-ui/icons/Photo";
import img from "./images/add_recipe.jpg";

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
                Minions ipsum uuuhhh tulaliloo belloo! Wiiiii poulet tikka
                masala bananaaaa para tú jeje poopayee po kass. Pepete tank
                yuuu! Jeje jiji. Bee do bee do bee do belloo! Bananaaaa gelatooo
                bananaaaa chasy para tú tulaliloo. Jeje chasy jiji tank yuuu! Po
                kass ti aamoo! Gelatooo wiiiii jiji. Wiiiii chasy aaaaaah me
                want bananaaa! Tulaliloo po kass baboiii tank yuuu! Bananaaaa la
                bodaaa tank yuuu! Chasy hana dul sae para tú po kass poulet
                tikka masala tulaliloo poopayee belloo! Daa la bodaaa tank yuuu!{" "}
              </p>
              <p>
                Tulaliloo gelatooo poopayee wiiiii baboiii butt bananaaaa
                belloo! Poulet tikka masala baboiii chasy. Potatoooo para tú
                potatoooo tulaliloo tank yuuu! Baboiii jiji. Jeje poulet tikka
                masala uuuhhh jiji para tú pepete hana dul sae chasy. Para tú
                pepete gelatooo la bodaaa tatata bala tu tulaliloo tatata bala
                tu. Jeje hana dul sae wiiiii jeje hahaha jiji. Butt tatata bala
                tu poulet tikka masala ti aamoo! Jeje hahaha baboiii. Poopayee
                ti aamoo! Gelatooo tulaliloo bananaaaa underweaaar bananaaaa
                tulaliloo. Chasy ti aamoo! Daa para tú jeje.
              </p>
              <div id="add_recipe_button_container">
                <Button
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

class AddRecipeForm extends Component {
  constructor(params) {
    super(params);
    this.state = {
      meal: "fruehstueck",
      dietForm: "keine",
      closeForm: params.closeForm,
      steps: 1,
      image: {}
    };

    this.handleMeal = this.handleMeal.bind(this);
    this.handleDietForm = this.handleDietForm.bind(this);
    this.addStep = this.addStep.bind(this);
    this.showSteps = this.showSteps.bind(this);
    this.removeStep = this.removeStep.bind(this);
    this.handleImageUpload = this.handleImageUpload.bind(this);
  }

  handleMeal(event) {
    this.setState({
      meal: event.target.value
    });
  }

  handleDietForm(event) {
    this.setState({
      dietForm: event.target.value
    });
  }

  showSteps() {
    var steps = [];
    if (this.state.steps > 0) {
      for (var x = 0; x < this.state.steps; x++) {
        steps.push(
          <Grid
            container
            spacing={2}
            key={"step" + (x + 1)}
            id={"step" + (x + 1)}
          >
            <Grid item xs={10} sm={11}>
              <TextField
                label={"Schritt " + (x + 1)}
                variant="outlined"
                multiline
                rows="3"
              />
            </Grid>
            {x == this.state.steps - 1 && (
              <Grid item xs={1} sm={1} id="remove_step_button">
                <Button
                  className="remove_step"
                  disableRipple
                  onClick={() => this.removeStep()}
                >
                  <CloseIcon />
                </Button>
              </Grid>
            )}
          </Grid>
        );
      }
    }
    return steps;
  }

  addStep() {
    this.setState({
      steps: this.state.steps + 1
    });
  }

  removeStep() {
    this.setState({
      steps: this.state.steps - 1
    });
  }

  handleImageUpload(file) {
    this.setState({
      file: file
    });
  }

  render() {
    return (
      <div className="AddRecipeForm">
        <Box
          display="flex"
          justifyContent="flex-end"
          id="close_button_container"
        >
          <p id="close_button_description">Rezept hinzufügen abbrechen</p>
          <Button disableRipple onClick={this.state.closeForm}>
            <CloseIcon />
          </Button>
        </Box>
        <h3 id="add_recipe_form_headline">Eigenes Rezept hinzufügen</h3>
        <form action="">
          <TextField
            id="recipe_name"
            required
            label="Rezeptname"
            variant="outlined"
          />
          <Grid container spacing={3} id="radio_buttons_container">
            <Grid item sm={6}>
              <FormLabel component="legend" className="form_labels" required>
                Mahlzeit
              </FormLabel>
              <div className="radioButtons">
                <RadioGroup
                  aria-label="meal"
                  name="meal"
                  value={this.state.meal}
                  onChange={this.handleMeal}
                >
                  <FormControlLabel
                    value="fruehstueck"
                    control={<Radio disableRipple />}
                    label="Frühstück"
                  />
                  <FormControlLabel
                    value="mittagessen"
                    control={<Radio disableRipple />}
                    label="Mittagessen"
                  />
                  <FormControlLabel
                    value="abendessen"
                    control={<Radio disableRipple />}
                    label="Abendessen"
                  />
                </RadioGroup>
              </div>
            </Grid>
            <Grid item sm={6}>
              <FormLabel component="legend" className="form_labels" required>
                Ernährungsform
              </FormLabel>
              <div className="radioButtons">
                <RadioGroup
                  aria-label="diet_form"
                  name="diet_form"
                  value={this.state.dietForm}
                  onChange={this.handleDietForm}
                >
                  <FormControlLabel
                    value="keine"
                    control={<Radio disableRipple />}
                    label="keine"
                  />
                  <FormControlLabel
                    value="vegetarisch"
                    control={<Radio disableRipple />}
                    label="vegetarisch"
                  />
                  <FormControlLabel
                    value="vegan"
                    control={<Radio disableRipple />}
                    label="vegan"
                  />
                </RadioGroup>
              </div>
            </Grid>
          </Grid>
          <FormLabel component="legend" className="form_labels" required>
            Zutaten
          </FormLabel>
          <TextField
            id="recipe_ingredients"
            required
            multiline
            rows="8"
            label="Zutaten"
            placeholder="Bspw. 100g Butter"
            variant="outlined"
          />
          <FormLabel component="legend" className="form_labels" required>
            Zubereitungsdauer
          </FormLabel>
          <TextField
            id="recipe_time"
            required
            className="recipe_time"
            label="std/min"
            type="time"
            defaultValue="01:00"
            variant="outlined"
            InputLabelProps={{
              shrink: true
            }}
            inputProps={{
              step: 300 // 5 min
            }}
          />
          <FormLabel component="legend" className="form_labels" required>
            Arbeitsschritte
          </FormLabel>
          <div>{this.showSteps()}</div>
          <div id="recipe_add_working_step" display="flex">
            <Button onClick={this.addStep} disableTouchRipple>
              <AddIcon />
              Arbeitsschritt hinzufügen*
            </Button>
          </div>
          <div id="add_image_button_container">
            <Grid container spacing={1}>
              <Grid item>
                <Button variant="outlined" component="label">
                  <PhotoIcon id="photo_icon" />
                  Bild hochladen
                  <input
                    required
                    type="file"
                    style={{ display: "none" }}
                    onChange={e => this.handleImageUpload(e.target.files[0])}
                  />
                </Button>
              </Grid>
              <Grid item id="filename_item">
                <div>{this.state.file != null && this.state.file.name}</div>
              </Grid>
            </Grid>
          </div>
        </form>
      </div>
    );
  }
}

export default AddRecipe;
