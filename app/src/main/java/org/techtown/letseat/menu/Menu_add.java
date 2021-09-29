package org.techtown.letseat.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import org.techtown.letseat.R;

import java.io.ByteArrayOutputStream;

public class Menu_add extends AppCompatActivity {

    private final int GET_GALLERY_IMAGE = 200;
    private EditText menuNameEdit, menuPriceEdit, menuDescriptionEdit;
    private ImageView menuImage;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_add);

        menuImage = (ImageView) findViewById(R.id.menu_image);
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK
                && data != null && data.getData() != null) {

            Uri selectedImageUri = data.getData();
            menuImage.setImageURI(selectedImageUri);
            BitmapDrawable drawable = (BitmapDrawable)menuImage.getDrawable();
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
    }/*
    // 로그인 POST 요청
    void login(){
        String url = "http://125.132.62.150:8000/letseat/login/normal";
        JSONObject postData = new JSONObject();
        try {
            postData.put("email", email_string);
            postData.put("password", pwd_string);
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
                        // 자동 로그인 값 넣어주기
                        SharedPreferences pref                                                                                                                                                                                                                                                                                          = getSharedPreferences("login", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("email", input_email.getText().toString());
                        editor.putString("pwd", input_password.getText().toString());
                        editor.commit();
                        // 화면 전환
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    }
                },
                new Response.ErrorListener() {
                    @Override // 에러 발생 시
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error",error.toString());
                        println("아이디나 비밀번호를 다시 확인해주세요.");
                    }
                }
        );
        request.setShouldCache(false); // 이전 결과 있어도 새로 요청해 응답을 보내줌
        AppHelper.requestQueue = Volley.newRequestQueue(this); // requsetQueue 초기화
        AppHelper.requestQueue.add(request);
    }*/
}