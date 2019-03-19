package com.ardiya.simpleweather;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

public class MpagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public MpagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        String cityName = "Singapore";
        switch (position) {
            case 0:
                return DailyFragment.newInstance(cityName);
            case 1:
                return new SpeedometerFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    private static class Item {
        private final int color;

        private Item(int color) {
            this.color = color;
        }
    }
}
