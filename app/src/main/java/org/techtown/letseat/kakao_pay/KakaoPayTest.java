package org.techtown.letseat.kakao_pay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.techtown.letseat.R;

public class KakaoPayTest extends AppCompatActivity {

    Button button;
    EditText editTextName;
    EditText editTextPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kakao_pay_test);

        editTextName = findViewById(R.id.editName);
        editTextPrice = findViewById(R.id.editPrice);
        button = findViewById(R.id.kakaopay_test_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String price = editTextPrice.getText().toString();

                KaKaoPay kaKaoPay = new KaKaoPay(name, price);

                Intent intent = new Intent(getApplicationContext(), kaKaoPay.getClass());
                startActivity(intent);

            }
        });
    }
}