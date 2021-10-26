package org.techtown.letseat.order;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import java.util.HashMap;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    String OrderCheck;
    private ArrayList<OrderData> items;
    private Context context;
    private Intent intent;

    public void setServingOrder(int orderId) {
        String url = "http://125.132.62.150:8000/letseat/order/serving?orderId=" + orderId;
        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("성공", "성공");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "연결상태 불량", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        request.setShouldCache(false); // 이전 결과 있어도 새로 요청해 응답을 보냄
        AppHelper.requestQueue.add(request);
    }

    public OrderAdapter(ArrayList<OrderData> Data) {
        this.items = Data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_recycle, parent, false);
        OrderAdapter.ViewHolder viewHolder = new OrderAdapter.ViewHolder(view);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        OrderData item = items.get(position);
        OrderCheck = item.getOrderCheck();
        if (OrderCheck.equals("Y")) {
            holder.okBtn.setBackgroundColor(Color.parseColor("#000000"));
            holder.okBtn.setText("접수 완료");
        }
        holder.priceTv.setText(item.getPrice());
        holder.requestTv.setText(item.getRequest());
        holder.menuTv.setText(item.getMenu());
        holder.tablenameTv.setText(item.getTableNumber());
        holder.dateTv.setText(item.getDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

    public void setOnItemClickListener(OrderAdapter.OnItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position){
        return position;
    }

    public void setItems(ArrayList<OrderData> items) {
        this.items = items;
    }

    public void setCheckOrder(int orderId) {
        String url = "http://125.132.62.150:8000/letseat/order/check?orderId=" + orderId;
        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("성공", "성공");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "연결상태 불량", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        request.setShouldCache(false); // 이전 결과 있어도 새로 요청해 응답을 보냄
        AppHelper.requestQueue.add(request);
    }
    /*public void getOrderList(int resId, int tableNum) {
        String url = "http://125.132.62.150:8000/letseat/order/list/find/orderList?resId=" + resId + "&tableNum=" + tableNum;
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            orderId = Integer.parseInt(response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "연결상태 불량", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        request.setShouldCache(false); // 이전 결과 있어도 새로 요청해 응답을 보냄
        AppHelper.requestQueue.add(request);
    }*/

    public class ViewHolder extends RecyclerView.ViewHolder {

        public HashMap<Integer, Integer> orderMap = new HashMap<>();
        public TextView priceTv, requestTv, menuTv, tablenameTv, dateTv;
        public View view1;
        public Button okBtn, cancelBtn, completeBtn;


        public ViewHolder(View view) {
            super(view);

            okBtn = view.findViewById(R.id.orderCheckBtn);
            cancelBtn = view.findViewById(R.id.orderCancelBtn);
            completeBtn = view.findViewById(R.id.completeBtn);
            priceTv = view.findViewById(R.id.priceTv);
            requestTv = view.findViewById(R.id.requestTv);
            menuTv = view.findViewById(R.id.menuTv);
            dateTv = view.findViewById(R.id.dateTv);
            tablenameTv = view.findViewById(R.id.tablenameTv);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        mListener.onItemClick(v, pos);
                    }
                }
            });
            okBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (!orderMap.containsKey(pos)) {
                        String tableNumber = items.get(pos).tableNumber;
                        //getOrderList(OrderFrag.resIdList.get(pos), Integer.parseInt(tableNumber));
                        orderMap.put(pos, 1);
                        setCheckOrder(items.get(pos).orderId);
                        okBtn.setBackgroundColor(Color.parseColor("#000000"));
                        okBtn.setText("접수 완료");
                        //dateTv = v.findViewById(R.id.dateTv);
                        String text = dateTv.getText().toString();
                        dateTv.setText(text);
                        Toast.makeText(context, "주문 접수가 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "이미 접수가 완료된 주문입니다.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            completeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (orderMap.containsKey(pos)) {
                        String tableNumber = items.get(pos).tableNumber;
                        setServingOrder(items.get(pos).orderId);
                        OrderFrag.adapter.notifyItemRemoved(pos);
                        orderMap.remove(pos);
                        items.remove(pos);
                        Toast.makeText(context, "서빙이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "서빙버튼을 누르기전 주문확인 버튼을 눌러주시기 바랍니다.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


}
