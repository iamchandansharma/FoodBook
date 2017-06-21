package me.chandansharma.foodbook;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.chandansharma.foodbook.ui.FoodBookMainScreen;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by iamcs on 2017-06-22.
 */

@RunWith(AndroidJUnit4.class)
public class FoodBookMainScreenIntentTest {

    private static final String RECIPE_INDEX_KEY = "recipe_index";
    private static final int RECIPE_INDEX_VALUE = 1;

    @Rule
    public IntentsTestRule<FoodBookMainScreen> mFoodBookMainScreenIntentsTestRule =
            new IntentsTestRule<FoodBookMainScreen>(FoodBookMainScreen.class);

    @Test
    public void clickOnRecipeListItem_OpensRecipeDetailActivity() {

        //1. Find the View
        onView(withId(R.id.rv_recipe_list));

        //2. Perform action on views
        onView(withId(R.id.rv_recipe_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        //3.check the view does what you expected
        intended(
                hasExtra(RECIPE_INDEX_KEY, RECIPE_INDEX_VALUE)
        );
    }


}
