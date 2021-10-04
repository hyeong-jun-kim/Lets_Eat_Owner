package org.techtown.letseat.restaurant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.letseat.R;
import org.techtown.letseat.RestItemReviewAdapter;
import org.techtown.letseat.RestItemReviewData;

public class RestaurantItemReviewFragment extends Fragment {
    View view;
    private RestItemReviewAdapter adapter = new RestItemReviewAdapter();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.restaurant_item_review_fragment, container, false);

        adapter.setItems(new RestItemReviewData().getItems());

        RecyclerView recyclerView = view.findViewById(R.id.rest_item_review_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }
}
