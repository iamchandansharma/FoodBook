package me.chandansharma.foodbook.model;

import java.util.ArrayList;

/**
 * Created by iamcs on 2017-06-10.
 * Model for Recipe
 */

public class Recipe {

    /**
     * Member Variable to holds Recipe Ingredients and Recipe Steps Details.
     */

    private ArrayList<RecipeIngredients> mRecipeIngredients;
    private ArrayList<RecipeSteps> mRecipeSteps;

    /**
     * @param mRecipeIngredients Collection of Recipe Ingredients for single recipe
     * @param mRecipeSteps       Complete Recipe Procedure
     */
    public Recipe(ArrayList<RecipeIngredients> mRecipeIngredients, ArrayList<RecipeSteps> mRecipeSteps) {
        this.mRecipeIngredients = mRecipeIngredients;
        this.mRecipeSteps = mRecipeSteps;
    }

    public ArrayList<RecipeIngredients> getRecipeIngredients() {
        return mRecipeIngredients;
    }

    public void setRecipeIngredients(ArrayList<RecipeIngredients> recipeIngredients) {
        mRecipeIngredients = recipeIngredients;
    }

    public ArrayList<RecipeSteps> getRecipeSteps() {
        return mRecipeSteps;
    }

    public void setRecipeSteps(ArrayList<RecipeSteps> recipeSteps) {
        mRecipeSteps = recipeSteps;
    }
}
