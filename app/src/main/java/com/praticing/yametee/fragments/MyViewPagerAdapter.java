package com.praticing.yametee.fragments;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
public class MyViewPagerAdapter extends FragmentStateAdapter {
    @SuppressLint("ResourceType")
    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);

    }
    @NonNull
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