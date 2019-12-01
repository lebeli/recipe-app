import React from 'react';
import Fab from '@material-ui/core/Fab';
import ReplayIcon from '@material-ui/icons/Replay';
import './Recommender.scss';

function RecommenderReloadButton() {
  return (
    <Fab aria-label="reload" id="ReloadFab">
      <ReplayIcon />
    </Fab>
  );
}

export default RecommenderReloadButton;
