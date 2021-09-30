package org.techtown.letseat.restaurant;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import org.techtown.letseat.R;

public class RestaurantFragment extends Fragment {

    private RestaurantRecycleAdapter adapter = new RestaurantRecycleAdapter();
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.restaurant_fragment,container,false);

        RecyclerView recyclerView = view.findViewById(R.id.store_recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

        adapter.setItems(new RestaurantData().getItems());

        adapter.setOnItemClickListener(new RestaurantRecycleAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View v, int pos) {

            }
        });

        ExtendedFloatingActionButton store_register_button = view.findViewById(R.id.store_register_button);
        store_register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RestaurantRegister.class);
                startActivity(intent);
            }
        });

        /*Button menu_management_button = view.findViewById(R.id.menu_management_button);
        menu_management_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Menu_Management.class);
                startActivity(intent);
            }
        }); */
        return view;
    }
}