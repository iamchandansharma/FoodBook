package me.chandansharma.foodbook.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import me.chandansharma.foodbook.R;
import me.chandansharma.foodbook.model.RecipeSteps;
import me.chandansharma.foodbook.utils.RecipeDetails;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeStepsDetailFragment extends Fragment {

    private ArrayList<RecipeSteps> mRecipeSteps;
    private int mRecipeStepsIndex;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mRecipeSteps = getArguments()
                .getParcelableArrayList(RecipeDetails.RECIPE_STEPS_KEY);
        mRecipeStepsIndex = getArguments().getInt(RecipeDetails.RECIPE_STEPS_INDEX);

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recipe_steps_detail, container, false);
        TextView recipeStepsDescription = (TextView) rootView
                .findViewById(R.id.tv_recipe_steps_detail);

        TextView previousRecipeStepsTextView = (TextView) rootView
                .findViewById(R.id.tv_previous_step);
        TextView nextRecipeStepsTextView = (TextView) rootView.findViewById(R.id.tv_next_step);

        if (mRecipeSteps != null)
            recipeStepsDescription.setText(mRecipeSteps.get(mRecipeStepsIndex)
                    .getRecipeStepsDescription());

        previousRecipeStepsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRecipeStepsIndex <= 0)
                    Toast.makeText(getActivity(),
                            "You already on first step", Toast.LENGTH_SHORT).show();
                else {
                    Bundle recipeStepsDataBundle = new Bundle();

                    recipeStepsDataBundle.putParcelableArrayList(RecipeDetails.RECIPE_STEPS_KEY,
                            mRecipeSteps);
                    recipeStepsDataBundle.putInt(RecipeDetails.RECIPE_STEPS_INDEX,
                            mRecipeStepsIndex - 1);

                    Fragment recipeStepsDetailFragment = new RecipeStepsDetailFragment();
                    recipeStepsDetailFragment.setArguments(recipeStepsDataBundle);

                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fm_recipe_detail, recipeStepsDetailFragment)
                            .commit();
                }
            }
        });

        nextRecipeStepsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mRecipeStepsIndex >= mRecipeSteps.size() - 1)
                    Toast.makeText(getActivity(),
                            "You already on last step", Toast.LENGTH_SHORT).show();
                else {
                    Bundle recipeStepsDataBundle = new Bundle();

                    recipeStepsDataBundle.putParcelableArrayList(RecipeDetails.RECIPE_STEPS_KEY,
                            mRecipeSteps);
                    recipeStepsDataBundle.putInt(RecipeDetails.RECIPE_STEPS_INDEX,
                            mRecipeStepsIndex + 1);

                    Fragment recipeStepsDetailFragment = new RecipeStepsDetailFragment();
                    recipeStepsDetailFragment.setArguments(recipeStepsDataBundle);

                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fm_recipe_detail, recipeStepsDetailFragment)
                            .commit();
                }
            }
        });
        return rootView;
    }
}
