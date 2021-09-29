package org.techtown.letseat.waiting;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.techtown.letseat.R;

import java.util.ArrayList;


public class WaitingFragment extends Fragment {

    private ArrayList<Waiting> items = new ArrayList<>();

    public WaitingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.waiting_fragment,container,false);

        initDataset();

        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.waiting_recyclerview);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        WaitingAdapter adapter = new WaitingAdapter(context,items);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void initDataset(){
        items.clear();
        items.add(new Waiting("1","안태현","010-1212-1111",
                "4명","17시30분"));
        items.add(new Waiting("2","김형준","010-2323-5565",
                "3명","18시00분"));
    }
}