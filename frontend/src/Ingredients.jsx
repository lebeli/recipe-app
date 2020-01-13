import React, { Component } from "react";
import "./Details.scss";

function Ingredients(params) {
  const items = params.ingredients.map(item => {
    return <li>{item}</li>;
  });

  return (
    <div className="Ingredients">
      <h4>ZUTATEN</h4>
      <ul>{items}</ul>
    </div>
  );
}

export default Ingredients;
