package org.techtown.letseat.restaurant;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.techtown.letseat.R;

public class RestaurantItemInfoFragment extends Fragment{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public RestaurantItemInfoFragment() {
        // Required empty public constructor
    }

    public static RestaurantItemInfoFragment newInstance(String param1, String param2) {
        RestaurantItemInfoFragment fragment = new RestaurantItemInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.restaurant_item_info_fragment, container, false);

        singleMealYes = view.findViewById(R.id.store_register_yes);
        singleMealNo = view.findViewById(R.id.store_register_no);
        textView = view.findViewById(R.id.textView);

        info_edit_btn = view.findViewById(R.id.info_edit_btn);
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

        Spinner spinner = view.findViewById(R.id.spinner);
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
                textView.setText("");
            }
        });

        restaurant_image = (ImageView) view.findViewById(R.id.restaurant_image);
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
}


