import React, { Component } from 'react';
import RecommenderImage from './RecommenderImage';
import RecommenderHeader from './RecommenderHeader';

function Recommender() {

  var containerStyle = {
    position: 'relative',
    height: '100%',
    width: '100%',
  };


  return(
    <div className="recommender" id="container" style={containerStyle}>
      <RecommenderHeader />
      <RecommenderImage />
    </div>
  );
}

export default Recommender;
