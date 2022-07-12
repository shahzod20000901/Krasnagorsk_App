package com.example.krasnagorsk_app.Categories;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.krasnagorsk_app.Ads.AdsActivity;
import com.example.krasnagorsk_app.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class LayoutEtc extends AppCompatActivity {

    private ImageView help_question, set_image;
    private static final int GALLERYPICK=1;
    private Uri imageUri;
    private TextView text_add_photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_etc);
        init();

        help_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(
                        LayoutEtc.this, R.style.BottonSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(getApplicationContext())
                        .inflate(
                                R.layout.layout_bottom_sheet_help_question,
                                (LinearLayout)findViewById(R.id.bottomSheetContainer)
                        );

                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });

        set_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenGallery();
            }
        });
    }

    private void init(){
        help_question=findViewById(R.id.help_question);
        set_image=findViewById(R.id.set_image);
        text_add_photo=findViewById(R.id.text_add_photo);
    }

    private void OpenGallery() {
        Intent galleryIntent= new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GALLERYPICK);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GALLERYPICK && resultCode==RESULT_OK && data!=null)
        {
            imageUri=data.getData();
            set_image.setImageURI(imageUri);
            text_add_photo.setVisibility(View.INVISIBLE);
        }
    }

}