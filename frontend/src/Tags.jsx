import React from "react";
import TextField from "@material-ui/core/TextField";
import Autocomplete from "@material-ui/lab/Autocomplete";
import "./Tags.scss";

function Tags(params) {
  return (
    <div className="Tags">
      <Autocomplete
        multiple
        options={params.ingredients}
        getOptionLabel={ingredient => ingredient}
        filterSelectedOptions
        onChange={(_, tags) => {
          params.updateState("chosen_ingredients", tags);
        }}
        renderInput={tags => (
          <TextField
            {...tags}
            className="tag"
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

export default Tags;
