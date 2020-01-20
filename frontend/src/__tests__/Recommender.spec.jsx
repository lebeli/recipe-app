import React from "react";
import { shallow, mount } from "enzyme";
import renderer from "react-test-renderer";
import Recommender from "../Recommender";

describe("Recommender", () => {
  test("should render elementes", () => {
    const wrapper = mount(<Recommender />);
    expect(wrapper.find(".RecommenderHeader"));
    expect(wrapper.find(".RecommenderImage"));
    expect(wrapper.find(".RecommenderReloadButton"));
  });

  test("renders correctly", () => {
    const tree = renderer.create(<Recommender />).toJSON();
    expect(tree).toMatchSnapshot();
  });
});
