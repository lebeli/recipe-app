import React from "react";
import { shallow } from "enzyme";
import renderer from "react-test-renderer";
import Footer from "../Footer";

describe("<Footer />", () => {
  test("should render elements", () => {
    const wrapper = shallow(<Footer />);
    expect(wrapper.find(".Footer"));
  });

  test("renders correctly", () => {
    const tree = renderer.create(<Footer />).toJSON();
    expect(tree).toMatchSnapshot();
  });
});
