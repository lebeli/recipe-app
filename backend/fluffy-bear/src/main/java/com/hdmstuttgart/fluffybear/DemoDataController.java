package com.hdmstuttgart.fluffybear;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdmstuttgart.fluffybear.model.Recipe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class DemoDataController {

    private static DemoDataController instance;

    private DemoDataController() {}

    public static synchronized DemoDataController getInstance () {
        if (DemoDataController.instance == null) {
            DemoDataController.instance = new DemoDataController();
        }
        return DemoDataController.instance;
    }

    public List<Recipe> getDemoRecipes() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("demo_recipes.json");
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObjectMapper mapper = new ObjectMapper();
        List<Recipe> demoReceipts = null;
        try {
            demoReceipts = mapper.readValue(resultStringBuilder.toString(), new TypeReference<List<Recipe>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return demoReceipts;
    }
}
