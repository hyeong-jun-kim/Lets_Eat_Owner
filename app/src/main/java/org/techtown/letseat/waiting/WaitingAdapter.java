package org.techtown.letseat.waiting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.letseat.R;
import org.techtown.letseat.menu.Menu;
import org.techtown.letseat.menu.MenuAdapter;

import java.util.ArrayList;

public class WaitingAdapter extends RecyclerView.Adapter<WaitingAdapter.MyViewHolder>{

    private ArrayList<Waiting> items;
    private LayoutInflater mInflate;
    private Context mContext;

    public WaitingAdapter(Context context, ArrayList<Waiting> items) {
        this.mContext = context;
        this.mInflate = LayoutInflater.from(context);
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View itemView = mInflate.inflate(R.layout.waiting_recycle, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.waiting_number.setText(items.get(position).waiting_number);
        holder.name.setText(items.get(position).name);
        holder.phonenum.setText(items.get(position).phonenum);
        holder.person_number.setText(items.get(position).person_number);
        holder.reception_time.setText(items.get(position).reception_time);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Waiting item){
        items.add(item);
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView waiting_number;
        public TextView name;
        public TextView phonenum;
        public TextView person_number;
        public TextView reception_time;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            waiting_number = itemView.findViewById(R.id.waiting_number);
            name = itemView.findViewById(R.id.name);
            phonenum = itemView.findViewById(R.id.phonenum);
            person_number = itemView.findViewById(R.id.person_number);
            reception_time = itemView.findViewById(R.id.reception_time);
        }
        public void setItem(Waiting item){
            waiting_number.setText(item.getWaiting_number());
            name.setText(item.getName());
            phonenum.setText(item.getPhonenum());
            person_number.setText(item.getPerson_number());
            reception_time.setText(item.getReception_time());
        }
    }
}
