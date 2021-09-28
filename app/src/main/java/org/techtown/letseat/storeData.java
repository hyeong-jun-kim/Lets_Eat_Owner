package org.techtown.letseat;

import org.techtown.letseat.R;

import java.util.ArrayList;

public class storeData {

    ArrayList<storeItem> items = new ArrayList<>();

    public ArrayList<storeItem> getItems() {

        storeItem order1 = new storeItem(R.drawable.image1,"하오탕");

        storeItem order2 = new storeItem(R.drawable.image2, "뼈대있는 가문");

        storeItem order3 = new storeItem(R.drawable.image3, "곱순네 순대국");

        items.add(order1);
        items.add(order2);
        items.add(order3);

        return items;
    }
}