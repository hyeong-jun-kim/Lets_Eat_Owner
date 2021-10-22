package org.techtown.letseat;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import org.techtown.letseat.order.OrderFrag;
import org.techtown.letseat.restaurant.RestaurantFragment;
import org.techtown.letseat.util.ViewPagerAdapter;
import org.techtown.letseat.waiting.WaitingFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private RestaurantFragment RestaurantFragment;
    private OrderFrag orderFrag;
    private WaitingFragment WaitingFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = getIntent();
        String ownerId = intent.getStringExtra("ownerId");



        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(3);

        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        RestaurantFragment = new RestaurantFragment();
        orderFrag = new OrderFrag();
        WaitingFragment = new WaitingFragment();

        Bundle bundle = new Bundle();
        bundle.putString("ownerId",ownerId);
        orderFrag.setArguments(bundle);
        Log.d("ds","ds");

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        viewPagerAdapter.addFragment(RestaurantFragment,"가게 관리");
        viewPagerAdapter.addFragment(orderFrag,"주문 관리");
        viewPagerAdapter.addFragment(WaitingFragment,"대기자 관리");

        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.getTabAt(0).setIcon(R.drawable.storeicon);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_fastfood_24);
        tabLayout.getTabAt(2).setIcon(R.drawable.waiting);

        tabLayout.getTabAt(0).getIcon().setColorFilter(Color.parseColor("#ED6868"),PorterDuff.Mode.SRC_IN);

        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager){

            @Override
            public void onTabSelected(@NonNull TabLayout.Tab tab) {
                super.onTabSelected(tab);
                tab.getIcon().setColorFilter(Color.parseColor("#ED6868"), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                super.onTabUnselected(tab);
                tab.getIcon().setColorFilter(Color.parseColor("#89000000"), PorterDuff.Mode.SRC_IN);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                super.onTabReselected(tab);
            }
        });
    }
}