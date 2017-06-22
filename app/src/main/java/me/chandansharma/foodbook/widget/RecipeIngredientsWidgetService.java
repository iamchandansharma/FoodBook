package me.chandansharma.foodbook.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by iamcs on 2017-06-22.
 */

public class RecipeIngredientsWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RecipeIngredientsWidgetAdapter(this);
    }
}
