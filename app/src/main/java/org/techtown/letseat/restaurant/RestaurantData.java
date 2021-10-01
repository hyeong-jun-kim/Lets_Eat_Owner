package org.techtown.letseat.restaurant;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.techtown.letseat.util.AppHelper;

import java.util.ArrayList;

public class RestaurantData {

    ArrayList<RestaurantItem> items = new ArrayList<>();
    ArrayList list = new ArrayList<String>();
    String image;
    Bitmap bitmap;

    public ArrayList<RestaurantItem> getItems() {

        /*RestaurantItem order1 = new RestaurantItem(R.drawable.image1,"하오탕");

        RestaurantItem order2 = new RestaurantItem(R.drawable.image2, "뼈대있는 가문");

        RestaurantItem order3 = new RestaurantItem(R.drawable.image3, "곱순네 순대국");

        items.add(order1);
        items.add(order2);
        items.add(order3);*/
        getResData();
        return items;
    }

    void getResData() {
        String url = "http://125.132.62.150:8000/letseat/store/findAll";


        JSONArray getData = new JSONArray();

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                getData,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            String restype, resName, location;
                            for (int i = 0; i < 5; i++) {
                                JSONObject jsonObject = (JSONObject) response.get(i);
                                resName = jsonObject.getString("resName");
                                //image = jsonObject.getString("image");
                                //bitmap = PhotoSave.StringToBitmap(image);
                                //bitmap = getBitmap(image);
                                bitmap = null;
                                RestaurantItem item = new RestaurantItem(bitmap, resName);
                                items.add(item);
                            }
                            Log.d("응답", response.toString());
                        } catch (JSONException e) {
                            Log.d("예외", e.toString());
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("에러", error.toString());
                    }
                }
        );
        request.setShouldCache(false); // 이전 결과 있어도 새로 요청해 응답을 보내줌
        //AppHelper.requestQueue = Volley.newRequestQueue(this); // requsetQueue 초기화
        AppHelper.requestQueue.add(request);
    }

    private Bitmap getBitmap(String image) {
        byte[] bytes = Base64.decode(image, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return bitmap;
    }
}