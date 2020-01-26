import React from "react";
import ToggleButton from "@material-ui/lab/ToggleButton";
import "./FilterCategory.scss";

function FilterCategory(params) {
  const [selected, setSelected] = React.useState(false);

  return (
    <div className="FilterCategory">
      <ToggleButton
        value={params.value}
        selected={selected}
        disableFocusRipple
        onChange={() => {
          params.updateState(params.value, !params.selected);
          setSelected(!params.selected);
        }}
      >
        {params.categoryName}
      </ToggleButton>
    </div>
  );
}

export default FilterCategory;
