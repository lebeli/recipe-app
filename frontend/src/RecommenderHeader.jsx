import React from 'react';

function RecommenderHeader() {

  const style = {
    position: 'absolute',
    top: '0',
    left: '0',
    background: 'rgba(255, 255, 255, 0.5)',
  };

  return (
    <div className="headerTitle" height="150" width="500" style={style}>Lasagne</div>
  );
}

export default RecommenderHeader;
