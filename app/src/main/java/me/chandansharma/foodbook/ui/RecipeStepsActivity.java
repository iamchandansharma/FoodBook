package me.chandansharma.foodbook.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import me.chandansharma.foodbook.R;
import me.chandansharma.foodbook.fragment.RecipeIngredientsDetailFragment;
import me.chandansharma.foodbook.fragment.RecipeStepsDetailFragment;
import me.chandansharma.foodbook.model.RecipeIngredients;
import me.chandansharma.foodbook.model.RecipeSteps;
import me.chandansharma.foodbook.utils.RecipeDetails;

public class RecipeStepsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);

        ArrayList<RecipeIngredients> recipeIngredients = new ArrayList<>();
        ArrayList<RecipeSteps> recipeSteps = new ArrayList<>();

        int recipeDetailIndex = Integer.parseInt(getIntent()
                .getStringExtra(RecipeDetails.RECIPE_STEPS_INDEX));

        if (recipeDetailIndex == 0) {
            recipeIngredients = getIntent()
                    .getParcelableArrayListExtra(RecipeDetails.RECIPE_INGREDIENTS_KEY);

            Bundle recipeIngredientsDataBundle = new Bundle();

            recipeIngredientsDataBundle.putParcelableArrayList(RecipeDetails.RECIPE_INGREDIENTS_KEY,
                    recipeIngredients);

            Fragment recipeIngredientsDetailFragment = new RecipeIngredientsDetailFragment();
            recipeIngredientsDetailFragment.setArguments(recipeIngredientsDataBundle);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fm_recipe_detail, recipeIngredientsDetailFragment)
                    .commit();
        } else {
            recipeSteps = getIntent().getParcelableArrayListExtra(RecipeDetails.RECIPE_STEPS_KEY);

            Bundle recipeStepsDataBundle = new Bundle();

            recipeStepsDataBundle.putInt(RecipeDetails.RECIPE_STEPS_INDEX, recipeDetailIndex - 1);

            recipeStepsDataBundle.putParcelableArrayList(RecipeDetails.RECIPE_STEPS_KEY,
                    recipeSteps);

            Fragment recipeStepsDetailFragment = new RecipeStepsDetailFragment();
            recipeStepsDetailFragment.setArguments(recipeStepsDataBundle);


            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fm_recipe_detail, recipeStepsDetailFragment)
                    .commit();
        }
    }
}
