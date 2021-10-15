package org.techtown.letseat.menu;

import android.graphics.Bitmap;

public class Menu {
    private String name;
    private String price;
    private Bitmap bitmap;

    public Menu(String name, String price, Bitmap bitmap) {
        this.name = name;
        this.price = price;
        this.bitmap = bitmap;
    }

    public String getName() {
        return name;
    }

    public void setName() {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
