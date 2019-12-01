import React, { Component } from 'react';
import './Header.scss';
import Box from '@material-ui/core/Box';

import img from './images/foodbaby.png';

class Header extends Component {
  render() {
    return (
      <div className="Header">
        <Box display="flex" justifyContent="center" m={1} p={1}>
          <img id="logo" src={img} alt="foodbaby logo" />
        </Box>
        <Box display="flex" justifyContent="center" m={1} p={1}>
          <p id="slogan">Was isst du heute?</p>
        </Box>
      </div>
    );
  }
}

export default Header;
