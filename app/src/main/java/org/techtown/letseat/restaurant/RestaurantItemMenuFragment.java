package org.techtown.letseat.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import org.techtown.letseat.R;
import org.techtown.letseat.menu.Menu_add;

public class RestaurantItemMenuFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ExtendedFloatingActionButton menu_register_button;

    public RestaurantItemMenuFragment() {
        // Required empty public constructor
    }

    public static RestaurantItemMenuFragment newInstance(String param1, String param2) {
        RestaurantItemMenuFragment fragment = new RestaurantItemMenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.restaurant_item_menu_fragment, container, false);

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
