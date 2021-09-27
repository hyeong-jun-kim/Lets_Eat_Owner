package org.techtown.letseat.waiting;

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

public class WaitingAdapter extends RecyclerView.Adapter<WaitingAdapter.ViewHolder>{

    ArrayList<Waiting> items = new ArrayList<Waiting>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.waiting_recycle, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Waiting item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Waiting item){
        items.add(item);
    }



    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView waiting_number;
        TextView name;
        TextView phonenum;
        TextView person_number;
        TextView reception_time;

        public ViewHolder(@NonNull View itemView){
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
