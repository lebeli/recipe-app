import React from 'react';
import RecommenderImage from './RecommenderImage';
import RecommenderHeader from './RecommenderHeader';
import RecommenderReloadButton from './RecommenderReloadButton';

function Recommender() {
  const containerStyle = {
    position: 'relative',
    height: '100%',
    width: '100%',
  };

  return (
    <div className="recommender" id="container" style={containerStyle}>
      <RecommenderHeader />
      <RecommenderReloadButton />
      <RecommenderImage />
    </div>
  );
}

export default Recommender;
