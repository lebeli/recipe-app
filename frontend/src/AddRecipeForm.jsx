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
  Checkbox
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
      image: {},
      fileUrl: ""
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
                required
                label="Menge"
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
                required
                label="Zutat"
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
    ingredient[ingredientIndex] = value;
    ingredientsCopy.splice(ingredientsIndex, 1, ingredient);

    this.setState({
      ingredients: ingredientsCopy
    });
  }

  addIngredient() {
    // Add an entry into the ingredients array, so that it can be updated with "updateIngredient"
    var ingredientsCopy = this.state.ingredients;
    var emptyIngredient = ["", ""];
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
                label={"Schritt " + (x + 1)}
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
      file: file
    });
  }

  saveRecipe() {
    const formData = new FormData();
    formData.append("file", this.state.image);
    fetch("/api/images/add", {
      method: "post",
      body: formData
    }).then(
      result => {
        console.log(result);
        console.log(result.url);
        // fetch("/api/recipes/add", {
        //   method: "post",
        //   headers: { "Content-Type": "application/json" },
        //   body: {
        //     name: this.state.name,
        //     image: result.url,
        //     totalTime: this.state.duration,
        //     category: this.state.meal,
        //     vegetarian: this.state.vegetarian,
        //     vegan: this.state.vegan,
        //     ingredients: this.state.ingredients,
        //     instructions: this.state.instructions
        //   }
        // });
      },
      error => {}
    );

    // TODO: Api call and reset of recipe state, if saving was successful
    // Also visual feedback, if saving was successful
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
                  aria-label="meal"
                  name="meal"
                  value={this.state.meal}
                  onChange={event => this.updateStateParameters("meal", event)}
                >
                  <FormControlLabel
                    value="breakfast"
                    control={<Radio disableRipple />}
                    label="Frühstück"
                  />
                  <FormControlLabel
                    value="lunch"
                    control={<Radio disableRipple />}
                    label="Mittagessen"
                  />
                  <FormControlLabel
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
          <div id="recipe_form_submit_button">
            <Button variant="contained" onClick={() => this.saveRecipe()}>
              Rezept speichern
            </Button>
          </div>
        </form>
      </div>
    );
  }
}

export default AddRecipeForm;
