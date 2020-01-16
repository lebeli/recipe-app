import React, { Component } from "react";
import "./AddRecipeForm.scss";
import {
  Box,
  Grid,
  Button,
  FormControlLabel,
  Radio,
  FormLabel,
  FormGroup,
  Checkbox,
  FormControl,
  FormHelperText
} from "@material-ui/core";
import CloseIcon from "@material-ui/icons/Close";
import AddIcon from "@material-ui/icons/Add";
import TextField from "@material-ui/core/TextField";
import RadioGroup from "@material-ui/core/RadioGroup";
import PhotoIcon from "@material-ui/icons/Photo";

class AddRecipeForm extends Component {
  constructor(params) {
    super(params);
    this.state = {
      closeForm: params.closeForm,
      name: "",
      meal: "breakfast",
      vegan: false,
      vegetarian: false,
      dietForm: "keine",
      duration: "01:00",
      ingredientsAmount: 0,
      ingredients: [],
      stepsAmount: 0,
      steps: [],
      image: "",
      feedback: false,
      valid: false
    };

    this.addIngredient = this.addIngredient.bind(this);
    this.addStep = this.addStep.bind(this);
  }

  updateStateParameters(name, event) {
    this.setState({
      [name]: event.target.value
    });
  }

  setDietForms(name, value) {
    this.setState({
      [name]: value
    });
  }

  showIngredients() {
    var ingredientsComponent = new Array();
    if (this.state.ingredientsAmount > 0) {
      for (var x = 0; x < this.state.ingredientsAmount; x++) {
        ingredientsComponent.push(
          <Grid
            className="ingredient"
            container
            spacing={2}
            key={"ingredient" + (x + 1)}
            id={"ingredient" + (x + 1)}
          >
            <Grid item sm={3}>
              <TextField
                id={x.toString()}
                className="ingredient_amount"
                label="Menge*"
                placeholder="Bspw. 100g"
                variant="outlined"
                onChange={evt =>
                  this.updateIngredient(evt.target.id, 1, evt.target.value)
                }
              />
            </Grid>
            <Grid item sm={8}>
              <TextField
                id={x.toString()}
                className="ingredient_name"
                label="Zutat*"
                placeholder="bspw. Butter"
                variant="outlined"
                onChange={evt =>
                  this.updateIngredient(evt.target.id, 0, evt.target.value)
                }
              />
            </Grid>
            {x == this.state.ingredientsAmount - 1 && (
              <Grid item xs={1} sm={1} className="remove_step_button">
                <Button
                  className="remove_step"
                  disableRipple
                  onClick={() => this.removeIngredient()}
                >
                  <CloseIcon />
                </Button>
              </Grid>
            )}
          </Grid>
        );
      }
    }
    return ingredientsComponent;
  }

  updateIngredient(ingredientsIndex, ingredientIndex, value) {
    var ingredientsCopy = Object.values(this.state.ingredients);

    var ingredient = ingredientsCopy[ingredientsIndex];
    ingredient[ingredientIndex][1] = value;
    ingredientsCopy.splice(ingredientsIndex, 1, ingredient);

    this.setState({
      ingredients: ingredientsCopy
    });
  }

  addIngredient() {
    // Add an entry into the ingredients array, so that it can be updated with "updateIngredient"
    var ingredientsCopy = this.state.ingredients;
    var emptyIngredient = [
      ["name", ""],
      ["typeAmount", ""]
    ];
    ingredientsCopy.push(emptyIngredient);

    this.setState({
      ingredientsAmount: this.state.ingredientsAmount + 1,
      ingredients: ingredientsCopy
    });
  }

  removeIngredient() {
    var ingredientsCopy = this.state.ingredients;
    ingredientsCopy.splice(ingredientsCopy.length - 1, 1);
    this.setState({
      ingredientsAmount: this.state.ingredientsAmount - 1,
      ingredients: ingredientsCopy
    });
  }

