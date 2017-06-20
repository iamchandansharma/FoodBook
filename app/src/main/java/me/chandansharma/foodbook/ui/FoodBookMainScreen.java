package me.chandansharma.foodbook.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

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
    private HashMap<Integer, ArrayList<RecipeIngredients>> mRecipeIngredientsMap =
            new HashMap<>();
    private ArrayList<RecipeIngredients> mRecipeIngredients = new ArrayList<>();
    private ArrayList<RecipeSteps> mRecipeSteps = new ArrayList<>();

    private RecipeListAdapter mRecipeListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_book_main_screen);

        /**
         * Helper Function to retrieve the data from the internet
         */
        getRecipeListData(RecipeDetails.RECIPE_URL);

        mRecipeListAdapter = new RecipeListAdapter(this, mRecipes);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_recipe_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
                                mRecipeIngredients.clear();
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
                                mRecipeIngredientsMap.put(recipeId, mRecipeIngredients);
                                mRecipes.add(new Recipe(recipeId, recipeName, recipeImageThumbnailUrl,
                                        mRecipeIngredients, mRecipeSteps, mRecipeIngredientsMap));
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
}

