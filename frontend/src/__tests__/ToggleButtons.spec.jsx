import React from "react";
import { shallow, mount } from "enzyme";
import renderer from "react-test-renderer";
import ToggleButtons from "../ToggleButtons";

describe("<ToggleButtons />", () => {
  test("should render elements", () => {
    const wrapper = mount(
      <ToggleButtons
        breakfast={false}
        lunch={false}
        dinner={false}
        vegetarian={false}
        vegan={false}
        fast={false}
        i_have_time={false}
        chosen_ingredients={[]}
      />
    );

    expect(wrapper.find(".ToggleButtons").length).toEqual(1);
    expect(wrapper.find(".FilterCategory").length).toEqual(7);
  });

  test("renders correctly", () => {
    const tree = renderer
      .create(
        <ToggleButtons
          breakfast={false}
          lunch={false}
          dinner={false}
          vegetarian={false}
          vegan={false}
          fast={false}
          i_have_time={false}
          chosen_ingredients={[]}
        />
      )
      .toJSON();
    expect(tree).toMatchSnapshot();
  });
});
