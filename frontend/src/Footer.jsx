import React, { Component } from "react";
import "./Footer.scss";
import { Box } from "@material-ui/core";

class Footer extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div className="Footer">
        <Box display="flex" justifyContent="center">
          <p>
            <a href="#">Impressum</a> | <a href="#">Datenschutz</a>
          </p>
        </Box>
      </div>
    );
  }
}

export default Footer;
