import React, { Component } from 'react';
import ExpansionPanel from '@material-ui/core/ExpansionPanel';
import ExpansionPanelSummary from '@material-ui/core/ExpansionPanelSummary';
import ExpansionPanelDetails from '@material-ui/core/ExpansionPanelDetails';
import Typography from '@material-ui/core/Typography';

class Filter extends Component {
  render() {
    return (
      <div className="Filter">
        <h3>Suche eingrenzen</h3>
        <ExpansionPanel>
          <ExpansionPanelSummary>
            <Typography>Suche eingrenzen</Typography>
          </ExpansionPanelSummary>
          <ExpansionPanelDetails>
            <Typography>Lorem ipsum dolor sit amet</Typography>
          </ExpansionPanelDetails>
        </ExpansionPanel>
      </div>
    );
  }
}

export default Filter;
