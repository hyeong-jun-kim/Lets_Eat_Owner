package org.techtown.letseat;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.techtown.letseat.menu.Menu;
import org.techtown.letseat.waiting.Waiting;
import org.techtown.letseat.waiting.WaitingAdapter;


public class waitingFrag extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_waiting,container,false);

        RecyclerView recyclerView = view.findViewById(R.id.waiting_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        WaitingAdapter adapter = new WaitingAdapter();

        adapter.addItem(new Waiting("마라탕", "3000원"));
        adapter.addItem(new Waiting("해물탕", "5000원"));

        recyclerView.setAdapter(adapter);

        return view;
    }
}