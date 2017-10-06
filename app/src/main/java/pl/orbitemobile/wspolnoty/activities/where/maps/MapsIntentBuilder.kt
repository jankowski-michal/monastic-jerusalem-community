/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.activities.where.maps

import android.content.Context
import android.content.Intent
import android.net.Uri

class MapsIntentBuilder() {

    private val mapsBrowserIntent: Intent
        get() = Intent(Intent.ACTION_VIEW, Uri.parse(MAPS_URL))

    fun mapsIntent(context: Context?) =
            if (context?.canOpenMapsApplication() == true) {
                mapsApplicationIntent
            } else {
                mapsBrowserIntent
            }

    private fun Context.canOpenMapsApplication(): Boolean {
        val mapIntent = mapsApplicationIntent
        return mapIntent.resolveActivity(packageManager) != null
    }

    private val mapsApplicationIntent: Intent
        get() {
            val mapIntent = Intent(Intent.ACTION_VIEW, parsedUri)
            mapIntent.`package` = "com.google.android.apps.maps"
            return mapIntent
        }

    private val parsedUri: Uri
        get() {
            val mapApplicationUri = mapsApplicationUri
            return Uri.parse(mapApplicationUri)
        }

    private val mapsApplicationUri: String
        get() {
            val latitude = 52.221897
            val longitude = 21.03841
            val label = "Wspólnoty Jerozolimskie"
            val uriBegin = "geo:$latitude,$longitude"
            val query = latitude.toString() + "," + longitude + "(" + label + ")"
            val encodedQuery = Uri.encode(query)
            return "$uriBegin?q=$encodedQuery&z=15"
        }

    private val MAPS_URL = "https://www.google.pl/maps/place/Wspólnoty+Jerozolimskie/@52.221897,21.03841,15z"

}
