/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.data.remote;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import android.util.Log;

import java.io.IOException;

public class Downloader {
    
    private static final String USER_AGENT
            = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";
    
    private static final int TIMEOUT = 10000;
    
    private static final String TAG = Downloader.class.getSimpleName();
    
    public Connection.Response getResponse(final String url) {
        Connection.Response response = null;
        try {
            response = Jsoup.connect(url)
                    .userAgent(USER_AGENT)
                    .timeout(TIMEOUT)
                    .ignoreContentType(true)
                    .execute();
        } catch (IOException | IllegalArgumentException e) {
            Log.d(TAG, "Error on get Response: " + e.getMessage());
        }
        return response;
    }
}
