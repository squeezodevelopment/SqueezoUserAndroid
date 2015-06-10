package com.squeezo.android.user.intro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.squeezo.android.user.R;


public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();

        IntroFragment introFragment = new IntroFragment();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.relativeLayoutIntro, introFragment, "introFragment");
        transaction.commit();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == SignInFragment.RC_SIGN_IN || requestCode == 64206) {

            Log.d("activity result act gp", "req code = " + requestCode + " rescode = " + resultCode);
            SignInFragment fragment = (SignInFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.relativeLayoutIntro);
            fragment.onActivityResult(requestCode, resultCode, data);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
