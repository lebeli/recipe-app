import React from "react";
import { shallow, mount } from "enzyme";
import Filter from "../Filter";
import renderer from "react-test-renderer";
import "regenerator-runtime";

describe("<Filter />", () => {
  beforeEach(function() {
    global.fetch = jest.fn().mockImplementation(() => {
      var p = new Promise((resolve, reject) => {
        resolve({
          ok: true,
          ingredients: [
            "Gurke",
            "Tomate",
            "Zwiebel",
            "Knoblauch",
            "Ziegenkäse",
            "Gouda",
            "Erdnüsse"
          ],
          json: function() {
            return ingredients;
          }
        });
      });
      return p;
    });
  });

  test("should render elements", async () => {
    const wrapper = mount(<Filter />);
    expect(wrapper.find(".ToggleButtons"));
    expect(wrapper.find(".Tags"));
  });

  test("calculateActiveFilters() should return a description of how many filters are active", () => {
    var mockFunction = () => function mock() {};
    const wrapper = shallow(<Filter updateRecipe={mockFunction} />);

    // Given
    expect(
      wrapper.instance().calculateActiveFilters().props.children[0]
    ).toEqual(0);

    // When
    wrapper.instance().updateState("breakfast", true);

    // Then
    expect(
      wrapper.instance().calculateActiveFilters().props.children[0]
    ).toEqual(1);

    // When
    wrapper.instance().updateState("i_have_time", true);

    // Then
    expect(
      wrapper.instance().calculateActiveFilters().props.children[0]
    ).toEqual(2);
  });

  test("updateState() should update different state attribute", () => {
    var mockFunction = () => function mock() {};
    const wrapper = shallow(<Filter updateRecipe={mockFunction} />);

    // Given
    expect(wrapper.state("fast")).toEqual(false);
    expect(wrapper.state("chosen_ingredients")).toEqual([]);

    // When
    wrapper.instance().updateState("fast", true);
    wrapper.instance().updateState("chosen_ingredients", ["Tomate", "Gurke"]);

    // Then
    expect(wrapper.state("fast")).toEqual(true);
    expect(wrapper.state("chosen_ingredients")).toEqual(["Tomate", "Gurke"]);
  });

  test("renders correctly", () => {
    const tree = renderer.create(<Filter />).toJSON();
    expect(tree).toMatchSnapshot();
  });
});
