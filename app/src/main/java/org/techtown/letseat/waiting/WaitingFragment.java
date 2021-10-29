package org.techtown.letseat.waiting;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.techtown.letseat.MainActivity;
import org.techtown.letseat.R;
import org.techtown.letseat.util.AppHelper;

import java.util.ArrayList;


public class WaitingFragment extends Fragment {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    View view;
    int num;
    public static WaitingAdapter adapter = new WaitingAdapter();
    public static ArrayList<Integer> resIdList = new ArrayList<>();
    private final String ownerId = MainActivity.ownerId;
    public ArrayList<WaitingData> items = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.waiting_fragment, container, false);
        //adapter.setItems(new WaitingSample().getItems());
        RecyclerView recyclerView = view.findViewById(R.id.waiting_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

        DatabaseReference myRef = database.getReference("waiting_ownerId_1");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // 데이터 값이 변했을 때마다 작동, text 안에 받아온 데이터 문자열을 넣어줌
                try{
                    num = dataSnapshot.getValue(Integer.class);
                    Log.d("ds","ds");
                    if(num != 0){
                        //푸쉬알림
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        PendingIntent contentIntent = PendingIntent.getActivity(getActivity(),0,
                                new Intent(getActivity(),MainActivity.class),0);
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), "default");
                        builder.setSmallIcon(R.mipmap.ic_launcher);
                        builder.setContentTitle("대기자");
                        builder.setContentText("새로운 대기자가 생겼습니다.");
                        builder.setContentIntent(contentIntent);
                        builder.setAutoCancel(true);
                        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            notificationManager.createNotificationChannel(new NotificationChannel("default", "기본 채널", NotificationManager.IMPORTANCE_DEFAULT));
                        }
                        notificationManager.notify(1, builder.build());

                    }
                    else {

                    }
                }catch(Exception e){
                    myRef.setValue(0);  //ownerId가 처음만들어졌을때
                    Log.d("ds","ds");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // 에러가 날 때 작동
            }
        });
        getResList();
        return view;
    }

    public void getResList() {
        String url = "http://125.132.62.150:8000/letseat/store/findOwner?ownerId=" + ownerId;
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                int resId = jsonObject.getInt("resId");
                                resIdList.add(resId);
                            }
                            getResWaitingList();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        request.setShouldCache(false); // 이전 결과 있어도 새로 요청해 응답을 보냄
        AppHelper.requestQueue.add(request);
    }

    public void getResWaitingList() {
        items.clear();
        for (int i = 0; i < resIdList.size(); i++) {
            int resId = resIdList.get(i);
            String url = "http://125.132.62.150:8000/letseat/waiting/res/load?resId=" + resId;
            JsonArrayRequest request = new JsonArrayRequest(
                    Request.Method.GET,
                    url,
                    null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    JSONObject userData = jsonObject.getJSONObject("user");
                                    String email = userData.getString("email");
                                    String phoneNumber = jsonObject.getString("phoneNumber");
                                    String peopleNum = jsonObject.getString("peopleNum");
                                    String date = jsonObject.getString("date");
                                    String waitingNum = "" + jsonObject.getInt("waitingNumber");
                                    WaitingData waitingData = new WaitingData(resId, date, waitingNum, email, peopleNum, phoneNumber);
                                    items.add(waitingData);
                                }
                                start();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }
            );
            request.setShouldCache(false); // 이전 결과 있어도 새로 요청해 응답을 보냄
            AppHelper.requestQueue.add(request);
        }
    }

    public void start() {
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
    }
}
