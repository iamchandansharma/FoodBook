package me.chandansharma.foodbook.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import me.chandansharma.foodbook.R;
import me.chandansharma.foodbook.model.Recipe;
import me.chandansharma.foodbook.ui.RecipeDetailActivity;
import me.chandansharma.foodbook.utils.RecipeDetails;

/**
 * Created by iamcs on 2017-06-10.
 * RecipeListAdapter class which return a single view and also recycle the vies with the new data
 */

public class RecipeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<Recipe> mRecipes;

    public RecipeListAdapter(Context context, ArrayList<Recipe> recipes) {
        mContext = context;
        mRecipes = recipes;
    }

    @Override
    public RecipeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         * Create new Single View and not added to children of the parent view
         */
        return new RecipeListViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.recipe_list, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        /**
         * Bind  View with data
         */
        ((RecipeListViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    /**
     * RecipeListViewHolder class which cache view once so that
     * each and every time not called findViewById
     */
    private class RecipeListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private int mItemPosition;

        private ImageView mRecipeThumbnailImageView;
        private TextView mRecipeNameTextView;
        private LinearLayout mRecipeNameLinearLayout;
        private ImageView mRecipeFavouriteButton;

        private RecipeListViewHolder(View itemView) {
            super(itemView);

            mRecipeThumbnailImageView = (ImageView) itemView.findViewById(R.id.im_recipe_thumbnail);
            mRecipeNameTextView = (TextView) itemView.findViewById(R.id.tv_recipe_name);
            mRecipeFavouriteButton = (ImageView) itemView.findViewById(R.id.im_favourite_button);
            mRecipeNameLinearLayout = (LinearLayout) itemView.findViewById(R.id.ll_recipe_name);
            itemView.setOnClickListener(this);
        }

        private void bindView(int itemPosition) {
            mItemPosition = itemPosition;
            Glide.with(mContext)
                    .load(mRecipes.get(itemPosition).getRecipeThumbnailUrl())
                    .placeholder(ContextCompat.getDrawable(mContext,
                            RecipeDetails.recipeThumbnailId[itemPosition]))
                    .centerCrop()
                    .into(mRecipeThumbnailImageView);

            mRecipeNameTextView.setText(mRecipes.get(itemPosition).getRecipeName());
            mRecipeNameLinearLayout.setBackgroundColor(Color.argb(178, 0, 0, 0));
        }

        @Override
        public void onClick(View v) {

            Intent sendRecipeDetailIntent = new Intent(mContext, RecipeDetailActivity.class);
            sendRecipeDetailIntent.putParcelableArrayListExtra(RecipeDetails.RECIPE_INGREDIENTS_KEY,
                    mRecipes.get(mItemPosition).getRecipeIngredients());
            sendRecipeDetailIntent.putParcelableArrayListExtra(RecipeDetails.RECIPE_STEPS_KEY,
                    mRecipes.get(mItemPosition).getRecipeSteps());
            mContext.startActivity(sendRecipeDetailIntent);
        }
    }
}
