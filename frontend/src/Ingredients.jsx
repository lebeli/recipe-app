import React, { Component } from "react";
import "./Details.scss";

function Ingredients(params) {
  const items = params.ingredients.map((item, i) => {
    return (
      <div key={i}>
        <span>{item.typeAmount}</span>
        {item.name}
      </div>
    );
  });

  return (
    <div className="Ingredients">
      <h4>ZUTATEN</h4>
      {items}
    </div>
  );
}

export default Ingredients;
