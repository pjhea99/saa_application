package com.grotesque.saa.board.adapter;


import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.grotesque.saa.board.SpecialFragment;
import com.grotesque.saa.home.data.DocumentList;

import java.util.ArrayList;

public class VerticalFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private static final String TAG = VerticalFragmentPagerAdapter.class.getSimpleName();
    private ArrayList<DocumentList> mArrayList;

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        container.removeView(((SpecialFragment) object).getView());    }

    public VerticalFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public VerticalFragmentPagerAdapter(FragmentManager fm, ArrayList<DocumentList> mArrayList) {
        super(fm);
        this.mArrayList = mArrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return SpecialFragment.newInstance(mArrayList.get(position));
    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }
}