import React from "react";
import ArrowBackIcon from "@material-ui/icons/ArrowBack";
import Button from "@material-ui/core/Button";

function BackButton({ handleGoBack }) {
  return (
    <Button onClick={handleGoBack}>
      <ArrowBackIcon />
      Zurück
    </Button>
  );
}

export default BackButton;
