package org.techtown.letseat.order;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.techtown.letseat.R;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{
    private ArrayList<Orderdata> Dataset;
    private Intent intent;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView ordertime, ordertimetext ,tablenumber, tablenumbertext, menu, menulist, quantity, quantitytext, quantitymore, moremenu, moremenutext, request, requesttext, price, pricetext;
        public View view1;
        public Button check_order, check_serving;

        public ViewHolder(View view){
            super(view);
            ordertime = (TextView) view.findViewById(R.id.ordertime);
            ordertimetext = (TextView) view.findViewById(R.id.ordertimetext);
            tablenumber = (TextView) view.findViewById(R.id.tablenumber);
            tablenumbertext = (TextView) view.findViewById(R.id.tablenumbertext);
            menu = (TextView) view.findViewById(R.id.menu);
            menulist = (TextView) view.findViewById(R.id.menulist);
            quantity = (TextView) view.findViewById(R.id.quantity);
            quantitytext = (TextView) view.findViewById(R.id.quantitytext);
            quantitymore = (TextView) view.findViewById(R.id.quantitymore);
            moremenu = (TextView) view.findViewById(R.id.moremenu);
            moremenutext = (TextView) view.findViewById(R.id.moremenutext);
            request = (TextView) view.findViewById(R.id.request);
            requesttext = (TextView) view.findViewById(R.id.requesttext);
            price = (TextView) view.findViewById(R.id.price);
            pricetext = (TextView) view.findViewById(R.id.pricetext);
            view1 = (View) view.findViewById(R.id.view1);
            check_order = view.findViewById(R.id.check_order);;
            check_serving = view.findViewById(R.id.check_serving);
        }
    }

    public OrderAdapter(ArrayList<Orderdata> Data){
        this.Dataset = Data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_recycle, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position){
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                intent = new Intent(view.getContext(), ItemActivity.class);
                intent.putExtra("number", position);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount(){
        return Dataset.size();
    }
}
