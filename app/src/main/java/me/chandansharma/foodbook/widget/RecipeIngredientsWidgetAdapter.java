package me.chandansharma.foodbook.widget;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import me.chandansharma.foodbook.R;
import me.chandansharma.foodbook.model.RecipeIngredients;
import me.chandansharma.foodbook.utils.RecipeDetails;
import me.chandansharma.foodbook.utils.VolleySingleton;

/**
 * Created by iamcs on 2017-06-22.
 */

public class RecipeIngredientsWidgetAdapter implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext;
    private ArrayList<RecipeIngredients> mRecipeIngredients = new ArrayList<>();

    //
    //Constructor
    public RecipeIngredientsWidgetAdapter(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate() {
        mRecipeIngredients = getRecipeIngredientsData(RecipeDetails.RECIPE_URL);
    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mRecipeIngredients.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {

        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(),
                R.layout.recipe_ingrediets_list_item_widget);

        remoteViews.setTextViewText(R.id.tv_recipe_ingredients_name,
                mRecipeIngredients.get(position).getRecipeIngredientsName());
        remoteViews.setTextViewText(R.id.tv_recipe_ingredients_quantity,
                String.valueOf(mRecipeIngredients.get(position).getRecipeIngredientsQuantity()));
        remoteViews.setTextViewText(R.id.tv_recipe_ingredients_measure,
                mRecipeIngredients.get(position).getRecipeIngredientsMeasure());

        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    private ArrayList<RecipeIngredients> getRecipeIngredientsData(String recipeUrl) {
        //Tag used to cancel request
        String jsonArrayTag = "jsonArrayTag";

        JsonArrayRequest recipeJsonArray = new JsonArrayRequest(recipeUrl,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        /**
                         * Parse the JSON Array and store data to data models
                         */
                        for (int i = 0; i < 1; i++) {
                            try {

                                JSONObject singleRecipeJsonObject = response.getJSONObject(i);

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

        return mRecipeIngredients;
    }
}
