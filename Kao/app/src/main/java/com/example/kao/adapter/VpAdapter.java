package com.example.kao.adapter;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class VpAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment>fragments;
    private ArrayList<String>titles;
//viewpager适配
    public VpAdapter(@NonNull FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);//当前展示的fragment
    }

    @Override
    public int getCount() {
        return fragments.size();//fragement的数量
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);//tablayout的标题
    }
}
