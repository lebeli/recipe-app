import React from "react";
import { shallow, mount } from "enzyme";
import AddRecipeForm from "../AddRecipeForm";
import renderer from "react-test-renderer";
import { Button } from "@material-ui/core";

describe("<AddRecipeForm />", () => {
  test("should render elements", () => {
    const wrapper = shallow(<AddRecipeForm />);
    expect(wrapper.find(".AddRecipeForm"));
    expect(wrapper.find("#recipe_name"));
    expect(wrapper.find(".radioButtons").length).toEqual(1);
    expect(wrapper.find(".checkBoxes").length).toEqual(1);
    expect(wrapper.find("#recipe_time"));
    expect(wrapper.find(".ingredients_container"));
    expect(wrapper.find(".steps_container"));
    expect(wrapper.find("#add_image_button_container"));
    expect(wrapper.find("#recipe_form_submit_button"));
  });

  test("updateStateParameters() should update state parameters", () => {
    const wrapper = shallow(<AddRecipeForm />);
    const spy = jest.spyOn(wrapper.instance(), "updateStateParameters");

    expect(wrapper.state("name")).toEqual("");

    var textfield = wrapper.find("#recipe_name");
    textfield.props.value = "state-name";
    textfield.at(0).simulate("change", {
      target: {
        value: "state-name"
      }
    });
    expect(spy).toHaveBeenCalled();
    expect(wrapper.state("name")).toEqual("state-name");
  });

  test("showIngredients() should show a user defined amount of input elements", () => {
    const wrapper = shallow(<AddRecipeForm />);
    // spy on methods "showIngredients()" and "addIngredient()"
    const spyShowIngredients = jest.spyOn(
      wrapper.instance(),
      "showIngredients"
    );
    const spyAddIngredient = jest.spyOn(wrapper.instance(), "addIngredient");

    // Given
    expect(wrapper.state("ingredientsAmount")).toEqual(0);
    expect(wrapper.state("ingredients")).toEqual([]);
    expect(wrapper.find(".ingredient").length).toEqual(0);

    // When
    wrapper.instance().addIngredient();

    // Then
    expect(wrapper.state("ingredientsAmount")).toEqual(1);
    expect(wrapper.state("ingredients")).toEqual([["", ""]]);
    expect(wrapper.find(".ingredient").length).toEqual(1);
    expect(spyShowIngredients).toHaveBeenCalled();
    expect(spyAddIngredient).toHaveBeenCalled();
  });

  test("updateIngredients() should update the ingredients array", () => {
    const wrapper = shallow(<AddRecipeForm />);

    // Given
    expect(wrapper.state("ingredients")).toEqual([]);

    // When
    wrapper.instance().addIngredient();

    // Then
    expect(wrapper.state("ingredients")).toEqual([["", ""]]);

    // When
    wrapper.instance().updateIngredient(0, 0, "100g");

    // Then
    expect(wrapper.state("ingredients")).toEqual([["100g", ""]]);
  });

  test("addIngredient() should change the state of 'ingredientsAmount' and 'ingredients'", () => {
    const wrapper = shallow(<AddRecipeForm />);

    // Given
    expect(wrapper.state("ingredientsAmount")).toEqual(0);
    expect(wrapper.state("ingredients")).toEqual([]);

    // When
    wrapper.instance().addIngredient();

    // Then
    expect(wrapper.state("ingredientsAmount")).toEqual(1);
    expect(wrapper.state("ingredients")).toEqual([["", ""]]);
  });

  test("removeIngredient() should change the state of 'ingredientsAmount' and 'ingredients'", () => {
    const wrapper = shallow(<AddRecipeForm />);

    // Given
    expect(wrapper.state("ingredientsAmount")).toEqual(0);
    expect(wrapper.state("ingredients")).toEqual([]);

    // When
    wrapper.instance().addIngredient();

    // Then
    expect(wrapper.state("ingredientsAmount")).toEqual(1);
    expect(wrapper.state("ingredients")).toEqual([["", ""]]);

    // When
    wrapper.instance().removeIngredient();

    // Then
    expect(wrapper.state("ingredientsAmount")).toEqual(0);
    expect(wrapper.state("ingredients")).toEqual([]);
  });

  test("showSteps() should show a user defined amount of input elements", () => {
    const wrapper = shallow(<AddRecipeForm />);
    // spy on method "showSteps()"
    const spy = jest.spyOn(wrapper.instance(), "showSteps");

    // Given
    expect(wrapper.state("stepsAmount")).toEqual(0);
    expect(wrapper.state("steps")).toEqual([]);
    expect(wrapper.find(".step").length).toEqual(0);

    // When
    wrapper.instance().addStep();

    // Then
    expect(wrapper.state("stepsAmount")).toEqual(1);
    expect(wrapper.state("steps")).toEqual([""]);
    expect(wrapper.find(".step").length).toEqual(1);
    expect(spy).toHaveBeenCalled();
  });

  test("updateStep() should update the steps array", () => {
    const wrapper = shallow(<AddRecipeForm />);

    // Given
    expect(wrapper.state("steps")).toEqual([]);

    // When
    wrapper.instance().addStep();

    // Then
    expect(wrapper.state("steps")).toEqual([""]);

    // When
    wrapper.instance().updateStep("0", "step description");

    // Then
    expect(wrapper.state("steps")).toEqual(["step description"]);
  });

  test("addStep() should change the state of 'stepsAmount' and 'steps'", () => {
    const wrapper = shallow(<AddRecipeForm />);

    // Given
    expect(wrapper.state("stepsAmount")).toEqual(0);
    expect(wrapper.state("steps")).toEqual([]);

    // When
    wrapper.instance().addStep();

    // Then
    expect(wrapper.state("stepsAmount")).toEqual(1);
    expect(wrapper.state("steps")).toEqual([""]);
  });

  test("removeStep() should change the state of 'stepsAmount' and 'steps'", () => {
    const wrapper = shallow(<AddRecipeForm />);

    // Given
    expect(wrapper.state("stepsAmount")).toEqual(0);
    expect(wrapper.state("steps")).toEqual([]);

    // When
    wrapper.instance().addStep();

    // Then
    expect(wrapper.state("stepsAmount")).toEqual(1);
    expect(wrapper.state("steps")).toEqual([""]);

    // When
    wrapper.instance().removeStep();

    // Then
    expect(wrapper.state("stepsAmount")).toEqual(0);
    expect(wrapper.state("steps")).toEqual([]);
  });

  test("handleImageUpload() should set the state of 'file'", () => {
    const wrapper = shallow(<AddRecipeForm />);
    const file = new File([""], "filename");

    // Given
    expect(wrapper.state("file")).toEqual(undefined);

    // When
    wrapper.instance().handleImageUpload(file);

    // Then
    expect(wrapper.state("file")).toEqual(file);
  });

  test("renders correctly", () => {
    const tree = renderer.create(<AddRecipeForm />).toJSON();
    expect(tree).toMatchSnapshot();
  });

  //TODO: Save Recipe
});
