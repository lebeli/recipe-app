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
            value="fruehstueck"
            selected={params.fruehstueck}
            updateState={params.updateState}
          />
        </Box>
        <Box p={1}>
          <FilterCategory
            categoryName="Mittagessen"
            value="mittagessen"
            selected={params.mittagessen}
            updateState={params.updateState}
          />
        </Box>
        <Box p={1}>
          <FilterCategory
            categoryName="Abendessen"
            value="abendessen"
            selected={params.abendessen}
            updateState={params.updateState}
          />
        </Box>
      </Box>
      <Box display="flex" justifyContent="center" m={1} p={1}>
        <Box p={1}>
          <FilterCategory
            categoryName="Vegetarisch"
            value="vegetarisch"
            selected={params.vegetarisch}
            updateState={params.updateState}
          />
        </Box>
        <Box p={1}>
          <FilterCategory
            categoryName="Vegan"
            value="vegan"
            selected={params.vegan}
            updateState={params.updateState}
          />
        </Box>
        <Box p={1}>
          <FilterCategory
            categoryName="Schnell"
            value="schnell"
            selected={params.schnell}
            updateState={params.updateState}
          />
        </Box>
        <Box p={1}>
          <FilterCategory
            categoryName="Ich habe Zeit"
            value="ich_habe_zeit"
            selected={params.ich_habe_zeit}
            updateState={params.updateState}
          />
        </Box>
      </Box>
    </div>
  );
}

export default ToggleButtons;
