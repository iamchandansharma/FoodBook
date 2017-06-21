package me.chandansharma.foodbook.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.chandansharma.foodbook.R;
import me.chandansharma.foodbook.adapter.RecipeDetailAdapter;
import me.chandansharma.foodbook.model.RecipeIngredients;
import me.chandansharma.foodbook.model.RecipeSteps;
import me.chandansharma.foodbook.utils.RecipeDetails;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDetailFragment extends Fragment {

    @BindView(R.id.rv_recipe_detail)
    RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ArrayList<RecipeIngredients> recipeIngredients = new ArrayList<>();
        ArrayList<RecipeSteps> recipeSteps = new ArrayList<>();

        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        ButterKnife.bind(this, rootView);
        recipeIngredients = getArguments().getParcelableArrayList(RecipeDetails.RECIPE_INGREDIENTS_KEY);
        recipeSteps = getArguments().getParcelableArrayList(RecipeDetails.RECIPE_STEPS_KEY);

        RecipeDetailAdapter recipeDetailAdapter = new RecipeDetailAdapter(getActivity(),
                recipeIngredients, recipeSteps);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(recipeDetailAdapter);

        return rootView;
    }
}
