package me.chandansharma.foodbook.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import me.chandansharma.foodbook.R;
import me.chandansharma.foodbook.fragment.RecipeDetailFragment;
import me.chandansharma.foodbook.model.RecipeIngredients;
import me.chandansharma.foodbook.model.RecipeSteps;
import me.chandansharma.foodbook.utils.RecipeDetails;

public class RecipeDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        ArrayList<RecipeIngredients> recipeIngredients = getIntent()
                .getParcelableArrayListExtra(RecipeDetails.RECIPE_INGREDIENTS_KEY);
        ArrayList<RecipeSteps> recipeSteps = getIntent()
                .getParcelableArrayListExtra(RecipeDetails.RECIPE_STEPS_KEY);

        Bundle recipeDetailDataBundle = new Bundle();

        recipeDetailDataBundle.putParcelableArrayList(RecipeDetails.RECIPE_INGREDIENTS_KEY,
                recipeIngredients);
        recipeDetailDataBundle.putParcelableArrayList(RecipeDetails.RECIPE_STEPS_KEY,
                recipeSteps);

        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
        recipeDetailFragment.setArguments(recipeDetailDataBundle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, recipeDetailFragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0)
            getFragmentManager().popBackStack();
        else
            super.onBackPressed();
    }
}
