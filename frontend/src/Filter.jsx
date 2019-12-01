import React, { Component } from 'react';
import ExpansionPanel from '@material-ui/core/ExpansionPanel';
import ExpansionPanelSummary from '@material-ui/core/ExpansionPanelSummary';
import ExpansionPanelDetails from '@material-ui/core/ExpansionPanelDetails';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import Typography from '@material-ui/core/Typography';
import Box from '@material-ui/core/Box';
import ToggleButton from '@material-ui/lab/ToggleButton';
import TextField from '@material-ui/core/TextField';
import Autocomplete from '@material-ui/lab/Autocomplete';
import './Filter.scss';

class Filter extends Component {
  constructor(params) {
    super(params);
    this.state = {
      ingredients: params.ingredients,
    };
  }

  render() {
    const { ingredients } = this.state;
    return (
      <div className="Filter">
        <ExpansionPanel square id="filter-panel">
          <Box display="flex" justifyContent="center" m={1} p={1}>
            <Box p={1}>
              <ExpansionPanelSummary
                expandIcon={<ExpandMoreIcon id="search-expand-icon" />}
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
                <Typography component="span">
                  <ToggleButtons />
                  <Tags ingredients={ingredients} />
                </Typography>
              </ExpansionPanelDetails>
            </Box>
          </Box>
        </ExpansionPanel>
      </div>
    );
  }
}

function ToggleButtons() {
  return (
    <div className="ToggleButtons">
      <Box display="flex" justifyContent="center" m={1} p={1}>
        <Box p={1}>
          <FilterCategory categoryName="Frühstück" value="fruehstueck" />
        </Box>
        <Box p={1}>
          <FilterCategory categoryName="Mittagessen" value="mittagessen" />
        </Box>
        <Box p={1}>
          <FilterCategory categoryName="Abendessen" value="abendessen" />
        </Box>
      </Box>
      <Box display="flex" justifyContent="center" m={1} p={1}>
        <Box p={1}>
          <FilterCategory categoryName="Vegetarisch" value="vegetarisch" />
        </Box>
        <Box p={1}>
          <FilterCategory categoryName="Vegan" value="vegan" />
        </Box>
        <Box p={1}>
          <FilterCategory categoryName="Schnell" value="schnell" />
        </Box>
        <Box p={1}>
          <FilterCategory categoryName="Ich habe Zeit" value="ich-habe-zeit" />
        </Box>
      </Box>
    </div>
  );
}

function FilterCategory(params) {
  const [selected, setSelected] = React.useState(false);
  return (
    <div className="FilterCategory">
      <ToggleButton
        value={params.value}
        id={params.value}
        selected={selected}
        onChange={() => {
          setSelected(!selected);
        }}
      >
        {params.categoryName}
      </ToggleButton>
    </div>
  );
}

function Tags(params) {
  return (
    <div className="Tags">
      <Autocomplete
        multiple
        options={params.ingredients}
        getOptionLabel={(ingredient) => ingredient}
        filterSelectedOptions
        renderInput={(tags) => (
          <TextField
            {...tags}
            variant="outlined"
            placeholder="Nach Zutaten suchen..."
            margin="normal"
            fullWidth
          />
        )}
      />
    </div>
  );
}

export default Filter;
