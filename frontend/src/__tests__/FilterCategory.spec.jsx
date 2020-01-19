import React from "react";
import { mount } from "enzyme";
import renderer from "react-test-renderer";
import FilterCategory from "../FilterCategory";

describe("<FilterCategory />", () => {
  test("should render elements", () => {
    const wrapper = mount(
      <FilterCategory
        categoryName="Frühstück"
        value="breakfast"
        selected={false}
      />
    );
    expect(wrapper.find(".FilterCategory"));
  });

  test("renders correctly", () => {
    const tree = renderer
      .create(
        <FilterCategory
          categoryName="Frühstück"
          value="breakfast"
          selected={false}
        />
      )
      .toJSON();
    expect(tree).toMatchSnapshot();
  });
});
