import React, { Component } from 'react';
import teaserImage from './resources/toast.jpg';

function Recommender() {
  return (
    <div className="teaserImage">
      <img src={teaserImage} width="50" height="150" />
    </div>
  );
}

export default Recommender;
