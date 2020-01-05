import React from "react";
import shallow from "enzyme";
import AddRecipeForm from "../AddRecipeForm";

describe("<AddRecipeForm />", () => {
  test("updateStateParameters() should update different state parameters", () => {
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
});
