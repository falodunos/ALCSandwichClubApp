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

    public static final String KEY_NAME = "name";
    public static final String KEY_MAIN_NAME = "mainName";
    public static final String KEY_ALSO_KNOWN_AS = "alsoKnownAs";
    public static final String KEY_INGREDIENTS = "ingredients";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_PLACE_OF_ORIGIN = "placeOfOrigin";

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();
        List <String> alsoKnownAsList, ingredientList;
        try {
            String notApplicable = "N/A";
            JSONObject jsonObj = new JSONObject(json);
            JSONObject jsonNameObj = jsonObj.getJSONObject(KEY_NAME);

            String mainName = jsonNameObj.optString(KEY_MAIN_NAME);
            sandwich.setMainName(mainName != "" ? mainName : notApplicable);

            JSONArray alsoKnownAs = jsonNameObj.optJSONArray(KEY_ALSO_KNOWN_AS);
            alsoKnownAsList = ingredientList = new ArrayList<>();
            alsoKnownAsList.clear();
            for(int i = 0; i < alsoKnownAs.length(); i++){
                alsoKnownAsList.add(alsoKnownAs.getString(i));
            }

            if (alsoKnownAsList.size() == 0) {
                alsoKnownAsList.add(notApplicable);
            }
            sandwich.setAlsoKnownAs(alsoKnownAsList);

            String description = jsonObj.optString(KEY_DESCRIPTION);
            sandwich.setDescription(description != "" ? description : notApplicable);

            sandwich.setImage(jsonObj.optString(KEY_IMAGE));

            String placeOfOrigin = jsonObj.optString(KEY_PLACE_OF_ORIGIN);
            sandwich.setPlaceOfOrigin(placeOfOrigin != "" ? placeOfOrigin : notApplicable);

            JSONArray ingredients = jsonObj.optJSONArray(KEY_INGREDIENTS);
            ingredientList.clear();
            for(int i = 0; i < ingredients.length(); i++){
                ingredientList.add(ingredients.getString(i));
            }

            if (ingredientList.size() == 0) {
                ingredientList.add(notApplicable);
            }
            sandwich.setIngredients(ingredientList);

            return sandwich;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
