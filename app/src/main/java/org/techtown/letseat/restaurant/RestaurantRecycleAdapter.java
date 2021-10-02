package org.techtown.letseat.restaurant;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.letseat.R;

import java.util.ArrayList;

public class RestaurantRecycleAdapter extends RecyclerView.Adapter<RestaurantRecycleAdapter.ViewHolder>{
    private Intent intent;
    private ArrayList<RestaurantItem> items = new ArrayList<>();
    private Context context;

    @NonNull
    @Override
    public RestaurantRecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_recycle, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantRecycleAdapter.ViewHolder viewHolder, int position) {
        RestaurantItem item = items.get(position);
        viewHolder.storeIv.setImageBitmap(item.getBitmap());
        viewHolder.titleTv.setText(item.getTitle());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent =  new Intent(view.getContext(), RestaurantItemMain.class);
                intent.putExtra("number", position);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(ArrayList<RestaurantItem> items) {
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
