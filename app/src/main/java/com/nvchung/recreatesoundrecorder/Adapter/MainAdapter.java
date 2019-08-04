package com.nvchung.recreatesoundrecorder.Adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.nvchung.recreatesoundrecorder.View.FragmentRecoderGellary;
import com.nvchung.recreatesoundrecorder.View.FragmentRecorder;

public class MainAdapter extends FragmentPagerAdapter {

    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    private String[] title = {"Recorder", "History"};

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            //f1
            return FragmentRecorder .newInstance(null, null);
        }else {
            //f2
            return FragmentRecoderGellary.newInstance(null,null);

        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
