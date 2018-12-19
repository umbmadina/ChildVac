package com.example.madina.childvac.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.madina.childvac.fragments.ListFragment;
import com.example.madina.childvac.fragments.MapFragment;
import com.example.madina.childvac.fragments.PrescriptionsFragment;
import com.example.madina.childvac.fragments.ScheduleFragment;
import com.example.madina.childvac.fragments.SettingsFragment;
import com.example.madina.childvac.fragments.TicketFragment;

public class FragmentListAdapter extends FragmentPagerAdapter {

     public FragmentListAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ScheduleFragment();
            case 1:
                return new PrescriptionsFragment();
            case 2:
                return new TicketFragment();
            case 3:
                return new MapFragment();
            default:
                break;
        }

        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
