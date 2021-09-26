package org.techtown.letseat;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.techtown.letseat.menu.Menu_Management;

public class storeFrag extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store,container,false);

        Button store_register_button = view.findViewById(R.id.store_register_button);
        store_register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Store_Register.class);
                startActivity(intent);
            }
        });

        Button menu_management_button = view.findViewById(R.id.menu_management_button);
        menu_management_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Menu_Management.class);
                startActivity(intent);
            }
        });
        return view;
    }
}