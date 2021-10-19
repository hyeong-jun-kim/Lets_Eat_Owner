package org.techtown.letseat.restaurant;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;
import org.techtown.letseat.MainActivity;
import org.techtown.letseat.R;
import org.techtown.letseat.util.AppHelper;
import org.techtown.letseat.util.PhotoSave;

import static android.app.Activity.RESULT_OK;

public class RestaurantItemInfoFragment extends Fragment {
    public static int resId;
    private final int GET_GALLERY_IMAGE = 200;
    private final String[] items = {"한식", "중식", "일식", "양식"};
    private String name, phoneNumber, openTime, resIntro, businessNumber, resType, location, image;
    private String email, ownerId;
    private int aloneAble;
    private TextView textView;
    private EditText nameEdit, phoneNumEdit, openTimeEdit, introEdit, businessEdit, locationEdit;
    private RadioButton singleMealYes, singleMealNo;
    private Button info_edit_btn;
    private ImageView restaurant_image = null;
    private Bitmap bitmap;
    private Bundle bundle;
    private Spinner spinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resId = RestaurantItemMain.getResId();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle saveBundle) {
        // OwnerId 가져오기
        SharedPreferences preferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        String email_string = preferences.getString("email", "");
        getOwnerId(email_string);
        // 초기 설정
        View view = inflater.inflate(R.layout.restaurant_item_info_fragment, container, false);
        singleMealYes = view.findViewById(R.id.info_singlemeal_yes);
        singleMealNo = view.findViewById(R.id.info_singlemeal_no);
        textView = view.findViewById(R.id.info_textView18);
        nameEdit = view.findViewById(R.id.info_resName);
        phoneNumEdit = view.findViewById(R.id.info_phoneNumber);
        openTimeEdit = view.findViewById(R.id.info_openTime);
        introEdit = view.findViewById(R.id.info_resIntro);
        businessEdit = view.findViewById(R.id.Info_businessNumber);
        locationEdit = view.findViewById(R.id.info_location);
        restaurant_image = view.findViewById(R.id.restaurant_image);
        info_edit_btn = view.findViewById(R.id.info_btn);
        info_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (singleMealYes.isChecked()) {
                    aloneAble = 1;
                } else if (singleMealNo.isChecked()) {
                    aloneAble = 0;
                }
                updateRestaurant(); // 업데이트 요청
            }
        });
        // 불러온 값 집어넣기
        spinner = view.findViewById(R.id.info_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(items[position]);
                switch (position) {
                    case 0:
                        resType = "koreanFood";
                        break;
                    case 1:
                        resType = "chineseFood";
                        break;
                    case 2:
                        resType = "japaneseFood";
                        break;
                    case 3:
                        resType = "westernFood";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                textView.setText(resType);
            }
        });
        restaurant_image = view.findViewById(R.id.info_image);
        restaurant_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.
                        setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });
        // 레스토랑 데이터 값 가져오기
        getResData(resId);
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK
                && data != null && data.getData() != null) {

            Uri selectedImageUri = data.getData();
            restaurant_image.setImageURI(selectedImageUri);
            BitmapDrawable drawable = (BitmapDrawable) restaurant_image.getDrawable();
            bitmap = drawable.getBitmap();
            bitmap = PhotoSave.resize(bitmap, getResources());
            image = PhotoSave.BitmapToString(bitmap);
        }
    }
    // 특정 식당 데이터 가져오기
    void getResData(int id) {
        int resId = id;
        String url = "http://125.132.62.150:8000/letseat/store/findOne?resId=" + resId;
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            name = response.getString("resName");
                            phoneNumber = response.getString("phoneNumber");
                            openTime = response.getString("openTime");
                            resIntro = response.getString("resIntro");
                            businessNumber = response.getString("businessNumber");
                            resType = response.getString("restype");
                            location = response.getString("location");
                            aloneAble = response.getInt("aloneAble");
                            image = response.getString("image");
                            String typeName = getResType(resType);
                            //불러온 값 집어넣기
                            nameEdit.setText(name);
                            phoneNumEdit.setText(phoneNumber);
                            openTimeEdit.setText(openTime);
                            introEdit.setText(resIntro);
                            businessEdit.setText(businessNumber);
                            locationEdit.setText(location);
                            if (aloneAble == 1) {
                                singleMealYes.setChecked(true);
                            } else {
                                singleMealNo.setChecked(true);
                            }
                            textView.setText(typeName);
                            bitmap = PhotoSave.StringToBitmap(image);
                            restaurant_image.setImageBitmap(bitmap);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error", "에러뜸");
                    }
                }
        );
        queue.add(jsonObjectRequest);
    }
    // 식당 UPDATE(PUT) 요청
    void updateRestaurant() {
        String url = "http://125.132.62.150:8000/letseat/store/update/" + resId;
        JSONObject postData = new JSONObject();
        JSONObject ownerData = new JSONObject();
        try {
            //값 불러오기
            name = nameEdit.getText().toString();
            phoneNumber = phoneNumEdit.getText().toString();
            openTime = openTimeEdit.getText().toString();
            resIntro = introEdit.getText().toString();
            businessNumber = businessEdit.getText().toString();
            location = locationEdit.getText().toString();
            ownerData.put("ownerId", ownerId);
            //jsonArray.put(ownerData);
            postData.put("resName", name);
            postData.put("phoneNumber", phoneNumber);
            postData.put("openTime", openTime);
            postData.put("resIntro", resIntro);
            postData.put("businessNumber", businessNumber);
            postData.put("restype", resType);
            postData.put("location", location);
            postData.put("aloneAble", aloneAble);
            postData.put("image", image);
            postData.put("owner", ownerData);
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.PUT,
                url,
                postData,
                new Response.Listener<JSONObject>() {
                    @Override // 응답 잘 받았을 때
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getActivity().getApplicationContext(), "성공적으로 수정이 되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override // 에러 발생 시
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error", error.toString());
                        Toast.makeText(getActivity(), "연결상태 불량", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        request.setShouldCache(false); // 이전 결과 있어도 새로 요청해 응답을 보내줌
        AppHelper.requestQueue = Volley.newRequestQueue(getActivity()); // requsetQueue 초기화
        AppHelper.requestQueue.add(request);
    }

    /**
     * Get요청으로 OwnerId 가져오기
     */
    public void getOwnerId(String email_string) {
        String url = "http://125.132.62.150:8000/letseat/register/owner/get/id?email=" + email_string;
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override // 응답 잘 받았을 때
                    public void onResponse(String response) {
                        ownerId = response;
                    }
                },
                new Response.ErrorListener() {
                    @Override // 에러 발생 시
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error", error.toString());
                    }

                }
        );
        request.setShouldCache(false); // 이전 결과 있어도 새로 요청해 응답을 보내줌
        AppHelper.requestQueue = Volley.newRequestQueue(getActivity()); // requsetQueue 초기화
        AppHelper.requestQueue.add(request);
    }
    public String getResType(String resType) {
        String typeName = null;
        int pos = 0;
        switch (resType) {
            case "koreanFood":
                typeName = "한식";
                pos = 0;
                break;
            case "chineseFood":
                typeName = "중식";
                pos = 1;
                break;
            case "japaneseFood":
                typeName = "일식";
                pos = 2;
                break;
            case "westernFood":
                typeName = "양식";
                pos = 3;
                break;
        }
        spinner.setSelection(pos);
        return typeName;
    }
}