/*
 * Copyright (C) 2017 Michał Jankowski.
 * www.michaeljankowski.com - michael.jankowski.com@gmail.com
 * All Rights Reserved.
 */
package pl.orbitemobile.wspolnoty;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import android.app.Application;

import pl.orbitemobile.wspolnoty.R;

public class BaseApplication extends Application {
    
    private Tracker mTracker;
    
    @Override
    public void onCreate() {
        super.onCreate();
    }
    
    synchronized public Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            mTracker = analytics.newTracker(R.xml.global_tracker);
        }
        return mTracker;
    }
}