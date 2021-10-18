package org.techtown.letseat.restaurant;

import android.graphics.Bitmap;

public class RestaurantItem {
    private String title;
    private Bitmap bitmap;

    public RestaurantItem(Bitmap bitmap, String title) {
        this.bitmap = bitmap;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
