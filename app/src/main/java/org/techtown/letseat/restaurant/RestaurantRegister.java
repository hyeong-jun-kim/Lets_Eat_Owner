package org.techtown.letseat.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import org.techtown.letseat.util.AppHelper;
import org.techtown.letseat.MainActivity;
import org.techtown.letseat.R;

public class RestaurantRegister extends AppCompatActivity {
import java.io.ByteArrayOutputStream;

public class Store_Register extends AppCompatActivity {

    private final int GET_GALLERY_IMAGE = 200;
    private int aloneAble;
    private String resName, resType, phoneNumber, openTime, intro, businessNumber, location;
    private TextView textView;
    private EditText resNameEdit, phoneNumberEdit, openTimeEdit, introEdit, businessNumberEdit, locationEdit;
    private RadioGroup singleMealRadio;
    private RadioButton singleMealYes, singleMealNo;
    private Button sendBtn;
    private String[] items = {"한식", "중식", "일식", "양식"};
    private ImageView restaurant_image;
    private Bitmap bitmap;

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

        restaurant_image = (ImageView) findViewById(R.id.restaurant_image);
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

    //사진등록
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK
                && data != null && data.getData() != null) {

            Uri selectedImageUri = data.getData();
            restaurant_image.setImageURI(selectedImageUri);
            BitmapDrawable drawable = (BitmapDrawable)restaurant_image.getDrawable();
            bitmap = drawable.getBitmap();
            bitmap = resize(bitmap);
            String image = bitmapToByteArray(bitmap);
        }
    }
    private Bitmap resize(Bitmap bm){
        Configuration config = getResources().getConfiguration();
        if(config.smallestScreenWidthDp>=800)
            bm = Bitmap.createScaledBitmap(bm, 400, 240, true);
        else if(config.smallestScreenWidthDp>=600)
            bm = Bitmap.createScaledBitmap(bm, 300, 180, true);
        else if(config.smallestScreenWidthDp>=400)
            bm = Bitmap.createScaledBitmap(bm, 200,  120, true);
        else if(config.smallestScreenWidthDp>=360)
            bm = Bitmap.createScaledBitmap(bm, 180, 108, true);
        else
            bm = Bitmap.createScaledBitmap(bm, 160, 96, true);
        return bm;
    }
    /**비트맵을 바이너리 바이트배열로 바꾸어주는 메서드 */
    public String bitmapToByteArray(Bitmap bitmap){
        String image = "";
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        image = "&image="+byteArrayToBinaryString(byteArray);
        return image;
    }
    /**바이너리 바이트 배열을 스트링으로 바꾸어주는 메서드*/
    public static String byteArrayToBinaryString(byte[] b){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < b.length; i++){
            sb.append(byteToBinaryString(b[i]));
        }
        return sb.toString();
    }
    /**바이너리 바이트를 스트링으로 바꾸어주는 메서드*/
    public static String byteToBinaryString(byte n){
        StringBuilder sb = new StringBuilder("00000000");
        for(int bit = 0;bit < 8; bit++){
            if(((n>>bit)&1)>0){
                sb.setCharAt(7-bit,'1');
            }
        }
        return sb.toString();
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