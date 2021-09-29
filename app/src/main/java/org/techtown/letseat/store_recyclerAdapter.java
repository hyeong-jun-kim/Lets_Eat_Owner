package org.techtown.letseat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class store_recyclerAdapter extends RecyclerView.Adapter<store_recyclerAdapter.ViewHolder>{

    private ArrayList<storeItem> items = new ArrayList<>();
    private Context context;

    @NonNull
    @Override
    public store_recyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_recycle, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        context = parent.getContext();

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull store_recyclerAdapter.ViewHolder viewHolder, int position) {

        storeItem item = items.get(position);

        viewHolder.storeIv.setImageResource(item.getSrc());
        viewHolder.titleTv.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(ArrayList<storeItem> items) {
        this.items = items;
    }

    public interface OnItemClickListener
    {
        void onItemClick(View v, int pos);
    }

    private OnItemClickListener mListener = null;

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.mListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView storeIv;
        TextView titleTv;

        ViewHolder(View itemView) {
            super(itemView);

            storeIv = itemView.findViewById(R.id.storeIv);
            titleTv = itemView.findViewById(R.id.titleTv);

            itemView.setOnClickListener(new View.OnClickListener()
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
}
