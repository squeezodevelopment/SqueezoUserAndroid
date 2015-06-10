package com.squeezo.android.user.userProfile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squeezo.android.user.R;


public class ProfileActivity extends AppCompatActivity {

    private android.support.v4.app.FragmentManager manager;
    private int currentFragmentPosition = 0;
    private int oldFragmentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button btnAboutProfile = (Button) findViewById(R.id.buttonAboutProfile);
        Button btnLocationProfile = (Button) findViewById(R.id.buttonLocationProfile);

        TextView textViewChangePictureProfile = (TextView) findViewById(R.id.textViewChangePictureProfile);
        SpannableString changePicture = new SpannableString("Change");
        changePicture.setSpan(new UnderlineSpan(), 0, changePicture.length(), 0);
        textViewChangePictureProfile.setText(changePicture);

        manager = getSupportFragmentManager();

        btnAboutProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentFragmentPosition = 1;

                ProfileAboutFragment aboutFragment = new ProfileAboutFragment();
                android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();

                /*if(oldFragmentPosition == 2 || oldFragmentPosition == 3)
                    transaction.setCustomAnimations(R.animator.slide_out_right,R.animator.slide_in_left);*/

                oldFragmentPosition = 1;

                transaction.replace(R.id.relativeLayoutFragmentProfile, aboutFragment, "AboutFragment");
                transaction.commit();
            }
        });

        btnLocationProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentFragmentPosition = 3;

                ProfileLocationFragment locationFragment = new ProfileLocationFragment();
                android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();

                /*if(oldFragmentPosition == 1 || oldFragmentPosition == 2)
                    transaction.setCustomAnimations(R.animator.slide_in_left,R.animator.slide_out_right);*/

                oldFragmentPosition = 3;

                transaction.replace(R.id.relativeLayoutFragmentProfile, locationFragment, "LocationFragment");
                transaction.commit();
            }
        });

    }

}
