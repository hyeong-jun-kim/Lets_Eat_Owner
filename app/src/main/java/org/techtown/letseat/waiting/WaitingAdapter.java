package org.techtown.letseat.waiting;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.techtown.letseat.R;
import org.techtown.letseat.util.AppHelper;

import java.util.ArrayList;

public class WaitingAdapter extends RecyclerView.Adapter<WaitingAdapter.ViewHolder> {
    private ArrayList<WaitingData> items = new ArrayList<>();
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
        WaitingData item = items.get(position);
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

    public void setItems(ArrayList<WaitingData> items) {
        this.items = items;
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateTv, waitingnumTv, idTv, peopleTv,phoneNumberTv;
        ImageView userImageIv;
        Button orderCheckBtn;
        ViewHolder(View itemView) {
            super(itemView);
            dateTv = itemView.findViewById(R.id.dateTv);
            waitingnumTv = itemView.findViewById(R.id.waitingnumTv);
            idTv = itemView.findViewById(R.id.idTv);
            peopleTv = itemView.findViewById(R.id.peopleTv);
            phoneNumberTv = itemView.findViewById(R.id.phoneNumberTv);
            userImageIv = itemView.findViewById(R.id.userImageIv);
            orderCheckBtn = itemView.findViewById(R.id.orderCheckBtn);
            orderCheckBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != 0) {
                        Toast.makeText(context, "앞의 웨이팅을 먼저 처리해야합니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    processWaiting(items.get(pos).resId);
                    WaitingFragment.adapter.notifyItemRemoved(pos);
                    items.remove(pos);
                    ArrayList<WaitingData> tmpItems = new ArrayList<>();
                    for (int i = 0; i < items.size(); i++) {
                        WaitingData data = items.get(i);
                        int waitingNum = Integer.parseInt(data.getWaitingnum());
                        data.setWaitingnum(String.valueOf(waitingNum - 1));
                        tmpItems.add(data);
                    }
                    items.clear();
                    items = tmpItems;
                    WaitingFragment.adapter.notifyDataSetChanged();
                }
            });
        }

        public void processWaiting(int resId) {
            String url = "http://125.132.62.150:8000/letseat/waiting/res/process?resId=" + resId;
            StringRequest request = new StringRequest(
                    Request.Method.GET,
                    url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(context, "웨이팅 처리가 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("Error", error.toString());
                        }
                    }
            );
            request.setShouldCache(false); // 이전 결과 있어도 새로 요청해 응답을 보냄
            AppHelper.requestQueue.add(request);
        }
    }

}

