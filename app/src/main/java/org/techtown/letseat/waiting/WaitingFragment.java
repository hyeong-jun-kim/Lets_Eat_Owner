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
import org.techtown.letseat.RestItemReviewAdapter;
import org.techtown.letseat.RestItemReviewData;

import java.util.ArrayList;


public class WaitingFragment extends Fragment {

    View view;
    private WaitingAdapter adapter = new WaitingAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.waiting_fragment, container, false);

        adapter.setItems(new WaitingSample().getItems());

        RecyclerView recyclerView = view.findViewById(R.id.waiting_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

        return view;

    }
}