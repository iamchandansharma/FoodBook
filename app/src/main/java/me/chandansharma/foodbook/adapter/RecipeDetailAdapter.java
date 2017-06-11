package me.chandansharma.foodbook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import me.chandansharma.foodbook.R;
import me.chandansharma.foodbook.model.RecipeSteps;

/**
 * Created by iamcs on 2017-06-11.
 */

public class RecipeDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<RecipeSteps> mRecipeSteps;


    public RecipeDetailAdapter(Context context, ArrayList<RecipeSteps> recipeSteps) {
        mContext = context;
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


    private class RecipeDetailViewHolder extends RecyclerView.ViewHolder {

        private TextView mRecipeDetailTextView;

        private RecipeDetailViewHolder(View itemView) {
            super(itemView);

            mRecipeDetailTextView = (TextView) itemView.findViewById(R.id.tv_recipe_short_description);
        }

        private void bindView(int itemPosition) {
            if (itemPosition == 0)
                mRecipeDetailTextView.setText("Recipe Ingeredients");
            else
                mRecipeDetailTextView.setText("Step " + itemPosition + ":" +
                        mRecipeSteps.get(itemPosition).getRecipeStepsShortDescription());
        }
    }
}
