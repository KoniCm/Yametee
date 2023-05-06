package com.praticing.yametee.fragments;

import android.annotation.SuppressLint;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyViewPagerAdapter extends FragmentStateAdapter
{

    @SuppressLint("ResourceType")
    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity)
    {
        super(fragmentActivity);

    }
    @NonNull
    @Override
    public Fragment createFragment(int position)
    {
        switch (position)
        {
            case 0:
                return new BorrowingActivity();
            case 1:
                return new ReturningActivity();
            default:
                return new BorrowingActivity();
        }
    }
    @Override
    public int getItemCount()
    {
        return 2;
    }
}