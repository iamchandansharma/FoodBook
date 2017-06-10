package me.chandansharma.foodbook.model;

/**
 * Created by iamcs on 2017-06-10.
 * Model class for Recipe Ingredients
 */

public class RecipeIngredients {

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
}
