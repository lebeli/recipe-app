import React, { Component } from 'react';
//import teaserImage from './resources/toast.jpg';
import teaserImage from './resources/wolf.jpg';

function RecommenderImage() {
  return (
    <div className="teaserImage" >
      <img src={teaserImage} width="50" height="150" />
    </div>
  );
}

export default RecommenderImage;
