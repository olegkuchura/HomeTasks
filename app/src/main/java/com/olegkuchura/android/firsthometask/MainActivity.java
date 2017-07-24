package com.olegkuchura.android.firsthometask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.olegkuchura.android.firsthometask.fragments.ConfirmFragment;
import com.olegkuchura.android.firsthometask.fragments.FirstFragment;
import com.olegkuchura.android.firsthometask.fragments.RejectFragment;

public class MainActivity extends AppCompatActivity {
    private static final String activityName = MainActivity.class.getSimpleName();

    private FragmentManager fragmentManager;

    private String fragmentForReplace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentForReplace = "";

        fragmentManager = getSupportFragmentManager();

        if ( fragmentManager.findFragmentById(R.id.ll_fragment_container) == null) {
            Log.d("MyLog", activityName + ".onCreate() Creating FirstFragment");
            Fragment fragment = new FirstFragment();
            FragmentTransaction transition = fragmentManager.beginTransaction();
            transition.add(R.id.ll_fragment_container, fragment, "FirstFragment");
            transition.commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        switch (fragmentForReplace) {
            case "ConfirmFragment":
                if (fragmentManager.findFragmentByTag("ConfirmFragment") == null) {
                    Fragment confirmFragment = new ConfirmFragment();
                    FragmentTransaction transition = fragmentManager.beginTransaction();
                    transition.replace(R.id.ll_fragment_container, confirmFragment, "ConfirmFragment");
                    transition.addToBackStack("replaceConfirmFragment");
                    transition.commit();
                }
                break;
            case "RejectFragment":
                if (fragmentManager.findFragmentByTag("RejectFragment") == null) {
                    Fragment rejectFragment = new RejectFragment();
                    FragmentTransaction transition = fragmentManager.beginTransaction();
                    transition.replace(R.id.ll_fragment_container, rejectFragment, "RejectFragment");
                    transition.addToBackStack("replaceRejectFragment");
                    transition.commit();
                }
                break;
        }
        fragmentForReplace = "";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode, data);
        Log.d("MyLog", activityName + ".onActivityResult() requestCode=" + requestCode + " resultCode=" + resultCode);
        if (requestCode == SecondActivity.REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                if (data == null) return;
                if (data.getBooleanExtra("isConfirmed", false)) {
                    FirstFragment fragment = (FirstFragment) fragmentManager.findFragmentByTag("FirstFragment");
                    if (fragment != null) fragment.clearEditText();
                    fragmentForReplace = "ConfirmFragment";
                } else {
                    fragmentForReplace = "RejectFragment";
                }
            } else {
                fragmentForReplace = "RejectFragment";
            }
        }
    }
}