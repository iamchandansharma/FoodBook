package me.chandansharma.foodbook.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import me.chandansharma.foodbook.R;
import me.chandansharma.foodbook.model.Recipe;

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
                .inflate(R.layout.recipe_details, parent, false));
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
    private class RecipeListViewHolder extends RecyclerView.ViewHolder {

        private ImageView mRecipeThumbnailImageView;
        private TextView mRecipeNameTextView;

        private RecipeListViewHolder(View itemView) {
            super(itemView);

            mRecipeThumbnailImageView = (ImageView) itemView.findViewById(R.id.im_recipe_thumbnail);
            mRecipeNameTextView = (TextView) itemView.findViewById(R.id.tv_recipe_name);
        }

        private void bindView(int itemPosition) {
            if (mRecipes.get(itemPosition).getRecipeThumbnailUrl().equals(""))
                mRecipeThumbnailImageView.setImageDrawable(ContextCompat
                        .getDrawable(mContext, R.mipmap.ic_launcher));

            mRecipeNameTextView.setText(mRecipes.get(itemPosition).getRecipeName());
        }
    }
}
