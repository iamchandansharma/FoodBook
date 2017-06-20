package me.chandansharma.foodbook.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by iamcs on 2017-06-10.
 * Model class for Recipe Steps
 */

public class RecipeSteps implements Parcelable {

    public static Creator<RecipeSteps> CREATOR = new Creator<RecipeSteps>() {
        @Override
        public RecipeSteps createFromParcel(Parcel source) {
            return new RecipeSteps(source);
        }

        @Override
        public RecipeSteps[] newArray(int size) {
            return new RecipeSteps[size];
        }
    };
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

    // Parcelling Part
    private RecipeSteps(Parcel in) {

        mRecipeStepsShortDescription = in.readString();
        mRecipeStepsDescription = in.readString();
        mRecipeStepsVideoUrl = in.readString();
        mRecipeStepsThumbnailUrl = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(mRecipeStepsShortDescription);
        dest.writeString(mRecipeStepsDescription);
        dest.writeString(mRecipeStepsVideoUrl);
        dest.writeString(mRecipeStepsThumbnailUrl);
    }
}
