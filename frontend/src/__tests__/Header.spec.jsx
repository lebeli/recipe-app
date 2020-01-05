import React from "react";
import { shallow } from "enzyme";
import renderer from "react-test-renderer";
import Header from "../Header";

describe("<Header />", () => {
  test("should render elements", () => {
    const wrapper = shallow(<Header />);
    expect(wrapper.find(".Header"));
  });

  test("renders correctly", () => {
    const tree = renderer.create(<Header />).toJSON();
    expect(tree).toMatchSnapshot();
  });
});
