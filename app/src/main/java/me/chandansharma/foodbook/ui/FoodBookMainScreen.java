package me.chandansharma.foodbook.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import me.chandansharma.foodbook.R;
import me.chandansharma.foodbook.adapter.RecipeListAdapter;
import me.chandansharma.foodbook.model.Recipe;
import me.chandansharma.foodbook.model.RecipeIngredients;
import me.chandansharma.foodbook.model.RecipeSteps;
import me.chandansharma.foodbook.utils.RecipeDetails;
import me.chandansharma.foodbook.utils.VolleySingleton;

public class FoodBookMainScreen extends AppCompatActivity {

    /**
     * Declaration of the ArrayList of the Model Recipe,
     * Recipe Ingredients, Recipe Steps
     */
    private ArrayList<Recipe> mRecipes = new ArrayList<>();

    private RecipeListAdapter mRecipeListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_book_main_screen);

        /**
         * Helper Function to retrieve the data from the internet
         */
        getRecipeListData(RecipeDetails.RECIPE_URL);
        float smallestWidth = getSmallestWidth();
        mRecipeListAdapter = new RecipeListAdapter(this, mRecipes);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_recipe_list);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE
                || smallestWidth > 600)
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        else if (smallestWidth >= 480)
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        else
            recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        recyclerView.setAdapter(mRecipeListAdapter);
    }

    private void getRecipeListData(String recipeUrl) {

        //Tag used to cancel request
        String jsonArrayTag = "jsonArrayTag";

        JsonArrayRequest recipeJsonArray = new JsonArrayRequest(recipeUrl,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        /**
                         * Parse the JSON Array and store data to data models
                         */
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                /**
                                 * ArrayList of RecipeIngredients and RecipeSteps
                                 */

                                ArrayList<RecipeIngredients> mRecipeIngredients = new ArrayList<>();
                                ArrayList<RecipeSteps> mRecipeSteps = new ArrayList<>();

                                JSONObject singleRecipeJsonObject = response.getJSONObject(i);
                                int recipeId = singleRecipeJsonObject.getInt("id");
                                String recipeName = singleRecipeJsonObject
                                        .getString("name");
                                String recipeImageThumbnailUrl = singleRecipeJsonObject
                                        .getString("image");

                                /**
                                 * fetching Single Recipe Ingredients Details
                                 */
                                JSONArray singleRecipeIngredientsJsonArray = singleRecipeJsonObject
                                        .getJSONArray("ingredients");

                                for (int j = 0; j < singleRecipeIngredientsJsonArray.length(); j++) {
                                    JSONObject singleRecipeIngredientsJsonObject =
                                            singleRecipeIngredientsJsonArray.getJSONObject(j);

                                    RecipeIngredients singleRecipeIngredients =
                                            new RecipeIngredients(
                                                    singleRecipeIngredientsJsonObject
                                                            .getString("ingredient"),
                                                    singleRecipeIngredientsJsonObject
                                                            .getString("measure"),
                                                    singleRecipeIngredientsJsonObject
                                                            .getDouble("quantity")
                                            );
                                    mRecipeIngredients.add(singleRecipeIngredients);
                                }

                                /**
                                 * fetching Single Recipe Steps Details
                                 */

                                JSONArray singleRecipeStepsJsonArray = singleRecipeJsonObject
                                        .getJSONArray("steps");

                                for (int j = 0; j < singleRecipeStepsJsonArray.length(); j++) {
                                    JSONObject singleRecipeStepsJsonObject =
                                            singleRecipeStepsJsonArray.getJSONObject(j);

                                    RecipeSteps singleRecipeSteps =
                                            new RecipeSteps(
                                                    singleRecipeStepsJsonObject
                                                            .getString("shortDescription"),
                                                    singleRecipeStepsJsonObject
                                                            .getString("description"),
                                                    singleRecipeStepsJsonObject
                                                            .getString("videoURL"),
                                                    singleRecipeStepsJsonObject
                                                            .getString("thumbnailURL")
                                            );
                                    mRecipeSteps.add(singleRecipeSteps);
                                }
                                mRecipes.add(new Recipe(recipeId, recipeName, recipeImageThumbnailUrl,
                                        mRecipeIngredients, mRecipeSteps));
                                mRecipeListAdapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        //Add RecipeList Data request to RequestQueue
        VolleySingleton.getInstance().addToRequestQueue(recipeJsonArray, jsonArrayTag);
    }

    private float getSmallestWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int widthInPixel = displayMetrics.widthPixels;
        int heightInPixel = displayMetrics.heightPixels;

        float scaleFactor = displayMetrics.density;

        float widthInDp = widthInPixel / scaleFactor;
        float heightInDp = heightInPixel / scaleFactor;

        return Math.min(widthInDp, heightInDp);
    }
}

