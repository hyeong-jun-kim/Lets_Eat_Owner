package org.techtown.letseat.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.techtown.letseat.R;

public class Menu_management extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_management);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        MenuAdapter adapter = new MenuAdapter();

        // 여기는 리사이클러 뷰 테스트용임
        adapter.addItem(new Menu("마라탕", "3000원", R.drawable.menuimg1));
        adapter.addItem(new Menu("해물탕", "5000원", R.drawable.menuimg2));

        recyclerView.setAdapter(adapter);

        Button add_menu_button = findViewById(R.id.add_menu_button);
        add_menu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Menu_add.class);
                startActivity(intent);
            }
        });
    }
}