package org.techtown.letseat.restaurant;

public class RestaurantItem {

    private String title;
    private int src;
    public RestaurantItem(int src, String title) {

        this.src = src;
        this.title = title;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }
}
