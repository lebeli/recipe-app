import React, { Component } from 'react';
import RecommenderImage from './RecommenderImage';
import RecommenderHeader from './RecommenderHeader';

function Recommender() {
  return(
    <div className="recommender" >
      <RecommenderHeader />
      <RecommenderImage />
    </div>
  );
}

export default Recommender;
