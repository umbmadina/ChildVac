package com.example.madina.childvac.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.madina.childvac.fragments.ListFragment;
import com.example.madina.childvac.fragments.PrescriptionsFragment;

public class FragmentListAdapter extends FragmentPagerAdapter {

     public FragmentListAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new PrescriptionsFragment();

            case 1:
                return new ListFragment();
            case 2:
                return new PrescriptionsFragment();
            case 3:
                return new ListFragment();
            case 4:
                return new PrescriptionsFragment();
            default:
                break;
        }

        return null;

//
//         ListFragment listFragment = new ListFragment();
//         Bundle bundle = new Bundle();
//        position += 1;
//         bundle.putString("message", "Hello from Page " + position);
//         listFragment.setArguments(bundle);
    }

    @Override
    public int getCount() {
        return 5;
    }
}
