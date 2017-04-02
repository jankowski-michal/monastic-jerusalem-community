/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.data_fetch.entity


import org.junit.Test
import pl.orbitemobile.kotler.Specification
import pl.orbitemobile.wspolnoty.utilities.TodaysMassCalculator
import java.util.*

class MassCalculatorTest : Specification() {
    private val massCalculator = TodaysMassCalculator()
    val calendar = Calendar::class.mock

    @Test
    fun kotler_test() {
        Where(data(Calendar.MONDAY, massCalculator.MONDAY_MASS),
                data(Calendar.TUESDAY, massCalculator.WEEK_DAYS_MASS),
                data(Calendar.WEDNESDAY, massCalculator.WEEK_DAYS_MASS),
                data(Calendar.THURSDAY, massCalculator.WEEK_DAYS_MASS),
                data(Calendar.FRIDAY, massCalculator.WEEK_DAYS_MASS),
                data(Calendar.SATURDAY, massCalculator.WEEK_DAYS_MASS),
                data(Calendar.SUNDAY, massCalculator.SUNDAYS_MASS))
        { (dayOfWeek, expectedMessage) ->

            Given
            calendar.get(Calendar.DAY_OF_WEEK) thenReturn dayOfWeek

            When
            val foundMessage = massCalculator.getMassFor(calendar)

            Then
            foundMessage equals expectedMessage
        }
    }
}
