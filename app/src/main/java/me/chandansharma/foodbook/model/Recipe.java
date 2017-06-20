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
    private int mRecipeId;
    private String mRecipeName;
    private String mRecipeThumbnailUrl;
    private ArrayList<RecipeIngredients> mRecipeIngredients;
    private ArrayList<RecipeSteps> mRecipeSteps;

    /**
     * @param mRecipeId           Id of the Recipe
     * @param mRecipeName         Name of the recipe
     * @param mRecipeThumbnailUrl ThumbnailUrl of the particular recipe
     * @param mRecipeIngredients  Collection of Recipe Ingredients for single recipe
     * @param mRecipeSteps        Complete Recipe Procedure
     */
    public Recipe(int mRecipeId, String mRecipeName, String mRecipeThumbnailUrl,
                  ArrayList<RecipeIngredients> mRecipeIngredients,
                  ArrayList<RecipeSteps> mRecipeSteps) {
        this.mRecipeId = mRecipeId;
        this.mRecipeName = mRecipeName;
        this.mRecipeThumbnailUrl = mRecipeThumbnailUrl;
        this.mRecipeIngredients = mRecipeIngredients;
        this.mRecipeSteps = mRecipeSteps;
    }

    public int getRecipeId() {
        return mRecipeId;
    }

    public void setRecipeId(int recipeId) {
        mRecipeId = recipeId;
    }

    public String getRecipeName() {
        return mRecipeName;
    }

    public void setRecipeName(String recipeName) {
        mRecipeName = recipeName;
    }

    public String getRecipeThumbnailUrl() {
        return mRecipeThumbnailUrl;
    }

    public void setRecipeThumbnailUrl(String recipeThumbnailUrl) {
        mRecipeThumbnailUrl = recipeThumbnailUrl;
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
