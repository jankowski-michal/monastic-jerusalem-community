/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.data_fetch.entity


import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import pl.orbitemobile.wspolnoty.utilities.TodaysMassCalculator
import java.util.*

class MassCalculatorTest {
    private val massCalculator = TodaysMassCalculator()

    @Test
    @Throws(Exception::class)
    fun testDataResponseCreation_valid() {
        val calendar = mock(Calendar::class.java)

        `when`(calendar.get(Calendar.DAY_OF_WEEK)).thenReturn(Calendar.MONDAY)
        assertEquals(massCalculator.getMassFor(calendar), massCalculator.MONDAY_MASS)


        `when`(calendar.get(Calendar.DAY_OF_WEEK)).thenReturn(Calendar.TUESDAY)
        assertEquals(massCalculator.getMassFor(calendar), massCalculator.WEEK_DAYS_MASS)

        `when`(calendar.get(Calendar.DAY_OF_WEEK)).thenReturn(Calendar.WEDNESDAY)
        assertEquals(massCalculator.getMassFor(calendar), massCalculator.WEEK_DAYS_MASS)

        `when`(calendar.get(Calendar.DAY_OF_WEEK)).thenReturn(Calendar.THURSDAY)
        assertEquals(massCalculator.getMassFor(calendar), massCalculator.WEEK_DAYS_MASS)

        `when`(calendar.get(Calendar.DAY_OF_WEEK)).thenReturn(Calendar.FRIDAY)
        assertEquals(massCalculator.getMassFor(calendar), massCalculator.WEEK_DAYS_MASS)

        `when`(calendar.get(Calendar.DAY_OF_WEEK)).thenReturn(Calendar.SATURDAY)
        assertEquals(massCalculator.getMassFor(calendar), massCalculator.WEEK_DAYS_MASS)

        `when`(calendar.get(Calendar.DAY_OF_WEEK)).thenReturn(Calendar.SUNDAY)
        assertEquals(massCalculator.getMassFor(calendar), massCalculator.SUNDAYS_MASS)
    }


}
