package org.techtown.letseat.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import org.techtown.letseat.R;
import org.techtown.letseat.menu.Menu;
import org.techtown.letseat.menu.MenuAdapter;
import org.techtown.letseat.menu.Menu_add;

public class RestaurantItemMenuFragment extends Fragment {

    ExtendedFloatingActionButton menu_register_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.restaurant_item_menu_fragment, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.rest_item_menu_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        MenuAdapter adapter = new MenuAdapter();

        adapter.addItem(new Menu("마라탕", "3000원", R.drawable.menuimg1));
        adapter.addItem(new Menu("해물탕", "5000원", R.drawable.menuimg2));

        recyclerView.setAdapter(adapter);
        menu_register_button = view.findViewById(R.id.menu_register_button);
        menu_register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Menu_add.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
