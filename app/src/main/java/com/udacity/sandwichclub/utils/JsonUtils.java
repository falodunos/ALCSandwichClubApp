package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.*;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();
        List <String> alsoKnownAs, ingredients;
        try {
            String[] notApplication= {"N/A"};
            JSONObject jsonObj = new JSONObject(json);
            JSONObject jsonNameObj = new JSONObject(jsonObj.get("name").toString());

            sandwich.setMainName(jsonNameObj.getString("mainName"));

            String[] alsoKnownAsArr = jsonNameObj.getString("alsoKnownAs").split(",");
            alsoKnownAs = Arrays.asList(alsoKnownAsArr.length > 0 ? alsoKnownAsArr : notApplication);
            sandwich.setAlsoKnownAs(alsoKnownAs);

            sandwich.setDescription(jsonObj.getString("description"));
            sandwich.setImage(jsonObj.getString("image"));
            sandwich.setPlaceOfOrigin(jsonObj.getString("placeOfOrigin"));

            String[] ingredientsArr = jsonObj.getString("ingredients").split(",");
            ingredients = Arrays.asList(ingredientsArr.length > 0 ? ingredientsArr : notApplication);
            sandwich.setIngredients(ingredients);

            return sandwich;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
