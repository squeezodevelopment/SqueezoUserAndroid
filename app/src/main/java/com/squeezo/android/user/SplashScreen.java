package com.squeezo.android.user;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;

import com.squeezo.android.user.intro.IntroActivity;
import com.squeezo.android.user.main.MainActivity;
import com.squeezo.android.user.notificationsSettings.NotificationSettingsActivity;
import com.squeezo.android.user.outletListing.OutletListingActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SplashScreen extends AppCompatActivity {

    private boolean initiated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        SharedPreferences pref = getSharedPreferences("first_time", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        if (pref.contains("initiated"))
            initiated = true;

        editor.putString("initiated", "true");
        editor.apply();

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.squeezo.android.user",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                System.out.println("KeyHash : " + Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        Thread background = new Thread() {
            public void run() {

                try {
                    // Thread will sleep for 2 seconds
                    sleep(2 * 1000);

                    // After 5 seconds redirect to another intent

                    if (!initiated) {
                        Intent i = new Intent(SplashScreen.this, OutletListingActivity.class); //introactivity
                        startActivity(i);
                    } else {
                        Intent i = new Intent(SplashScreen.this, OutletListingActivity.class); //loginactivity
                        startActivity(i);
                    }

                    //Remove activity
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // start thread
        background.start();

    }

}
