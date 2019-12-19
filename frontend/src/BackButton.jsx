import React, { Component } from "react";
import Button from "@material-ui/core/Button";

function BackButton({ handleGoBack }) {
  return <Button onClick={handleGoBack}>Zurück</Button>;
}

export default BackButton;
