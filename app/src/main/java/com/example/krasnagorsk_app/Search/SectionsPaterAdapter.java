package com.example.krasnagorsk_app.Search;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/*
Class that stores fragments for tabs
 */

public class SectionsPaterAdapter extends FragmentPagerAdapter {

    private static final String TAG="SectionsPaterAdapter";
    private final List<Fragment> mFragmentList=new ArrayList<>();



    public SectionsPaterAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment)
    {
        mFragmentList.add(fragment);
    }

}
