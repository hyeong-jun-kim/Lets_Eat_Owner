package org.techtown.letseat.waiting;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.techtown.letseat.R;
import org.techtown.letseat.RestItemReviewItem;

import java.util.ArrayList;

public class WaitingSample {

    ArrayList<Waiting> items = new ArrayList<>();

    public ArrayList<Waiting> getItems() {

        Waiting order1 = new Waiting("2021/10/28","1","tjaudwls","( 4 ) 명","010-9435-6257",R.drawable.image1);

        Waiting order2 = new Waiting("2021/10/28","2","rlagudwns","( 5 ) 명","010-2529-1674",R.drawable.image2);

        Waiting order3 = new Waiting("2021/10/28","3","dksxogus","( 6 ) 명","010-6238-1812",R.drawable.image3);

        items.add(order1);
        items.add(order2);
        items.add(order3);

        return items;
    }
}
