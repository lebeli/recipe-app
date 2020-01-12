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
