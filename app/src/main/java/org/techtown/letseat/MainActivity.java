package org.techtown.letseat;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private storeFrag storeFrag;
    private orderFrag orderFrag;
    private waitingFrag waitingFrag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(3);

        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        storeFrag = new storeFrag();
        orderFrag = new orderFrag();
        waitingFrag = new waitingFrag();

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        viewPagerAdapter.addFragment(storeFrag,"가게 관리");
        viewPagerAdapter.addFragment(orderFrag,"주문 관리");
        viewPagerAdapter.addFragment(waitingFrag,"대기자 관리");

        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.getTabAt(0).setIcon(R.drawable.storeicon);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_fastfood_24);
        tabLayout.getTabAt(2).setIcon(R.drawable.waiting);

    }


}