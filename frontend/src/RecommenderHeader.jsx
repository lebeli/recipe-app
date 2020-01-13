import React from "react";
import "./Recommender.scss";

function RecommenderHeader(params) {
  return (
    <div className="Header">
      <div className="HeaderContent">{params.recipeName}</div>
      <div className="HeaderContent">{params.time}</div>
    </div>
  );
}

export default RecommenderHeader;
