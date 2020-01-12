import React from "react";
import AddRecipe from "../AddRecipe";
import { shallow, mount } from "enzyme";
import AddRecipeForm from "../AddRecipeForm";

describe("<AddRecipe />", () => {
  test("should render elements", () => {
    const wrapper = shallow(<AddRecipe />);
    expect(wrapper.find("#add_recipe_description"));
    expect(wrapper.find("#add_recipe_image_container"));
    expect(wrapper.find("#open_form_button"));
  });

  test("button onClick should change state", () => {
    const wrapper = shallow(<AddRecipe />);
    const button = wrapper.find("#open_form_button");
    expect(wrapper.state("showForm")).toEqual(false);
    button.simulate("click");
    expect(wrapper.state("showForm")).toEqual(true);
  });

  test("button onClick should open Form", () => {
    const wrapper = mount(<AddRecipe />);
    expect(wrapper.find(AddRecipeForm).length).toEqual(0);
    wrapper
      .find("#open_form_button")
      .at(0)
      .simulate("click");
    expect(wrapper.find(AddRecipeForm).length).toEqual(1);
  });

  test("closeForm should change state", () => {
    const wrapper = shallow(<AddRecipe />);
    const instance = wrapper.instance();
    expect(wrapper.state("showForm")).toEqual(false);
    instance.openForm();
    expect(wrapper.state("showForm")).toEqual(true);
    instance.closeForm();
    expect(wrapper.state("showForm")).toEqual(false);
  });
});
