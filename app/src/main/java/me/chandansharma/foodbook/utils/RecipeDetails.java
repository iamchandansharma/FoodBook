package me.chandansharma.foodbook.utils;

import me.chandansharma.foodbook.R;

/**
 * Created by iamcs on 2017-06-10.
 */

public class RecipeDetails {

    /**
     * Recipe Data Url
     */
    public static final String RECIPE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/" +
            "May/59121517_baking/baking.json";

    /**
     * Recipe Steps and Recipe Ingredients Key Value
     */
    public static final String RECIPE_KEY = "recipe";
    public static final String RECIPE_STEPS_KEY = "recipe_steps";
    public static final String RECIPE_INGREDIENTS_KEY = "recipe_ingredients";
    public static final String RECIPE_STEPS_INDEX = "steps_index";


    public static final int[] recipeThumbnailId = {
            R.drawable.nutellapie,
            R.drawable.brownies,
            R.drawable.yellowcake,
            R.drawable.cheesecake
    };
}
