package org.techtown.letseat.restaurant;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.techtown.letseat.R;
import org.techtown.letseat.util.PhotoSave;

public class RestaurantInfoActivity extends AppCompatActivity {
    private final int GET_GALLERY_IMAGE = 200;
    private final String[] items = {"한식", "중식", "일식", "양식"};
    String name, phoneNumber, openTime, resIntro, businessNumber, resType, location, image;
    private int aloneAble;
    private TextView textView;
    private EditText nameEdit, phoneNumEdit, openTimeEdit, introEdit, businessEdit, locationEdit;
    private RadioButton singleMealYes, singleMealNo;
    private Button info_edit_btn;
    private ImageView restaurant_image = null;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.store_item_info_fragment);
        // Bundle 값 가져오기
        name = bundle.getString("name");
        phoneNumber = bundle.getString("phoneNumber");
        openTime = bundle.getString("openTime");
        resIntro = bundle.getString("resIntro");
        businessNumber = bundle.getString("businessNumber");
        resType = bundle.getString("resType");
        location = bundle.getString("location");
        image = bundle.getString("image");
        aloneAble = bundle.getInt("aloneAble");
        // 초기 설정
        singleMealYes = findViewById(R.id.store_register_yes);
        singleMealNo = findViewById(R.id.store_register_no);
        textView = findViewById(R.id.info_textView18);
        nameEdit = findViewById(R.id.info_resName);
        phoneNumEdit = findViewById(R.id.info_phoneNumber);
        openTimeEdit = findViewById(R.id.info_openTime);
        introEdit = findViewById(R.id.info_resIntro);
        businessEdit = findViewById(R.id.Info_businessNumber);
        locationEdit = findViewById(R.id.info_location);
        restaurant_image = (ImageView) findViewById(R.id.restaurant_image);
        bitmap = PhotoSave.StringToBitmap(image);

        info_edit_btn = findViewById(R.id.info_btn);
        info_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (singleMealYes.isChecked()) {
                    aloneAble = 1;
                } else if (singleMealNo.isChecked()) {
                    aloneAble = 0;
                }
            }
        });
        // 불러온 값 집어넣기
        nameEdit.setText(name);
        phoneNumEdit.setText(phoneNumber);
        openTimeEdit.setText(openTime);
        introEdit.setText(resIntro);
        locationEdit.setText(location);
        if (aloneAble == 1) {
            singleMealYes.setChecked(true);
        } else {
            singleMealNo.setChecked(true);
        }
        textView.setText(resType);
        restaurant_image.setImageBitmap(bitmap);

        Spinner spinner = findViewById(R.id.spinner);
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
        restaurant_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });
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
}
