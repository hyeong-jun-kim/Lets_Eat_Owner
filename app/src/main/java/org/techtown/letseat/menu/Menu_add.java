package org.techtown.letseat.menu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.techtown.letseat.R;
import org.techtown.letseat.restaurant.RestaurantItemMain;
import org.techtown.letseat.util.PhotoSave;

public class Menu_add extends AppCompatActivity {

    private final int GET_GALLERY_IMAGE = 200;
    private EditText menuNameEdit, menuPriceEdit, menuDescriptionEdit;
    private Button menuAddButton;
    private ImageView menuImage;
    private Bitmap bitmap;
    private String image;
    private JSONObject restaurant;
    private int resId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resId = RestaurantItemMain.getResId();
        setContentView(R.layout.menu_add);
        menuImage = findViewById(R.id.menu_image);
        menuNameEdit = findViewById(R.id.menu_name);
        menuPriceEdit = findViewById(R.id.menu_price);
        menuDescriptionEdit = findViewById(R.id.menu_description);
        menuAddButton = findViewById(R.id.menu_add_btn);
        getRestaurant();
        menuImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.
                        setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });
        menuAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postMenuAdd();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK
                && data != null && data.getData() != null) {

            Uri selectedImageUri = data.getData();
            menuImage.setImageURI(selectedImageUri);
            BitmapDrawable drawable = (BitmapDrawable) menuImage.getDrawable();
            bitmap = drawable.getBitmap();
            bitmap = PhotoSave.resizeMenu(bitmap, getResources());
            image = PhotoSave.BitmapToString(bitmap);
        }
    }

    // 메뉴 등록요청
    void postMenuAdd() {
        String url = "http://125.132.62.150:8000/letseat/store/menu/register";
        RequestQueue queue = Volley.newRequestQueue(this);
        String name = menuNameEdit.getText().toString();
        String price = menuPriceEdit.getText().toString();
        String description = menuDescriptionEdit.getText().toString();
        JSONObject postData = new JSONObject();
        JSONObject resData = new JSONObject();
        try {
            resData.put("resId", resId);
            postData.put("restaurant", resData);
            postData.put("name", name);
            postData.put("price", Integer.parseInt(price));
            postData.put("photo", image);
            postData.put("excription", description);
            postData.put("resId", restaurant);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                postData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), "성공적으로 메뉴가 등록되었습니다.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), RestaurantItemMain.class);
                        startActivity(intent);
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "연결 불량.", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        queue.add(jsonObjectRequest);
    }

    // 레스토랑 정보 불러오기
    void getRestaurant() {
        String url = "http://125.132.62.150:8000/letseat/store/findRestaurantById?resId=" + resId;
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject res) {
                        restaurant = res;
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "연결 불량.", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        queue.add(jsonObjectRequest);
    }
}