package com.praticing.yametee.fragments;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyViewPagerAdapter extends FragmentStateAdapter {

    public MyViewPagerAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);

    }

    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new WhoBorrow();
            case 1:
                return new WhoReturn();
            default:
                return new WhoBorrow();
        }
    }

    @Override
    public int getItemCount() { return 2; }
}