import React from "react";
import { shallow, mount } from "enzyme";
import renderer from "react-test-renderer";
import Tags from "../Tags";

describe("<Tags />", () => {
  test("should render elements", () => {
    const wrapper = mount(
      <Tags
        ingredients={[
          "Gurke",
          "Tomate",
          "Zwiebel",
          "Knoblauch",
          "Ziegenkäse",
          "Gouda",
          "Erdnüsse"
        ]}
        chosen_ingredients={[]}
      />
    );
    expect(wrapper.find(".Tags"));
    expect(wrapper.find(".tag").length).toEqual(5);
  });

  test("renders correctly", () => {
    const tree = renderer
      .create(
        <Tags
          ingredients={[
            "Gurke",
            "Tomate",
            "Zwiebel",
            "Knoblauch",
            "Ziegenkäse",
            "Gouda",
            "Erdnüsse"
          ]}
          chosen_ingredients={[]}
        />
      )
      .toJSON();
    expect(tree).toMatchSnapshot();
  });
});
