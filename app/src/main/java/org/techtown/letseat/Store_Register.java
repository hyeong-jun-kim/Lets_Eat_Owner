package org.techtown.letseat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.techtown.letseat.login.Login;

public class Store_Register extends AppCompatActivity {

    private int aloneAble;
    private String resName, resType, phoneNumber, openTime, intro, businessNumber, location;
    private TextView textView;
    private EditText resNameEdit, phoneNumberEdit, openTimeEdit, introEdit, businessNumberEdit, locationEdit;
    private RadioGroup singleMealRadio;
    private RadioButton singleMealYes, singleMealNo;
    private Button sendBtn;
    private String[] items = {"한식", "중식", "일식", "양식"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_register);
        Spinner spinner = findViewById(R.id.spinner);
        textView = findViewById(R.id.textView);
        resNameEdit = findViewById(R.id.store_register_email);
        phoneNumberEdit = findViewById(R.id.store_register_phoneNumber);
        openTimeEdit = findViewById(R.id.store_register_openTime);
        introEdit = findViewById(R.id.store_register_intro);
        businessNumberEdit = findViewById(R.id.store_register_businessNumber);
        locationEdit = findViewById(R.id.store_register_location);
        singleMealRadio = findViewById(R.id.store_register_singleMeal);
        singleMealYes = findViewById(R.id.store_register_yes);
        singleMealNo = findViewById(R.id.store_register_no);
        sendBtn = findViewById(R.id.store_register_btn);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, items);
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
                textView.setText("");
            }
        });
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resName = resNameEdit.getText().toString();
                phoneNumber = phoneNumberEdit.getText().toString();
                openTime = openTimeEdit.getText().toString();
                intro = introEdit.getText().toString();
                businessNumber = businessNumberEdit.getText().toString();
                location = locationEdit.getText().toString();
                if (singleMealYes.isChecked()) {
                    aloneAble = 1;
                } else if (singleMealNo.isChecked()) {
                    aloneAble = 0;
                }
                if (resName.isEmpty() || phoneNumber.isEmpty() || openTime.isEmpty() ||
                        intro.isEmpty() || businessNumber.isEmpty() || location.isEmpty() ||
                        resType.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "빈칸을 채워주시고 다시 시도하시길 바랍니다.", Toast.LENGTH_SHORT).show();
                    return;
                } else if (businessNumber.length() != 10) {
                    Toast.makeText(getApplicationContext(), "사업자 등록번호는 '-' 구분선 제외 10자리를 입력해주세요", Toast.LENGTH_SHORT).show();
                    businessNumberEdit.setText("");
                    return;
                }
                Toast.makeText(getApplicationContext(), "선택되었습니다", Toast.LENGTH_SHORT).show();
                registerStore();
            }
        });
    }

    // 식당 생성 POST 요청
    void registerStore() {
        String url = "http://125.132.62.150:8000/letseat/store/register";
        JSONObject postData = new JSONObject();
        JSONObject ownerData = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try {
            ownerData.put("ownerId", 1);
            //jsonArray.put(ownerData);
            postData.put("resName", resName);
            postData.put("phoneNumber", phoneNumber);
            postData.put("openTime", openTime);
            postData.put("resIntro", intro);
            postData.put("businessNumber", businessNumber);
            postData.put("restype", resType);
            postData.put("location", location);
            postData.put("aloneAble", aloneAble);
            postData.put("owner", ownerData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                postData,
                new Response.Listener<JSONObject>() {
                    @Override // 응답 잘 받았을 때
                    public void onResponse(JSONObject response) {
                        // 화면 전환
                        Toast.makeText(getApplicationContext(), "식당 등록이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override // 에러 발생 시
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error", error.toString());
                        Toast.makeText(getApplicationContext(), "연결상태 불량", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        request.setShouldCache(false); // 이전 결과 있어도 새로 요청해 응답을 보내줌
        AppHelper.requestQueue = Volley.newRequestQueue(this); // requsetQueue 초기화
        AppHelper.requestQueue.add(request);
    }
}