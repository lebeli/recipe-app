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

  test("photo data is passed to child component", () => {
    const wrapper = mount(<Recommender image="../images/lasagne.jpg" />);
    const image = wrapper.find("img");
    expect(image.prop("src")).toEqual("../images/lasagne.jpg");
  });
});
