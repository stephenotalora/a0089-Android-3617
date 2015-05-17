package com.example.jono.activities;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;


public class Test2_DoubleRotateActivityOneTest extends
        ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;
    private int timeout = 5000;
    private int sleep = 1000;

    public Test2_DoubleRotateActivityOneTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    protected void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    public void testRun() {
        // ==================== Section One =====================
        // Wait for activity: 'course.labs.activitylab.ActivityOne'
        assertTrue("DoubleRotateActivityOneTest failed: " +
                        "Section One:" +
                        "ActivityOne did not correctly load",
                solo.waitForActivity(MainActivity.class, timeout));


        solo.sleep(sleep);

        // ==================== Section Two =====================
        // Rotate the screen
        solo.setActivityOrientation(Solo.LANDSCAPE);

        // Wait for activity: 'course.labs.activitylab.ActivityOne'
        assertTrue("DoubleRotateActivityOneTest failed:" +
                        "Section Two:" +
                        "ActivityOne did not correctly load after first LANDSCAPE rotation.",
                solo.waitForActivity(MainActivity.class, timeout));


        solo.sleep(sleep);

        // ==================== Section Three =====================
        // Rotate the screen
        solo.setActivityOrientation(Solo.PORTRAIT);

        // Wait for activity: 'course.labs.activitylab.ActivityOne'
        assertTrue("DoubleRotateActivityOneTest failed:" +
                        "Section Three:" +
                        "ActivityOne did not correctly load after second PORTRAIT rotation.",
                solo.waitForActivity(MainActivity.class, timeout));

        solo.sleep(sleep);

        // Check for proper counts
        assertTrue("DoubleRotateActivityOneTest failed:" +
                        "Section Three:" +
                        "onCreate() count was off for ActivityOne after second PORTRAIT rotation.",
                solo.searchText("onCreate\\(\\) calls: 3"));
        assertTrue("DoubleRotateActivityOneTest failed:" +
                        "Section Three:" +
                        "onStart() count was off for ActivityOne after second PORTRAIT rotation.",
                solo.searchText("onStart\\(\\) calls: 3"));
        assertTrue("DoubleRotateActivityOneTest failed:" +
                        "Section Three:" +
                        "onResume() count was off for ActivityOne after second PORTRAIT rotation.",
                solo.searchText("onResume\\(\\) calls: 3"));
        assertTrue("DoubleRotateActivityOneTest failed:" +
                        "Section Three:" +
                        "onRestart() count was off for ActivityOne after second PORTRAIT rotation.",
                solo.searchText("onRestart\\(\\) calls: 0"));
    }

}
