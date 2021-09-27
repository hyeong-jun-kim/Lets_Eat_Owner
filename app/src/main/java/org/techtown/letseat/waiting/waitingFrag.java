package org.techtown.letseat.waiting;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.techtown.letseat.R;
import org.techtown.letseat.menu.Menu;
import org.techtown.letseat.waiting.Waiting;
import org.techtown.letseat.waiting.WaitingAdapter;


public class waitingFrag extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_waiting,container,false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.waiting_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        WaitingAdapter adapter = new WaitingAdapter();

        adapter.addItem(new Waiting("1", "안태현","010-2222-3333",
                "4명","18시30분"));
        adapter.addItem(new Waiting("2", "김형준","010-3333-4444",
                "3명","18시45분"));

        recyclerView.setAdapter(adapter);

        return rootView;
    }
}