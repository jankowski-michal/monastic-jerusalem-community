/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.home.logic;

import java.util.Calendar;

public class TodaysMassCalculator {
    
    private static final String MONDAY_MASS = "\"Dziś nie ma mszy św\"";
    
    private static final String WEEK_DAYS_MASS = "Dziś msza św o 18:30";
    
    private static final String SUNDAYS_MASS = "Dziś msza św o 11:00";
    
    private static final String NO_INFO = "Brak informacji o mszy św";
    
    public String calculateTodaysMass() {
        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek) {
            case Calendar.MONDAY:
                return MONDAY_MASS;
            case Calendar.TUESDAY:
                return WEEK_DAYS_MASS;
            case Calendar.WEDNESDAY:
                return WEEK_DAYS_MASS;
            case Calendar.THURSDAY:
                return WEEK_DAYS_MASS;
            case Calendar.FRIDAY:
                return WEEK_DAYS_MASS;
            case Calendar.SATURDAY:
                return WEEK_DAYS_MASS;
            case Calendar.SUNDAY:
                return SUNDAYS_MASS;
        }
        return NO_INFO;
    }
}
