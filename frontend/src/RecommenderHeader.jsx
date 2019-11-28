import React from 'react';

function RecommenderHeader() {

  var style = {
    position: 'absolute',
    top: '0',
    left: '0',
    background: 'red'
  };

  return (
    <div className="headerTitle" height="150" width="500" style={style}>Lasagne</div>
  );
}

export default RecommenderHeader;
