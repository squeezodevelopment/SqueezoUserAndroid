package com.squeezo.android.user.intro;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.squeezo.android.user.R;

import me.relex.circleindicator.CircleIndicator;

public class IntroFragment extends android.support.v4.app.Fragment {

    private FragmentManager manager;
    private FragmentTransaction transaction;

    public IntroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_intro, container, false);

        manager = getFragmentManager();

        Button btnSignIn = (Button) view.findViewById(R.id.buttonLogInIntro);
        Button btnRegister = (Button) view.findViewById(R.id.buttonRegisterIntro);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity());
        ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(adapter);

        CircleIndicator indicator = (CircleIndicator) view.findViewById(R.id.indicator);
        indicator.setViewPager(pager);

        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignInFragment signInFragment = new SignInFragment();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.relativeLayoutIntro, signInFragment, "signInFragment");
                transaction.addToBackStack("signIn");
                transaction.commit();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpFragment signUpFragment = new SignUpFragment();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.relativeLayoutIntro, signUpFragment, "signUpFragment");
                transaction.addToBackStack("signUp");
                transaction.commit();
            }
        });

        return view;
    }


}
