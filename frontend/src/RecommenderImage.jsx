import React, { Component } from 'react';
//import teaserImage from './resources/toast.jpg';
import teaserImage from './resources/wolf.jpg';

function RecommenderImage() {
  return (
    <div className="teaserImage" >
      <img src={teaserImage} width="100%" height="100%" />
    </div>
  );
}

export default RecommenderImage;
