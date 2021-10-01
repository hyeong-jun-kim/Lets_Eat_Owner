package org.techtown.letseat.order;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
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
    private Context context;
    private Intent intent;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView priceTv,requestTv, menuTv, tablenameTv, dateTv;
        public View view1;
        public Button okBtn, cancelBtn;

        public ViewHolder(View view){
            super(view);
            priceTv = (TextView) view.findViewById(R.id.priceTv);
            requestTv = (TextView) view.findViewById(R.id.requestTv);
            menuTv = (TextView) view.findViewById(R.id.menuTv);
            dateTv = (TextView) view.findViewById(R.id.dateTv);
            tablenameTv = (TextView) view.findViewById(R.id.tablenameTv);

            view.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION)
                    {
                        mListener.onItemClick(v, pos);
                    }
                }
            });
        }
    }

    public OrderAdapter(ArrayList<Orderdata> Data){
        this.Dataset = Data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_recycle, parent, false);
        OrderAdapter.ViewHolder viewHolder = new OrderAdapter.ViewHolder(view);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position){

        Orderdata item = Dataset.get(position);

        holder.priceTv.setText(item.getPriceTv());
        holder.requestTv.setText(item.getRequestTv());
        holder.menuTv.setText(item.getMenuTv());
        holder.tablenameTv.setText(item.getTablenameTv());
        holder.dateTv.setText(item.getDateTv());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                intent = new Intent(view.getContext(), ItemActivity.class);
                intent.putExtra("number", position);
                view.getContext().startActivity(intent);
            }
        });
    }
    public interface OnItemClickListener
    {
        void onItemClick(View v, int pos);
    }

    private OrderAdapter.OnItemClickListener mListener = null;

    public void setOnItemClickListener(OrderAdapter.OnItemClickListener listener)
    {
        this.mListener = listener;
    }
    @Override
    public int getItemCount(){
        return Dataset.size();
    }

    public void setItems(ArrayList<Orderdata> items) {
        this.Dataset = items;
    }
}
