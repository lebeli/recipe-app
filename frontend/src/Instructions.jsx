import React, { Component } from "react";
import "./Details.scss";

function Instructions(params) {
  const items = params.instructions.map(item => {
    return <li>{item}</li>;
  });

  return (
    <div className="Instructions">
      <ol>{items}</ol>
    </div>
  );
}

export default Instructions;
