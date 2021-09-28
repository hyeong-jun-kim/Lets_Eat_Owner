package org.techtown.letseat.order;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.techtown.letseat.R;

public class ItemActivity extends AppCompatActivity{
    private Intent intent;
    private TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13;
    private int number;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_item);

        intent = getIntent();
        number = intent.getIntExtra("number", -1);

        textView1 = findViewById(R.id.order_item_text1);
        textView2 = findViewById(R.id.order_item_text2);
        textView3 = findViewById(R.id.order_item_text3);
        textView4 = findViewById(R.id.order_item_text4);
        textView5 = findViewById(R.id.order_item_text5);
        textView6 = findViewById(R.id.order_item_text6);
        textView7 = findViewById(R.id.order_item_text7);
        textView8 = findViewById(R.id.order_item_text8);
        textView9 = findViewById(R.id.order_item_text9);
        textView10 = findViewById(R.id.order_item_text10);
        textView11 = findViewById(R.id.order_item_text11);
        textView12 = findViewById(R.id.order_item_text12);
        textView13 = findViewById(R.id.order_item_text13);

        switch (number)
        {
            case 0:
            textView3.setText("2021-09-28-23:17");
            textView5.setText("1번 테이블");
            textView7.setText("마라탕 1인분, 해물탕 1인분");
            textView9.setText("보통맛으로 해주세요");
            textView11.setText("20,000원");
            textView13.setText("카카오페이 결제완료");
            break;

            case 1:
            textView3.setText("2021-09-28-23:28");
            textView5.setText("2번 테이블");
            textView7.setText("마라탕 2인분");
            textView9.setText("매운맛으로 해주세요");
            textView11.setText("20,000원");
            textView13.setText("현장결제");
            break;
        }
    }
}
