package me.chandansharma.foodbook.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
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
                mRecipeDetailTextView.setText("Step " + itemPosition + ": " +
                        mRecipeSteps.get(itemPosition - 1).getRecipeStepsShortDescription());
        }

        @Override
        public void onClick(View v) {

            if (mItemPosition == 0) {

                Bundle recipeIngredientsDataBundle = new Bundle();

                recipeIngredientsDataBundle.putParcelableArrayList(RecipeDetails.RECIPE_INGREDIENTS_KEY,
                        mRecipeIngredients);

                Fragment recipeIngredientsFragment = new RecipeIngredientsDetailFragment();
                recipeIngredientsFragment.setArguments(recipeIngredientsDataBundle);

                ((RecipeDetailActivity) mContext).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(android.R.id.content, recipeIngredientsFragment)
                        .commit();

            } else {
                Intent sendRecipeStepsDetailIntent = new Intent(mContext,
                        RecipeStepsDetailFragment.class);
                sendRecipeStepsDetailIntent.putExtra(RecipeDetails.RECIPE_STEPS_KEY,
                        mRecipeSteps);
                mContext.startActivity(sendRecipeStepsDetailIntent);
            }
        }
    }
}
