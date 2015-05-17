package com.example.jono.activities;

import android.test.ActivityInstrumentationTestCase2;
import com.robotium.solo.Solo;

public class Test6_ReopenActivityTwoTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;
    private int timeout = 20000;
    private int sleep = 1000;

    public Test6_ReopenActivityTwoTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    protected void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    // Executes the ReopenActivityTwoTest
    public void testRun() {

        // ==================== Section One =====================
        // Wait for activity: 'course.labs.activitylab.ActivityOne'
        assertTrue("ReopenActivityTwoTest failed:" +
                        "Section One:" +
                        "ActivityOne did not load correctly",
                solo.waitForActivity(MainActivity.class, timeout));

        solo.waitForView(R.id.launchActivity);

        // ==================== Section Two =====================
        // Click on Start Activity Two
        solo.clickOnView(solo.getView(R.id.launchActivity));

        // Wait for activity: 'course.labs.activitylab.ActivityTwo'
        assertTrue("ReopenActivityTwoTest failed:" +
                        "Section Two:" +
                        "ActivityTwo did not load correctly",
                solo.waitForActivity(SecondaryActivity.class, timeout));

        solo.waitForView(R.id.closeButton);

        solo.sleep(sleep);

        // ==================== Section Three =====================
        // Click on Close Activity
        solo.clickOnView(solo.getView(R.id.closeButton));

        // Wait for activity: 'course.labs.activitylab.ActivityOne'
        assertTrue("ReopenActivityTwoTest failed:" +
                        "Section Three:" +
                        "ActivityTwo did not close correctly",
                solo.waitForActivity(MainActivity.class, timeout));

        solo.waitForView(R.id.launchActivity);

        solo.sleep(sleep);

        // ==================== Section Four =====================
        // Click on Start Activity Two
        solo.clickOnView(solo.getView(R.id.launchActivity));

        // Wait for activity: 'course.labs.activitylab.ActivityTwo'
        assertTrue("ReopenActivityTwoTest failed:" +
                        "Section Four:" +
                        "ActivityTwo did not reopen correctly after being closed",
                solo.waitForActivity(SecondaryActivity.class, timeout));

        solo.waitForView(R.id.closeButton);

        // Check for proper counts
        assertTrue("ReopenActivityTwoTest failed:" +
                        "Section Four:" +
                        "onCreate() count was off for ActivityTwo after being reopened for a second time.",
                solo.waitForText("onCreate\\(\\) calls: 1"));

        assertTrue("ReopenActivityTwoTest failed:" +
                        "Section Four:" +
                        "onStart() count was off for ActivityTwo after being reopened for a second time.",
                solo.waitForText("onStart\\(\\) calls: 1"));

        assertTrue("ReopenActivityTwoTest failed:" +
                        "Section Four:" +
                        "onResume() count was off for ActivityTwo after being reopened for a second time.",
                solo.waitForText("onResume\\(\\) calls: 1"));

        assertTrue("ReopenActivityTwoTest failed:" +
                        "Section Four:" +
                        "onRestart() count was off for ActivityTwo after being reopened for a second time.",
                solo.waitForText("onRestart\\(\\) calls: 0"));


    }

}
