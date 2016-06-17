package com.iappdragon.recruitmenttest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.iappdragon.recruitmenttest.Utilities.AsyncTaskHandler;
import com.iappdragon.recruitmenttest.Utilities.AsyncTaskListener;

public class ForthBugActivity extends AppCompatActivity implements AsyncTaskListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_forth_bug);

        AsyncTaskHandler asyncTaskHandler = new AsyncTaskHandler(this, AsyncTaskHandler.GET_INFO);
        asyncTaskHandler.execute();
    }

    @Override
    public void onPreExecute(int action_id) {

    }

    @Override
    public void onPostExecute(String result, int responseCode, int action_id) {


    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onInternetNotAvailable(String result, int action_id) {

    }
}
