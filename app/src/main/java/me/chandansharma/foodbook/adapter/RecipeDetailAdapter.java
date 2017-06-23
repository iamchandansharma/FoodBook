package me.chandansharma.foodbook.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    private static final int RECIPE_INGREDIENTS = 0;
    private static final int RECIPE_STEPS = 1;
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

        if (viewType == RECIPE_INGREDIENTS)
            return new RecipeIngredientsDetailViewHolder(LayoutInflater.from(mContext)
                    .inflate(R.layout.recipe_ingredients_item, parent, false));
        else
            return new RecipeStepsDetailViewHolder(LayoutInflater.from(mContext)
                    .inflate(R.layout.recipe_steps_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == RECIPE_INGREDIENTS)
            ((RecipeIngredientsDetailViewHolder) holder).bindView(position);
        else
            ((RecipeStepsDetailViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return mRecipeSteps.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return RECIPE_INGREDIENTS;
        else
            return RECIPE_STEPS;
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

    class RecipeIngredientsDetailViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        int mItemPosition;

        @BindView(R.id.tv_recipe_short_description)
        TextView mRecipeDetailTextView;


        private RecipeIngredientsDetailViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        private void bindView(int itemPosition) {

            mItemPosition = itemPosition;
            float smallestWidth = getSmallestWidth();
            if (smallestWidth >= 600) {
                mRecipeDetailTextView
                        .setText(mContext.getString(R.string.recipe_ingredients_text));
                itemView.performClick();

            } else
                mRecipeDetailTextView
                        .setText(mContext.getString(R.string.recipe_ingredients_text));
        }

        @Override
        public void onClick(View v) {

            float smallestWidth = getSmallestWidth();

            if (smallestWidth >= 600) {

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
        }
    }

    class RecipeStepsDetailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        int mItemPosition;

        @BindView(R.id.tv_recipe_short_description)
        TextView mRecipeDetailTextView;

        @BindView(R.id.im_recipe_steps_thumbnail)
        ImageView mRecipeStepsThumbnail;

        private RecipeStepsDetailViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        private void bindView(int itemPosition) {

            mItemPosition = itemPosition;

            Glide.with(mContext)
                    .load(mRecipeSteps.get(itemPosition - 1).getRecipeStepsThumbnailUrl())
                    .placeholder(ContextCompat.getDrawable(mContext, R.drawable.ic_recipe_steps))
                    .into(mRecipeStepsThumbnail);

            mRecipeDetailTextView.setText("Step " + (itemPosition - 1) + ": " +
                    mRecipeSteps.get(itemPosition - 1).getRecipeStepsShortDescription());

        }

        @Override
        public void onClick(View v) {

            float smallestWidth = getSmallestWidth();

            if (smallestWidth >= 600) {

                Bundle recipeStepsDataBundle = new Bundle();

                recipeStepsDataBundle.putInt(RecipeDetails.RECIPE_STEPS_INDEX, mItemPosition);

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
                sendRecipeStepsIntent
                        .putParcelableArrayListExtra(RecipeDetails.RECIPE_STEPS_KEY,
                                mRecipeSteps);
                mContext.startActivity(sendRecipeStepsIntent);
            }
        }
    }
}


