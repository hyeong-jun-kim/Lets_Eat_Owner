package org.techtown.letseat.restaurant;

import org.techtown.letseat.R;

import java.util.ArrayList;

public class RestaurantData {

    ArrayList<RestaurantItem> items = new ArrayList<>();

    public ArrayList<RestaurantItem> getItems() {

        RestaurantItem order1 = new RestaurantItem(R.drawable.image1,"하오탕");

        RestaurantItem order2 = new RestaurantItem(R.drawable.image2, "뼈대있는 가문");

        RestaurantItem order3 = new RestaurantItem(R.drawable.image3, "곱순네 순대국");

        items.add(order1);
        items.add(order2);
        items.add(order3);

        return items;
    }

}