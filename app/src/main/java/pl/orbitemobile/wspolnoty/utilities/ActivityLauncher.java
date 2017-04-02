/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.utilities;


import android.content.Context;
import android.content.Intent;

import java.util.HashMap;

import pl.orbitemobile.wspolnoty.data.entities.Article;

public class ActivityLauncher {

    public void startActivity(Context context, Class nextActivityClass) {
        Intent intent = new Intent(context, nextActivityClass);
        context.startActivity(intent);
    }


    public void startActivity(Context context, Class nextActivityClass, HashMap<Article.KEY, String> extra) {
        Intent intent = new Intent(context, nextActivityClass);
        for (Article.KEY key : extra.keySet()) {
            intent.putExtra(key.name(), extra.get(key));
        }
        context.startActivity(intent);

    }
}
