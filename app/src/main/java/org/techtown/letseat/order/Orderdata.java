package org.techtown.letseat.order;

public class Orderdata {
    private String ordertimetext, menulist;
    private String  requesttext, pricetext;
    private int tablenumber, quantity, moremenu;


    public Orderdata(String ordertimetext, int tablenumber, String menulist, int quantity, int moremenu, String requesttext, String pricetext){
        this.ordertimetext = ordertimetext;
        this.tablenumber = tablenumber;
        this.menulist = menulist;
        this.quantity = quantity;
        this.moremenu = moremenu;
        this.requesttext = requesttext;
        this.pricetext = pricetext;
    }

    public String getOrdertimetext(){
        return ordertimetext;
    }
    public void setOrdertimetext(String ordertimetext){
        this.ordertimetext = ordertimetext;
    }
    public int getTablenumber(){
        return tablenumber;
    }
    public void setTablenumber(int tablenumber){
        this.tablenumber = tablenumber;
    }
    public String getTablenumbertext(){
        return ordertimetext;
    }
    public String getMenulist(){
        return menulist;
    }
    public void setMenulist(String menulist){
        this.menulist = menulist;
    }
    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    public int getMoremenu(){
        return moremenu;
    }
    public void setMoremenu(int moremenu){
        this.moremenu = moremenu;
    }
    public String getRequesttext(){
        return requesttext;
    }
    public void setRequesttext(String requesttext){
        this.requesttext = requesttext;
    }
    public String getPricetext(){
        return pricetext;
    }
    public void setPricetext(String pricetext){
        this.pricetext = pricetext;
    }
}
