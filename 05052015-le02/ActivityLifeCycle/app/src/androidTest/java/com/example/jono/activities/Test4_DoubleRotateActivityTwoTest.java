package com.example.jono.activities;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

public class Test4_DoubleRotateActivityTwoTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;
    private int timeout = 20000;
    private int sleep = 1000;

    public Test4_DoubleRotateActivityTwoTest() {
        super(MainActivity.class);
    }

    protected void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    protected void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    // Exectues the DoubleRotateActivityTwoTest
    public void testRun() {
        // ==================== Section One =====================
        // Wait for activity: 'course.labs.activitylab.ActivityOne'
        assertTrue("DoubleRotateActivityTwoTest failed:" +
                        "Section One:" +
                        "ActivityOne did not load correctly",
                solo.waitForActivity(MainActivity.class, timeout));


        // ==================== Section Two =====================
        // Click on Start Activity Two
        solo.waitForView(R.id.closeButton);
        solo.clickOnView(solo
                .getView(R.id.closeButton));

        // Wait for activity: 'course.labs.activitylab.ActivityTwo'
        assertTrue("DoubleRotateActivityTwoTest failed:" +
                        "Section Two:" +
                        "ActivityTwo did not load correctly",
                solo.waitForActivity(SecondaryActivity.class, timeout));


        solo.waitForView(R.id.closeButton);

        // ==================== Section Three =====================
        // Rotate the screen
        solo.setActivityOrientation(Solo.LANDSCAPE);

        // Wait for activity: 'course.labs.activitylab.ActivityTwo'
        assertTrue("DoubleRotateActivityTwoTest failed:" +
                        "Section Three:" +
                        "ActivityTwo did not correctly load after first LANDSCAPE rotation.",
                solo.waitForActivity(SecondaryActivity.class, timeout));

        solo.waitForView(R.id.closeButton);

        // ==================== Section Four =====================
        // Rotate the screen
        solo.setActivityOrientation(Solo.PORTRAIT);

        // Wait for activity: 'course.labs.activitylab.ActivityTwo'
        assertTrue("DoubleRotateActivityTwoTest failed:" +
                        "Section Four:" +
                        "ActivityTwo did not correctly load after second PORTRAIT rotation.",
                solo.waitForActivity(SecondaryActivity.class, timeout));

        solo.waitForView(R.id.closeButton);

        // Check for proper counts
        assertTrue("DoubleRotateActivityTwoTest failed:" +
                        "Section Four:" +
                        "onCreate() count was off for ActivityTwo after second PORTRAIT rotation.",
                solo.waitForText("onCreate\\(\\) calls: 3"));

        assertTrue("DoubleRotateActivityTwoTest failed:" +
                        "Section Four:" +
                        "onStart() count was off for ActivityTwo after second PORTRAIT rotation.",
                solo.waitForText("onStart\\(\\) calls: 3"));

        assertTrue("DoubleRotateActivityTwoTest failed:" +
                        "Section Four:" +
                        "onResume() count was off for ActivityTwo after second PORTRAIT rotation.",
                solo.waitForText("onResume\\(\\) calls: 3"));

        assertTrue("DoubleRotateActivityTwoTest failed:" +
                        "Section Four:" +
                        "onRestart() count was off for ActivityTwo after second PORTRAIT rotation.",
                solo.waitForText("onRestart\\(\\) calls: 0"));
    }

}