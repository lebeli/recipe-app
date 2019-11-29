import React, { Component } from 'react';
import ExpansionPanel from '@material-ui/core/ExpansionPanel';
import ExpansionPanelSummary from '@material-ui/core/ExpansionPanelSummary';
import ExpansionPanelDetails from '@material-ui/core/ExpansionPanelDetails';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import Typography from '@material-ui/core/Typography';
import Box from '@material-ui/core/Box';
import ToggleButton from '@material-ui/lab/ToggleButton';
import './Filter.scss';

class Filter extends Component {
  render() {
    return (
      <div className="Filter">
        <ExpansionPanel square id="filter-panel">
          <Box display="flex" justifyContent="center" m={1} p={1}>
            <Box p={1}>
              <ExpansionPanelSummary
                expandIcon={<ExpandMoreIcon bgcolor="white" />}
                aria-controls="filter-panel-content"
                id="filter-panel-header-summery"
              >
                <Typography id="filter-header">
                  Suche eingrenzen
                </Typography>
              </ExpansionPanelSummary>
            </Box>
          </Box>
          <Box display="flex" justifyContent="center" m={1} p={1}>
            <Box p={1}>
              <ExpansionPanelDetails>
                <Typography>
                  <Box display="flex" justifyContent="center" m={1} p={1}>
                    <Box p={1}>
                      <FilterCategory categoryOne="Frühstück" />
                    </Box>
                    <Box p={1}>
                      <FilterCategory categoryOne="Mittagessen" />
                    </Box>
                    <Box p={1}>
                      <FilterCategory categoryOne="Abendessen" />
                    </Box>
                  </Box>
                </Typography>
              </ExpansionPanelDetails>
            </Box>
          </Box>
        </ExpansionPanel>
      </div>
    );
  }
}

function FilterCategory(params) {
  const selected = false;

  function setSelected(s) {
    return s;
  }

  return (
    <div className="FilterCategory">
      <ToggleButton
        value="check"
        selected={selected}
        onChange={() => {
          setSelected(!selected);
        }}
      >
        {params.categoryOne}
      </ToggleButton>
    </div>
  );
}

export default Filter;
