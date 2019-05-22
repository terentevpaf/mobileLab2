package com.example.lab2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

public class Detail extends FragmentActivity {
    static final String TAG = "myLogs";
    static int PAGE_COUNT = 10;

    ViewPager pager;
    PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String jsonArray = intent.getStringExtra("json");
        String num = intent.getStringExtra("num");
        String countItem = intent.getStringExtra("countItem");
        PAGE_COUNT = new Integer(countItem);

        pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), this, jsonArray);
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(new Integer(num));

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected, position = " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        private Context context;
        public MyFragmentPagerAdapter(FragmentManager fm, Context cont, String json) {
            super(fm);
            this.context = cont;
            PageFragment.JsonData(json);
        }

        @Override
        public Fragment getItem(int position) {
            return PageFragment.newInstance(position, context);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }
    }
}
