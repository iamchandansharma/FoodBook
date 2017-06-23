package me.chandansharma.foodbook.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by iamcs on 2017-06-10.
 * Model for Recipe
 */

public class Recipe implements Parcelable {

    public static Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel source) {
            return new Recipe(source);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
    /**
     * Member Variable to holds Recipe Ingredients and Recipe Steps Details.
     */
    private int mRecipeId;
    private String mRecipeName;
    private int mRecipeServingPerson;
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
    public Recipe(int mRecipeId, String mRecipeName, int mRecipeServingPerson,
                  String mRecipeThumbnailUrl, ArrayList<RecipeIngredients> mRecipeIngredients,
                  ArrayList<RecipeSteps> mRecipeSteps) {
        this.mRecipeId = mRecipeId;
        this.mRecipeName = mRecipeName;
        this.mRecipeServingPerson = mRecipeServingPerson;
        this.mRecipeThumbnailUrl = mRecipeThumbnailUrl;
        this.mRecipeIngredients = mRecipeIngredients;
        this.mRecipeSteps = mRecipeSteps;
    }

    private Recipe(Parcel in) {
        mRecipeId = in.readInt();
        mRecipeName = in.readString();
        mRecipeServingPerson = in.readInt();
        mRecipeThumbnailUrl = in.readString();
        mRecipeIngredients = in.readArrayList(RecipeIngredients.class.getClassLoader());
        mRecipeSteps = in.readArrayList(RecipeSteps.class.getClassLoader());
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

    public int getRecipeServingPerson() {
        return mRecipeServingPerson;
    }

    public void setRecipeServingPerson(int recipeServingPerson) {
        mRecipeServingPerson = recipeServingPerson;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mRecipeId);
        dest.writeString(mRecipeName);
        dest.writeInt(mRecipeServingPerson);
        dest.writeString(mRecipeThumbnailUrl);
        dest.writeList(mRecipeIngredients);
        dest.writeList(mRecipeSteps);
    }
}

