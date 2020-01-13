import React from "react";
import AccessTimeIcon from "@material-ui/icons/AccessTime";
import "./Recommender.scss";

function RecommenderHeader(params) {
  return (
    <div className="Header">
      <div className="HeaderContent">{params.recipeName}</div>
      <div className="HeaderContent">
        <AccessTimeIcon />
        {params.time}
      </div>
    </div>
  );
}

export default RecommenderHeader;
