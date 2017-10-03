/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.utilities;


import android.content.Context;
import android.content.Intent;

import java.util.HashMap;

import pl.orbitemobile.wspolnoty.data.dto.ArticleDTO;

public class ActivityLauncher {

    public void startActivity(Context context, Class nextActivityClass) {
        Intent intent = new Intent(context, nextActivityClass);
        context.startActivity(intent);
    }


    public void startActivity(Context context, Class nextActivityClass, HashMap<ArticleDTO.KEY, String> extra) {
        Intent intent = new Intent(context, nextActivityClass);
        for (ArticleDTO.KEY key : extra.keySet()) {
            intent.putExtra(key.name(), extra.get(key));
        }
        context.startActivity(intent);

    }
}