  showSteps() {
    var steps = [];
    if (this.state.stepsAmount > 0) {
      for (var x = 0; x < this.state.stepsAmount; x++) {
        steps.push(
          <Grid
            className="step"
            container
            spacing={2}
            key={"step" + (x + 1)}
            id={"step" + (x + 1)}
          >
            <Grid item xs={10} sm={11}>
              <TextField
                id={"x" + x}
                label={"Schritt " + (x + 1) + "*"}
                variant="outlined"
                multiline
                rows="3"
                onChange={evt =>
                  this.updateStep(event.target.id, evt.target.value)
                }
              />
            </Grid>
            {x == this.state.stepsAmount - 1 && (
              <Grid item xs={1} sm={1} className="remove_step_button">
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

  updateStep(stepIndex, value) {
    var index = stepIndex.substring(1, stepIndex.length);
    var stepsCopy = this.state.steps;
    var step = stepsCopy[index];
    step = value;
    stepsCopy.splice(index, 1, step);

    this.setState({
      steps: stepsCopy
    });
  }

  addStep() {
    // Add an entry into the steps array, so that it can be updated with "updateStep"
    var stepsCopy = this.state.steps;
    var emptyStep = "";
    stepsCopy.push(emptyStep);

    this.setState({
      stepsAmount: this.state.stepsAmount + 1,
      steps: stepsCopy
    });
  }

  removeStep() {
    var stepsCopy = this.state.steps;
    stepsCopy.splice(stepsCopy.length - 1, 1);
    this.setState({
      stepsAmount: this.state.stepsAmount - 1,
      steps: stepsCopy
    });
  }

  handleImageUpload(file) {
    this.setState({
      image: file
    });
  }

  saveRecipe(event) {
    // Prevent Page-Reload after submit
    event.preventDefault();

    // Check if all necessary inputs are given
    if (
      this.state.name != "" &&
      this.state.ingredients != null &&
      this.state.ingredients.length > 0 &&
      this.state.steps != null &&
      this.state.steps.length > 0 &&
      this.state.image != ""
    ) {
      this.setState({
        valid: true,
        feedback: true
      });

      // Calculate the minutes of the totalTime
      var hours = parseInt(this.state.duration.substr(0, 2));
      var minutes = parseInt(this.state.duration.substr(3));
      var totalTime = hours * 60 + minutes;

      // Create "Sets" for Backend from Ingredients Array
      var ingredients = this.state.ingredients;
      var ingredientsSet = [];
      for (let ingredient of ingredients) {
        ingredientsSet.push(Object.fromEntries(ingredient));
      }

      // Put image as FormData
      const formData = new FormData();
      formData.append("file", this.state.image);

      // Options for addImage-fetch
      const addImageOptions = {
        method: "POST",
        body: formData
      };

      fetch("/api/images/add", addImageOptions)
        .then(res => {
          if (res.status == 200) {
            return res.json();
          }
        })
        .then(result => {
          // Body
          var raw = JSON.stringify({
            name: this.state.name,
            image: result.url,
            totalTime: totalTime,
            category: this.state.meal,
            vegetarian: this.state.vegetarian,
            vegan: this.state.vegan,
            ingredients: ingredientsSet,
            instructions: this.state.steps
          });

          // Options for addRecipe-fetch
          const addRecipeOptions = {
            headers: {
              "Content-Type": "application/json"
            },
            method: "POST",
            body: raw
          };

          fetch("/api/recipes/add", addRecipeOptions)
            .then(result => {
              if (result.status == 200) {
                this.resetState();
              }
            })
            .catch(error => {
              console.log("Add recipe went wrong: " + error);
            });
        })
        .catch(error => {
          console.log("Add image or add recipe went wrong: " + error);
        });
    } else {
      this.setState({
        valid: false,
        feedback: true
      });
    }
  }

  resetState() {
    this.setState({
      name: "",
      meal: "breakfast",
      vegan: false,
      vegetarian: false,
      duration: "01:00",
      ingredients: [],
      ingredientsAmount: 0,
      steps: [],
      stepsAmount: 0,
      image: ""
    });
  }

  showFeedback() {
    var feedback;
    if (this.state.valid == false) {
      feedback = (
        <div className="feedback" id="invalid_form_feedback">
          Das Rezept ist leider noch nicht vollständig. Überprüfe die mit *
          markierten Felder.
        </div>
      );
    } else {
      feedback = (
        <div className="feedback" id="valid_form_feedback">
          Das Rezept wurde erfolgreich hinzugefügt. Die FOODBABY Community dankt
          dir :)
        </div>
      );
    }

    return feedback;
  }

  render() {
    return (
      <div className="AddRecipeForm">
        <Box
          display="flex"
          justifyContent="flex-end"
          id="close_button_container"
        >
          <Button disableRipple onClick={this.state.closeForm}>
            <div id="close_button_description">Rezept hinzufügen abbrechen</div>
            <CloseIcon />
          </Button>
        </Box>
        <h3 id="add_recipe_form_headline">Eigenes Rezept hinzufügen</h3>
        <form action="" onSubmit={event => this.saveRecipe(event)}>
          <TextField
            id="recipe_name"
            label="Rezeptname*"
            variant="outlined"
            value={this.state.name}
            onChange={event => this.updateStateParameters("name", event)}
          />
          <Grid container spacing={3} id="meal_dietform_container">
            <Grid item sm={6}>
              <FormLabel component="legend" className="form_labels" required>
                Mahlzeit
              </FormLabel>
              <div className="radioButtons">
                <RadioGroup
                  required
                  aria-label="meal"
                  name="meal"
                  value={this.state.meal}
                  onChange={event => this.updateStateParameters("meal", event)}
                >
                  <FormControlLabel
                    id="breakfast"
                    value="breakfast"
                    control={<Radio disableRipple />}
                    label="Frühstück"
                  />
                  <FormControlLabel
                    id="lunch"
                    value="lunch"
                    control={<Radio disableRipple />}
                    label="Mittagessen"
                  />
                  <FormControlLabel
                    id="dinner"
                    value="dinner"
                    control={<Radio disableRipple />}
                    label="Abendessen"
                  />
                </RadioGroup>
              </div>
            </Grid>
            <Grid item sm={6}>
              <FormLabel component="legend" className="form_labels">
                Ernährungsform
              </FormLabel>
              <div className="checkBoxes">
                <FormGroup aria-label="diet_form">
                  <FormControlLabel
                    control={
                      <Checkbox
                        disableRipple
                        value="vegetarisch"
                        checked={this.state.vegetarian}
                        onChange={() =>
                          this.setDietForms(
                            "vegetarian",
                            !this.state.vegetarian
                          )
                        }
                      />
                    }
                    label="vegetarisch"
                  />
                  <FormControlLabel
                    control={
                      <Checkbox
                        disableRipple
                        value="vegan"
                        checked={this.state.vegan}
                        onChange={() =>
                          this.setDietForms("vegan", !this.state.vegan)
                        }
                      />
                    }
                    label="vegan"
                  />
                </FormGroup>
              </div>
            </Grid>
          </Grid>
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
            onChange={event => this.updateStateParameters("duration", event)}
          />
          <FormLabel component="legend" className="form_labels" required>
            Zutaten
          </FormLabel>
          <div className="ingredients_container">{this.showIngredients()}</div>
          <div className="recipe_add" display="flex">
            <Button
              id="add_ingredient_button"
              onClick={this.addIngredient}
              disableTouchRipple
            >
              <AddIcon />
              Zutat hinzufügen
            </Button>
          </div>
          <FormLabel component="legend" className="form_labels" required>
            Arbeitsschritte
          </FormLabel>
          <div className="steps_container">{this.showSteps()}</div>
          <div className="recipe_add" display="flex">
            <Button onClick={this.addStep} disableTouchRipple>
              <AddIcon />
              Arbeitsschritt hinzufügen
            </Button>
          </div>
          <div id="add_image_button_container">
            <Grid container spacing={1}>
              <Grid item>
                <Button variant="outlined" component="label">
                  <PhotoIcon id="photo_icon" />
                  Bild hochladen*
                  <input
                    name="image"
                    type="file"
                    style={{ display: "none" }}
                    onChange={e => this.handleImageUpload(e.target.files[0])}
                  />
                </Button>
              </Grid>
              <Grid item id="filename_item">
                <div>{this.state.image != null && this.state.image.name}</div>
              </Grid>
            </Grid>
          </div>
          <div id="submit_feedback">
            {this.state.feedback == true && this.showFeedback()}
          </div>
          <div id="recipe_form_submit_button">
            <Button variant="contained" type="submit">
              Rezept speichern
            </Button>
          </div>
        </form>
      </div>
    );
  }
}

export default AddRecipeForm;
