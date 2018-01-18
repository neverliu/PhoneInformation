package com.megafone.PhoneInformation;

import java.util.ArrayList;
import java.util.List;

import com.megafone.PhoneInformation.R;
import com.megafone.PhoneInformation.adapter.BaseFragment;
import com.megafone.PhoneInformation.factory.FragmentFactory;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {
    private CategoryTabStrip tabs;
    private ViewPager pager;
    private MyPagerAdapter adapter;
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabs = (CategoryTabStrip) findViewById(R.id.category_strip);
        pager = (ViewPager) findViewById(R.id.view_pager);
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        context = this;
        pager.setAdapter(adapter);

        tabs.setViewPager(pager);

    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        private final List<String> catalogs = new ArrayList<String>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            catalogs.add(getString(R.string.sor));
//            catalogs.add(getString(R.string.device));
            catalogs.add(getString(R.string.system));
            catalogs.add(getString(R.string.TP_SENSOR_LCD));
//            catalogs.add(getString(R.string.seneors));
//            catalogs.add(getString(R.string.tcd));
            catalogs.add(getString(R.string.about));
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return catalogs.get(position);
        }

        @Override
        public int getCount() {
            return catalogs.size();
        }

        @Override
        public Fragment getItem(int position) {
			BaseFragment fragment = FragmentFactory.getFragment(position,context);
			return fragment;
        }

    }

}
