import React from 'react';

function RecommenderHeader() {

  const style = {
    position: 'absolute',
    top: '0',
    left: '0',
    background: 'rgba(255, 255, 255, 0.5)',
    display: 'flex',
    width: '100%',
    height: '10%'
    //justify-content: 'center',
    //align-items: 'center',
  };

  const contentStyle = {
    margin: '5px'
  };

  return (
    <div className="header" style={style}>
      <div className="headerTitel" style={contentStyle}>Lasagne</div>
      <div className="headerTime" style={contentStyle}>45min</div>
    </div>
  );
}

export default RecommenderHeader;
