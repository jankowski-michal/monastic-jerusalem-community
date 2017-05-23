/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.utilities;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import pl.orbitemobile.wspolnoty.activities.article.ArticleFragment;
import pl.orbitemobile.wspolnoty.BaseApplication;

public class AnalyticsLogger {
    
    private Tracker mTracker;
    
    public void LogAnalytics(String TAG, BaseApplication application) {
        mTracker = application.getDefaultTracker();
        mTracker.setScreenName(ArticleFragment.class.getSimpleName());
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }
}
