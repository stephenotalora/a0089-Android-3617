package com.example.jono.activities;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by jono on 15-05-16.
 * ParentActivity for Activity Classes in Lab Week 2
 */
public class ParentActivity extends Activity {
    protected Button mIntentButton;
    protected TextView mTextView1, mTextView2, mTextView3, mTextView4;
    protected int mOnCreateCount, mOnStart, mOnRestart, mOnResume, mOnPause, mOnStop, mOnDestroy;

    // Use these as keys when you're saving state between reconfigurations
    protected static final String RESTART_KEY = "restart";
    protected static final String RESUME_KEY = "resume";
    protected static final String START_KEY = "start";
    protected static final String CREATE_KEY = "create";

    private String buildDisplayString(int resId, int val) {
        return getResources().getString(resId) + " " + val;
    }

    private void displayCounts() {
        // init Content for views
        mTextView1.setText(buildDisplayString(R.string.on_create, mOnCreateCount));
        mTextView2.setText(buildDisplayString(R.string.on_start, mOnStart));
        mTextView3.setText(buildDisplayString(R.string.on_resume, mOnResume));
        mTextView4.setText(buildDisplayString(R.string.on_restart, mOnRestart));
    }

    @Override
    protected void onStart() {
        super.onStart();
        ++mOnStart;
    }

    @Override
    protected void onResume() {
        super.onResume();
        ++mOnResume;
        displayCounts();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        ++mOnRestart;
    }

    @Override
    protected void onPause() {
        super.onPause();
        ++mOnPause;
    }

    @Override
    protected void onStop() {
        super.onStop();
        ++mOnStop;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ++mOnDestroy;
    }
}
