package org.techtown.letseat.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kakao.auth.Session;
import com.kakao.usermgmt.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;
import org.techtown.letseat.MainActivity;
import org.techtown.letseat.R;
import org.techtown.letseat.util.AppHelper;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;




public class Login extends AppCompatActivity {
    private Button  btn_register, login_button, sub_login_button;
    private LoginButton kakao_login_button;
    private ImageView fakeKakao;
    private EditText input_email, input_password;
    private String email_string, pwd_string;
    private String ownerId;
    private KaKaoCallBack kaKaoCallBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        fakeKakao = findViewById(R.id.fake_kakao);
        login_button = findViewById(R.id.login_button);
        input_email = findViewById(R.id.input_email);
        input_password = findViewById(R.id.input_password);
        input_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email_string = input_email.getText().toString();
                pwd_string = input_password.getText().toString();
                login();
            }
        });


        btn_register = findViewById(R.id.btnRegister);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });



        kaKaoCallBack = new KaKaoCallBack();
        Session.getCurrentSession().addCallback(kaKaoCallBack);
        Session.getCurrentSession().checkAndImplicitOpen();


        kakao_login_button = findViewById(R.id.kakao_login_button);

        kakao_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        HashKey();

        fakeKakao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kakao_login_button.performClick();
            }
        });

        sub_login_button = findViewById(R.id.sub_login_button);
        sub_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    // 로그인 POST 요청
    void login(){
            String url = "http://125.132.62.150:8000/letseat/login/owner/normal";
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
                            SharedPreferences pref = getSharedPreferences("login",MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("email", input_email.getText().toString());
                            editor.putString("pwd", input_password.getText().toString());
                            editor.commit();
                            get_ownerId();
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
    }

    void get_ownerId(){
        String url = "http://125.132.62.150:8000/letseat/register/owner/get/id?email="+email_string;
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        String ownerId = response;
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        intent.putExtra("ownerId",ownerId);
                        startActivity(intent);
                        finish();
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error",error.toString());
                        println("아이디나 비밀번호를 다시 확인해주세요.");
                    }
                }
        );
        request.setShouldCache(false); // 이전 결과 있어도 새로 요청해 응답을 보내줌
        AppHelper.requestQueue = Volley.newRequestQueue(this); // requsetQueue 초기화
        AppHelper.requestQueue.add(request);
    }

    public void kakaoError(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(kaKaoCallBack);
    }

    private void HashKey() {
        try {
            PackageInfo pkinfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : pkinfo.signatures) {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA");
                messageDigest.update(signature.toByteArray());
                String result = new String(Base64.encode(messageDigest.digest(), 0));
                Log.d("해시", result);
            }
        }
        catch (Exception e) {
        }
    }

    public void println(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
}