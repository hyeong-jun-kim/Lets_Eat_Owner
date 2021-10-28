package org.techtown.letseat.waiting;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.techtown.letseat.R;

import java.util.ArrayList;

public class WaitingAdapter extends RecyclerView.Adapter<WaitingAdapter.ViewHolder> {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private ArrayList<Waiting> items = new ArrayList<>();
    private Context context;

    @NonNull
    @Override
    public WaitingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.waiting_recycle, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        context = parent.getContext();

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WaitingAdapter.ViewHolder viewHolder, int position) {

        Waiting item = items.get(position);

        viewHolder.userImageIv.setImageResource(item.getSrc());
        viewHolder.dateTv.setText(item.getDate());
        viewHolder.idTv.setText(item.getId());
        viewHolder.peopleTv.setText(item.getPeople());
        viewHolder.waitingnumTv.setText(item.getWaitingnum());
        viewHolder.phoneNumberTv.setText(item.getPhonenumber());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(ArrayList<Waiting> items) {
        this.items = items;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView dateTv, waitingnumTv, idTv, peopleTv,phoneNumberTv;
        ImageView userImageIv;
        MaterialButton orderCheckBtn;

        ViewHolder(View itemView) {
            super(itemView);

            dateTv = itemView.findViewById(R.id.dateTv);
            waitingnumTv = itemView.findViewById(R.id.waitingnumTv);
            idTv = itemView.findViewById(R.id.idTv);
            peopleTv = itemView.findViewById(R.id.peopleTv);
            phoneNumberTv = itemView.findViewById(R.id.phoneNumberTv);
            userImageIv = itemView.findViewById(R.id.userImageIv);
            orderCheckBtn = itemView.findViewById(R.id.orderCheckBtn);
            DatabaseReference myRef = database.getReference("userId_1");
            orderCheckBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myRef.setValue(1);
                }
            });
        }
    }
}

