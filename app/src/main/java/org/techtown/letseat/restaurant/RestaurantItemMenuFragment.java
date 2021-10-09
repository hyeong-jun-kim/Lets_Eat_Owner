package org.techtown.letseat.restaurant;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.techtown.letseat.R;
import org.techtown.letseat.menu.Menu;
import org.techtown.letseat.menu.MenuAdapter;
import org.techtown.letseat.menu.Menu_add;
import org.techtown.letseat.util.PhotoSave;

import java.util.ArrayList;
import java.util.List;

public class RestaurantItemMenuFragment extends Fragment {
    private final List<JSONObject> menus = new ArrayList<>();
    private int resId;
    private String email, ownerId;
    private JSONObject restaurant;
    private MenuAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resId = RestaurantItemMain.getResId();
        getMenuList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.restaurant_item_menu_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rest_item_menu_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MenuAdapter();
        //adapter.addItem(new Menu("마라탕", "3000원", R.drawable.menuimg1));
        //adapter.addItem(new Menu("해물탕", "5000원", R.drawable.menuimg2));

        recyclerView.setAdapter(adapter);

        ExtendedFloatingActionButton menu_register_button = view.findViewById(R.id.menu_register_button);
        menu_register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Menu_add.class);
                startActivity(intent);
            }
        });

        return view;

    }

    // 메뉴 리스트 가져오기
    void getMenuList() {
        String url = "http://125.132.62.150:8000/letseat/store/menu/load?resId=" + resId;
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray menuList) {
                        String name = null;
                        String image_string;
                        Bitmap bitmap = null;
                        int price = 0;
                        for (int i = 0; i < menuList.length(); i++) {
                            try {
                                JSONObject menu = menuList.getJSONObject(i);
                                name = menu.getString("name");
                                image_string = menu.getString("photo");
                                bitmap = PhotoSave.StringToBitmap(image_string);
                                price = menu.getInt("price");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            adapter.addItem(new Menu(name, price + "원", bitmap));
                        }
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error", "에러뜸");
                    }
                }
        );
        queue.add(jsonArrayRequest);
    }
}
