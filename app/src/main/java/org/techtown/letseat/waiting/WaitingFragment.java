package org.techtown.letseat.waiting;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.techtown.letseat.MainActivity;
import org.techtown.letseat.R;
import org.techtown.letseat.RestItemReviewAdapter;
import org.techtown.letseat.RestItemReviewData;

import java.util.ArrayList;


public class WaitingFragment extends Fragment {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    View view;
    int num;
    private WaitingAdapter adapter = new WaitingAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.waiting_fragment, container, false);

        adapter.setItems(new WaitingSample().getItems());

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
        return view;

    }
}