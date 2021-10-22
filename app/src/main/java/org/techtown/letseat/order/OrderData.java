package org.techtown.letseat.order;

public class OrderData {

    String date, tableNumber, menu, request, price;

    public OrderData(String date, String tableNumber, String menu, String request,
                     String price) {
        this.date = date;
        this.tableNumber = tableNumber;
        this.menu = menu;
        this.request = request;
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public String getMenu() {
        return menu;
    }

    public String getRequest() {
        return request;
    }

    public String getPrice() {
        return price;
    }
}
