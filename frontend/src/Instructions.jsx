import React, { Component } from "react";
import "./Details.scss";

function Instructions(params) {
  const items = params.instructions.map((item, i) => {
    return <li key={i}>{item}</li>;
  });

  return (
    <div className="Instructions">
      <ol>{items}</ol>
    </div>
  );
}

export default Instructions;
