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
        TextView name;
        TextView phonenum;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phonenum = itemView.findViewById(R.id.phonenum);
        }
        public void setItem(Waiting item){
            name.setText(item.getName());
            phonenum.setText(item.getPhonenum());
        }
    }
}
