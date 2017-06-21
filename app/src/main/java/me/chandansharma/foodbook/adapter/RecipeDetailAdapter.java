package me.chandansharma.foodbook.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import me.chandansharma.foodbook.R;
import me.chandansharma.foodbook.fragment.RecipeIngredientsDetailFragment;
import me.chandansharma.foodbook.fragment.RecipeStepsDetailFragment;
import me.chandansharma.foodbook.model.RecipeIngredients;
import me.chandansharma.foodbook.model.RecipeSteps;
import me.chandansharma.foodbook.ui.RecipeDetailActivity;
import me.chandansharma.foodbook.ui.RecipeStepsActivity;
import me.chandansharma.foodbook.utils.RecipeDetails;

/**
 * Created by iamcs on 2017-06-11.
 */

public class RecipeDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<RecipeIngredients> mRecipeIngredients;
    private ArrayList<RecipeSteps> mRecipeSteps;


    public RecipeDetailAdapter(Context context, ArrayList<RecipeIngredients> recipeIngredients,
                               ArrayList<RecipeSteps> recipeSteps) {
        mContext = context;
        mRecipeIngredients = recipeIngredients;
        mRecipeSteps = recipeSteps;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecipeDetailViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.recipe_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((RecipeDetailViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return mRecipeSteps.size() + 1;
    }


    private class RecipeDetailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        int mItemPosition;
        private TextView mRecipeDetailTextView;

        private RecipeDetailViewHolder(View itemView) {
            super(itemView);

            mRecipeDetailTextView = (TextView) itemView.findViewById(R.id.tv_recipe_short_description);

            itemView.setOnClickListener(this);
        }

        private void bindView(int itemPosition) {

            mItemPosition = itemPosition;

            if (itemPosition == 0)
                mRecipeDetailTextView.setText("Recipe Ingredients");
            else
                mRecipeDetailTextView.setText("Step " + (itemPosition - 1) + ": " +
                        mRecipeSteps.get(itemPosition - 1).getRecipeStepsShortDescription());
        }

        @Override
        public void onClick(View v) {

            float smallestWidth = getSmallestWidth();
            Log.d("My APP", String.valueOf(smallestWidth));

            if (mItemPosition == 0) {

                if (smallestWidth >= 600 || mContext.getResources().getConfiguration().orientation
                        == Configuration.ORIENTATION_LANDSCAPE) {

                    Bundle recipeIngredientsDataBundle = new Bundle();

                    recipeIngredientsDataBundle.putInt(RecipeDetails.RECIPE_STEPS_INDEX, mItemPosition);

                    recipeIngredientsDataBundle.putParcelableArrayList(RecipeDetails.RECIPE_INGREDIENTS_KEY,
                            mRecipeIngredients);

                    Fragment recipeIngredientsDetailFragment = new RecipeIngredientsDetailFragment();
                    recipeIngredientsDetailFragment.setArguments(recipeIngredientsDataBundle);

                    ((RecipeDetailActivity) mContext).getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fm_recipe_steps, recipeIngredientsDetailFragment)
                            .commit();
                } else {
                    Intent sendRecipeIngredientsIntent = new Intent(mContext, RecipeStepsActivity.class);
                    sendRecipeIngredientsIntent.putExtra(RecipeDetails.RECIPE_STEPS_INDEX,
                            String.valueOf(mItemPosition));
                    sendRecipeIngredientsIntent
                            .putParcelableArrayListExtra(RecipeDetails.RECIPE_INGREDIENTS_KEY,
                                    mRecipeIngredients);
                    mContext.startActivity(sendRecipeIngredientsIntent);
                }
            } else {

                if (smallestWidth >= 600 || mContext.getResources().getConfiguration().orientation
                        == Configuration.ORIENTATION_LANDSCAPE) {

                    Bundle recipeStepsDataBundle = new Bundle();

                    recipeStepsDataBundle.putInt(RecipeDetails.RECIPE_STEPS_INDEX, mItemPosition - 1);

                    recipeStepsDataBundle.putParcelableArrayList(RecipeDetails.RECIPE_STEPS_KEY,
                            mRecipeSteps);

                    Fragment recipeStepsDetailFragment = new RecipeStepsDetailFragment();
                    recipeStepsDetailFragment.setArguments(recipeStepsDataBundle);

                    ((RecipeDetailActivity) mContext).getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fm_recipe_steps, recipeStepsDetailFragment)
                            .commit();
                } else {
                    Intent sendRecipeStepsIntent = new Intent(mContext, RecipeStepsActivity.class);
                    sendRecipeStepsIntent.putExtra(RecipeDetails.RECIPE_STEPS_INDEX,
                            String.valueOf(mItemPosition));
                    sendRecipeStepsIntent.putParcelableArrayListExtra(RecipeDetails.RECIPE_STEPS_KEY,
                            mRecipeSteps);
                    mContext.startActivity(sendRecipeStepsIntent);
                }
            }
        }

        private float getSmallestWidth() {
            DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();

            int widthInPixel = displayMetrics.widthPixels;
            int heightInPixel = displayMetrics.heightPixels;

            float scaleFactor = displayMetrics.density;

            float widthInDp = widthInPixel / scaleFactor;
            float heightInDp = heightInPixel / scaleFactor;

            return Math.min(widthInDp, heightInDp);
        }
    }
}
