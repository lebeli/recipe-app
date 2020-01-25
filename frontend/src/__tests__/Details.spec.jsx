import React from "react";
import { shallow, mount } from "enzyme";
import renderer from "react-test-renderer";
import Details from "../Details";
import Instructions from "../Instructions";

describe("Details", () => {
  test("should render elementes", () => {
    const wrapper = mount(
      <Details
        ingredients={[
          {
            name: "flour",
            typeAmount: "300g"
          },
          {
            name: "cucumber",
            typeAmount: "1"
          }
        ]}
        instructions={[
          "Banannaaaaa",
          "Gruuuu!",
          "Halloosn viawhbfiaw hfeiuwbfhbwe diqwbdhbvwei lfhea iugf46griwa zdegksg fuishfiu gfzsgdfjz sfv,jhbs< fhksbc. kjsbdvjk bydskjvf sudhv ousgczs h!!"
        ]}
      />
    );
    expect(wrapper.find(".RecommenderImage"));
    expect(wrapper.find(".BackButton"));
    expect(wrapper.find("#details_header"));
    expect(wrapper.find(".Ingredients"));
    expect(wrapper.find(".Instructions"));
  });

  test("renders correctly", () => {
    const tree = renderer
      .create(
        <Details
          ingredients={[
            {
              name: "flour",
              typeAmount: "300g"
            },
            {
              name: "cucumber",
              typeAmount: "1"
            }
          ]}
          instructions={[
            "Banannaaaaa",
            "Gruuuu!",
            "Halloosn viawhbfiaw hfeiuwbfhbwe diqwbdhbvwei lfhea iugf46griwa zdegksg fuishfiu gfzsgdfjz sfv,jhbs< fhksbc. kjsbdvjk bydskjvf sudhv ousgczs h!!"
          ]}
        />
      )
      .toJSON();
    expect(tree).toMatchSnapshot();
  });
});
