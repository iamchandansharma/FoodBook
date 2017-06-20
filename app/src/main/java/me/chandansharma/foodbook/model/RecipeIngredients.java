package me.chandansharma.foodbook.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by iamcs on 2017-06-10.
 * Model class for Recipe Ingredients
 */

public class RecipeIngredients implements Parcelable {

    //Method to recreate RecipeIngredients from Parcel
    public static Creator<RecipeIngredients> CREATOR = new Creator<RecipeIngredients>() {
        @Override
        public RecipeIngredients createFromParcel(Parcel source) {
            return new RecipeIngredients(source);
        }

        @Override
        public RecipeIngredients[] newArray(int size) {
            return new RecipeIngredients[size];
        }
    };
    /**
     * Member Variable to holds Recipe Ingredients Details.
     */
    private String mRecipeIngredientsName;
    private String mRecipeIngredientsMeasure;
    private Double mRecipeIngredientsQuantity;

    /**
     * @param mRecipeIngredientsName     Recipe Ingredients Name
     * @param mRecipeIngredientsMeasure  Recipe Ingredients Measure example like Cup, TBSP
     * @param mRecipeIngredientsQuantity Recipe Ingredients Quantity
     */

    public RecipeIngredients(String mRecipeIngredientsName, String mRecipeIngredientsMeasure,
                             Double mRecipeIngredientsQuantity) {

        this.mRecipeIngredientsName = mRecipeIngredientsName;
        this.mRecipeIngredientsMeasure = mRecipeIngredientsMeasure;
        this.mRecipeIngredientsQuantity = mRecipeIngredientsQuantity;
    }

    // Parcelling part
    private RecipeIngredients(Parcel in) {
        mRecipeIngredientsName = in.readString();
        mRecipeIngredientsMeasure = in.readString();
        mRecipeIngredientsQuantity = in.readDouble();
    }

    public String getRecipeIngredientsName() {
        return mRecipeIngredientsName;
    }

    public void setRecipeIngredientsName(String recipeIngredientsName) {
        mRecipeIngredientsName = recipeIngredientsName;
    }

    public String getRecipeIngredientsMeasure() {
        return mRecipeIngredientsMeasure;
    }

    public void setRecipeIngredientsMeasure(String recipeIngredientsMeasure) {
        mRecipeIngredientsMeasure = recipeIngredientsMeasure;
    }

    public Double getRecipeIngredientsQuantity() {
        return mRecipeIngredientsQuantity;
    }

    public void setRecipeIngredientsQuantity(Double recipeIngredientsQuantity) {
        mRecipeIngredientsQuantity = recipeIngredientsQuantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mRecipeIngredientsName);
        dest.writeString(mRecipeIngredientsMeasure);
        dest.writeDouble(mRecipeIngredientsQuantity);
    }
}
