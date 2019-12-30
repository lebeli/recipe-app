import React from "react";
import AddRecipe from "../AddRecipe";
import { shallow, mount } from "enzyme";

describe("<AddRecipe />", () => {
  let wrapper = mount(<AddRecipe />);
  //let wrapper = mount(<AddRecipe showForm={false} />);
  test("openForm", () => {
    expect(wrapper.find("#add_recipe_description"));
  });
});
