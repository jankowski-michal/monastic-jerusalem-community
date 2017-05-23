/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.utilities

import java.util.*

class TodaysMassCalculator {


    fun calculateTodaysMass(): String {
        val c = Calendar.getInstance()
        return getMassFor(c)
    }

    fun getMassFor(c: Calendar): String {
        val dayOfWeek = c.get(Calendar.DAY_OF_WEEK)
        when (dayOfWeek) {
            Calendar.MONDAY -> return MONDAY_MASS
            Calendar.TUESDAY -> return WEEK_DAYS_MASS
            Calendar.WEDNESDAY -> return WEEK_DAYS_MASS
            Calendar.THURSDAY -> return WEEK_DAYS_MASS
            Calendar.FRIDAY -> return WEEK_DAYS_MASS
            Calendar.SATURDAY -> return WEEK_DAYS_MASS
            Calendar.SUNDAY -> return SUNDAYS_MASS
        }
        return NO_INFO
    }

    val MONDAY_MASS = "\"Dziś nie ma mszy św\""

    val WEEK_DAYS_MASS = "Dziś msza św o 18:30"

    val SUNDAYS_MASS = "Dziś msza św o 11:00"

    val NO_INFO = "Brak informacji o mszy św"

}
