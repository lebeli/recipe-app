import React from "react";
import { shallow, mount } from "enzyme";
import Filter from "../Filter";
import renderer from "react-test-renderer";

describe("<Filter />", () => {
  test("should render elements", () => {
    const wrapper = mount(<Filter />);
    expect(wrapper.find(".ToggleButtons"));
    expect(wrapper.find(".Tags"));
  });

  test("calculateActiveFilters() should return a description of how many filters are active", () => {
    const wrapper = shallow(<Filter />);

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
    const wrapper = shallow(<Filter />);

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
