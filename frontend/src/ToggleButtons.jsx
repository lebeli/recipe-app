import React from "react";
import Box from "@material-ui/core/Box";
import FilterCategory from "./FilterCategory";

import "./Filter.scss";

function ToggleButtons(params) {
  return (
    <div className="ToggleButtons">
      <Box display="flex" justifyContent="center" m={1} p={1}>
        <Box p={1}>
          <FilterCategory
            categoryName="Frühstück"
            value="breakfast"
            selected={params.breakfast}
            updateState={params.updateState}
          />
        </Box>
        <Box p={1}>
          <FilterCategory
            categoryName="Mittagessen"
            value="lunch"
            selected={params.lunch}
            updateState={params.updateState}
          />
        </Box>
        <Box p={1}>
          <FilterCategory
            categoryName="Abendessen"
            value="dinner"
            selected={params.dinner}
            updateState={params.updateState}
          />
        </Box>
      </Box>
      <Box display="flex" justifyContent="center" m={1} p={1}>
        <Box p={1}>
          <FilterCategory
            categoryName="vegetarisch"
            value="vegetarian"
            selected={params.vegetarian}
            updateState={params.updateState}
          />
        </Box>
        <Box p={1}>
          <FilterCategory
            categoryName="vegan"
            value="vegan"
            selected={params.vegan}
            updateState={params.updateState}
          />
        </Box>
        <Box p={1}>
          <FilterCategory
            categoryName="schnell"
            value="schnell"
            selected={params.schnell}
            updateState={params.updateState}
          />
        </Box>
        <Box p={1}>
          <FilterCategory
            categoryName="ich habe Zeit"
            value="i_have_time"
            selected={params.i_have_time}
            updateState={params.updateState}
          />
        </Box>
      </Box>
    </div>
  );
}

export default ToggleButtons;
