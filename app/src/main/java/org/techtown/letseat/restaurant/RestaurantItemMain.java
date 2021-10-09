package org.techtown.letseat.restaurant;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import org.techtown.letseat.R;
import org.techtown.letseat.util.ViewPagerAdapter;


public class RestaurantItemMain extends AppCompatActivity {
    public static int resId;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private RestaurantItemInfoFragment restaurant_Item_Info_Fragment;
    private RestaurantItemMenuFragment restaurant_Item_Menu_fragment;
    private RestaurantItemReviewFragment restaurant_Item_review_fragment;

    public static int getResId() {
        return resId;
    }

    @Override
    protected void onCreate(Bundle saveBundle) {
        super.onCreate(saveBundle);
        setContentView(R.layout.restaurant_item);
        viewPager = findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(3);

        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        restaurant_Item_Info_Fragment = new RestaurantItemInfoFragment();
        restaurant_Item_Menu_fragment = new RestaurantItemMenuFragment();
        restaurant_Item_review_fragment = new RestaurantItemReviewFragment();

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        viewPagerAdapter.addFragment(restaurant_Item_Info_Fragment,"가게 정보");
        viewPagerAdapter.addFragment(restaurant_Item_Menu_fragment,"메뉴 설정");
        viewPagerAdapter.addFragment(restaurant_Item_review_fragment,"리뷰 관리");

        viewPager.setAdapter(viewPagerAdapter);


        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager){


            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                super.onTabReselected(tab);
            }
        });

    }
}
