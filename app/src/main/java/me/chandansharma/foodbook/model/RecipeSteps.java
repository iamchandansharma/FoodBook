package me.chandansharma.foodbook.model;

/**
 * Created by iamcs on 2017-06-10.
 * Model class for Recipe Steps
 */

public class RecipeSteps {

    /**
     * Member Variable to holds Recipe Steps Details.
     */

    private String mRecipeStepsShortDescription;
    private String mRecipeStepsDescription;
    private String mRecipeStepsVideoUrl;
    private String mRecipeStepsThumbnailUrl;

    /**
     * @param mRecipeStepsShortDescription Recipe Procedure Shore Description
     * @param mRecipeStepsDescription      Recipe Procedure Long Description
     * @param mRecipeStepsVideoUrl         Recipe Procedure Video Url
     * @param mRecipeStepsThumbnailUrl     Recipe Procedure ThumbnailUrl
     */


    public RecipeSteps(String mRecipeStepsShortDescription, String mRecipeStepsDescription,
                       String mRecipeStepsVideoUrl, String mRecipeStepsThumbnailUrl) {
        this.mRecipeStepsShortDescription = mRecipeStepsShortDescription;
        this.mRecipeStepsDescription = mRecipeStepsDescription;
        this.mRecipeStepsVideoUrl = mRecipeStepsVideoUrl;
        this.mRecipeStepsThumbnailUrl = mRecipeStepsThumbnailUrl;
    }

    public String getRecipeStepsShortDescription() {
        return mRecipeStepsShortDescription;
    }

    public void setRecipeStepsShortDescription(String recipeStepsShortDescription) {
        mRecipeStepsShortDescription = recipeStepsShortDescription;
    }

    public String getRecipeStepsDescription() {
        return mRecipeStepsDescription;
    }

    public void setRecipeStepsDescription(String recipeStepsDescription) {
        mRecipeStepsDescription = recipeStepsDescription;
    }

    public String getRecipeStepsVideoUrl() {
        return mRecipeStepsVideoUrl;
    }

    public void setRecipeStepsVideoUrl(String recipeStepsVideoUrl) {
        mRecipeStepsVideoUrl = recipeStepsVideoUrl;
    }

    public String getRecipeStepsThumbnailUrl() {
        return mRecipeStepsThumbnailUrl;
    }

    public void setRecipeStepsThumbnailUrl(String recipeStepsThumbnailUrl) {
        mRecipeStepsThumbnailUrl = recipeStepsThumbnailUrl;
    }
}
