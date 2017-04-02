/*
 * Copyright (C) 2017 Michał Jankowski.
 * www.michaeljankowski.com - michael.jankowski.com@gmail.com
 * All Rights Reserved.
 */
package pl.orbitemobile.wspolnoty.activities.where.logic;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

public class MapsIntentBuilder {
    
    private final static String MAPS_URL = "https://www.google.pl/maps/place/Wspólnoty+Jerozolimskie/@52.221897,21.03841,15z";
    
    private Context mContext;
    
    public MapsIntentBuilder(Context context) {
        mContext = context;
    }
    
    public Intent getMapsBrowserIntent() {
        return new Intent(Intent.ACTION_VIEW, Uri.parse(MAPS_URL));
    }
    
    public Intent getMapsIntent() {
        if (canOpenMapsApplication()) {
            return getMapsApplicationIntent();
        } else {
            return getMapsBrowserIntent();
        }
    }
    
    private boolean canOpenMapsApplication() {
        Intent mapIntent = getMapsApplicationIntent();
        return mapIntent.resolveActivity(mContext.getPackageManager()) != null;
    }
    
    @NonNull
    private Intent getMapsApplicationIntent() {
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, getParsedUri());
        mapIntent.setPackage("com.google.android.apps.maps");
        return mapIntent;
    }
    
    private Uri getParsedUri() {
        String mapApplicationUri = getMapsApplicationUri();
        return Uri.parse(mapApplicationUri);
    }
    
    @NonNull
    private String getMapsApplicationUri() {
        double latitude = 52.221897;
        double longitude = 21.03841;
        String label = "Wspólnoty Jerozolimskie";
        String uriBegin = "geo:" + latitude + "," + longitude;
        String query = latitude + "," + longitude + "(" + label + ")";
        String encodedQuery = Uri.encode(query);
        return uriBegin + "?q=" + encodedQuery + "&z=15";
    }
}
