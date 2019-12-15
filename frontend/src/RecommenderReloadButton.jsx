import React from "react";
import Fab from "@material-ui/core/Fab";
import ReplayIcon from "@material-ui/icons/Replay";
import "./Recommender.scss";

function RecommenderReloadButton({ onClick }) {
  return (
    <Fab aria-label="reload" id="ReloadFab" onClick={onClick}>
      <ReplayIcon />
    </Fab>
  );
}

export default RecommenderReloadButton;
