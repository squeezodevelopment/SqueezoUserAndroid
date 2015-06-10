package com.squeezo.android.user.outletListing;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence titles[];
    int noOfTabs;

    public ViewPagerAdapter(FragmentManager fm, CharSequence titles[], int noOfTabs) {
        super(fm);

        this.titles = titles;
        this.noOfTabs = noOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0)
            return new InfoTab();
        else if (position == 1)
            return new MenuTab();
        else if (position == 2)
            return new PhotosTab();
        else if (position == 3)
            return new ReviewsTab();
        else
            return new OffersTab();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return noOfTabs;
    }
}
