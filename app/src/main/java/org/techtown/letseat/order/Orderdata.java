package org.techtown.letseat.order;

public class Orderdata {
    private String dateTv, menuTv, tablenameTv, requestTv, priceTv;

    public Orderdata(String dateTv, String menuTv,String tablenameTv, String requestTv, String priceTv){
        this.dateTv = dateTv;
        this.menuTv = menuTv;
        this.tablenameTv = tablenameTv;
        this.requestTv = requestTv;
        this.priceTv = priceTv;
    }

    public String getDateTv() {
        return dateTv;
    }

    public String getMenuTv() {
        return menuTv;
    }

    public String getTablenameTv() {
        return tablenameTv;
    }

    public String getRequestTv() {
        return requestTv;
    }

    public String getPriceTv() {
        return priceTv;
    }
    public void setDateTv(String dateTv) {
        this.dateTv = dateTv;
    }

    public void setMenuTv(String menuTv) {
        this.menuTv = menuTv;
    }

    public void setTablenameTv(String tablenameTv) {
        this.tablenameTv = tablenameTv;
    }

    public void setRequestTv(String requestTv) {
        this.requestTv = requestTv;
    }

    public void setPriceTv(String priceTv) {
        this.priceTv = priceTv;
    }
}
