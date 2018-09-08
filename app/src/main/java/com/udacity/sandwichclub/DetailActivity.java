package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    TextView mTextViewMainName;
    TextView mAlsoKnownAs;
    TextView mTextViewDescription;
    TextView mTextViewIngredients;
    TextView mTextViewPlaceOfOrigin;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = findViewById(R.id.image_iv);
        mTextViewMainName = (TextView) findViewById(R.id.main_name_tv);
        mAlsoKnownAs = (TextView) findViewById(R.id.also_known_as_tv);
        mTextViewDescription = (TextView) findViewById(R.id.description);
        mTextViewIngredients = (TextView) findViewById(R.id.ingredients_tv);
        mTextViewPlaceOfOrigin = (TextView) findViewById(R.id.place_of_origin_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);

        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(imageView);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        mTextViewMainName.setText(sandwich.getMainName());
        mAlsoKnownAs.setText(convertListToString(sandwich.getAlsoKnownAs()));
        mTextViewDescription.setText(sandwich.getDescription());
        mTextViewPlaceOfOrigin.setText(sandwich.getPlaceOfOrigin());
        mTextViewIngredients.setText(convertListToString(sandwich.getIngredients()));
    }

    private String convertListToString(List<String> stringList) {
        String csv = "";
        if (stringList != null) {
            for(String item : stringList){
                csv += item + ",";
            }
            csv = csv.substring(0, csv.length() - ",".length());
            return csv;
        }
        return csv;
    }
}
