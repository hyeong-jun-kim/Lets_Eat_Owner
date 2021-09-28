package org.techtown.letseat;

public class storeItem {

    private String title;
    private int src;
    public storeItem(int src, String title) {

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
