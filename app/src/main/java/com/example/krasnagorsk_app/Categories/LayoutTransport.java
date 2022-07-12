package com.example.krasnagorsk_app.Categories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.krasnagorsk_app.Ads.AdsActivity;
import com.example.krasnagorsk_app.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class LayoutTransport extends AppCompatActivity {

    private ImageView cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_transport);
        init();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LayoutTransport.this, AdsActivity.class);
                startActivity(intent);
            }
        });

    }

    private void init()
    {
        cancel=findViewById(R.id.cancel_transport);
    }
}