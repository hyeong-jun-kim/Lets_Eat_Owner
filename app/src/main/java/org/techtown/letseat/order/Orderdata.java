package org.techtown.letseat.order;

import java.util.ArrayList;

public class Orderdata {

    ArrayList Menu_amount_List;
    Object  OrderTimeList, TableNumberList, ExcriptionList, PriceList;

    public Orderdata(Object OrderTimeList, ArrayList Menu_amount_List , Object TableNumberList,
                     Object ExcriptionList, Object PriceList){
        this.OrderTimeList = OrderTimeList;
        this.Menu_amount_List = Menu_amount_List;
        this.TableNumberList = TableNumberList;
        this.ExcriptionList = ExcriptionList;
        this.PriceList = PriceList;
    }


    public Object getOrderTimeList()  {return OrderTimeList;}

    public ArrayList<String> getMenu_amount_List()  {return Menu_amount_List;}


    public Object getTableNumberList()  {return TableNumberList;}

    public Object getExcriptionList()  {return ExcriptionList;}

    public Object getPriceList()  {return PriceList;}

}
