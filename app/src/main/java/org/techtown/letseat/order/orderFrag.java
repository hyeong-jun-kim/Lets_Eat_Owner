package org.techtown.letseat.order;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.techtown.letseat.R;
import org.techtown.letseat.order.OrderAdapter;
import org.techtown.letseat.order.Orderdata;

import java.util.ArrayList;

public class orderFrag extends Fragment {

    private RecyclerView recyclerView;
    private OrderAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Orderdata> list = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        prepareData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        adapter = new OrderAdapter(list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void prepareData(){
        list.clear();
        list.add(new Orderdata("2021년 9월 27일 10시 00분","마라탕","1번 테이블", "보통맛으로 해주세요","20,000"));
        list.add(new Orderdata("2021년 9월 27일 11시 00분","존나 마라탕","2번 테이블", "존나 매운맛으로 해주세요","30,000"));
    }

}