package org.techtown.letseat.store;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import org.techtown.letseat.R;
import org.techtown.letseat.util.ViewPagerAdapter;


public class Store_Item_Main extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private Store_Item_info_frag store_Item_info_frag;
    private Store_Item_Menu_frag store_Item_Menu_frag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_item);

        viewPager = findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(3);

        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        store_Item_info_frag = new Store_Item_info_frag();
        store_Item_Menu_frag = new Store_Item_Menu_frag();
        Store_Item_Review_frag store_Item_review_frag = new Store_Item_Review_frag();

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        viewPagerAdapter.addFragment(store_Item_info_frag, "가게 정보");
        viewPagerAdapter.addFragment(store_Item_Menu_frag, "메뉴 설정");
        viewPagerAdapter.addFragment(store_Item_review_frag, "리뷰 관리");

        viewPager.setAdapter(viewPagerAdapter);


        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {


            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                super.onTabReselected(tab);
            }
        });

    }
}
