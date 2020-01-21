import React from "react";
import { shallow, mount } from "enzyme";
import renderer from "react-test-renderer";
import Details from "../Details";

describe("Recommender", () => {
  test("should render elementes", () => {
    const wrapper = mount(<Details />);
    expect(wrapper.find(".RecommenderImage"));
    expect(wrapper.find(".BackButton"));
    expect(wrapper.find(".RecommenderHeader"));
    expect(wrapper.find(".Ingredients"));
    expect(wrapper.find(".Instructions"));
  });

  test("renders correctly", () => {
    const tree = renderer.create(<Details />).toJSON();
    expect(tree).toMatchSnapshot();
  });
});
