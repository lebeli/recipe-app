import React from 'react';
// import teaserImage from './resources/toast.jpg';
import teaserImage from './resources/wolf.jpg';

function RecommenderImage() {
  return (
    <div className="teaserImage">
      <img src={teaserImage} alt="finished dish" width="100%" height="100%" />
    </div>
  );
}

export default RecommenderImage;
