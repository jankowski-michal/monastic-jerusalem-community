/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.article;

import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.orbitemobile.wspolnoty.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
@SmallTest
public class LogHistoryUnitTest {
    //todo: kotler - add android library
    //todo: kotler - add extending InstrumentedTest and extend from:
    /*
    * - unit
    * - instrumented unit
    * - integration
    * */
    //todo: add extensions for hamcrest so this can be done smoothly

    @Rule
    public ActivityTestRule<ArticleActivity> mActivityRule = new ActivityTestRule<>(ArticleActivity.class);

    @Test
    public void logHistory_ParcelableWriteRead() {
        // Set up the Parcelable object to send and receive.
        onView(withId(R.id.error_button)).check(matches(withText("Nie udało się pobrać danych.\\nKliknij aby spróbować ponownie.")));

        // Verify that the received data is correct.
//        assertThat(createdFromParcelData.size(), is(1));
//        assertThat(createdFromParcelData.get(0).first, is(TEST_STRING));
//        assertThat(createdFromParcelData.get(0).second, is(TEST_LONG));
    }
}
