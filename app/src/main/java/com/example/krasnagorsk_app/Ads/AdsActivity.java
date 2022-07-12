package com.example.krasnagorsk_app.Ads;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.krasnagorsk_app.Categories.LayoutEtc;
import com.example.krasnagorsk_app.Categories.LayoutTransport;
import com.example.krasnagorsk_app.R;
import com.example.krasnagorsk_app.Utils.BottomNavigationViewHelper;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class AdsActivity extends AppCompatActivity {

    String TAG="AdsActivity.this";
    private static final int ACTIVITY_NUM=2;
    private Context mContext=AdsActivity.this;
    private Button add_product_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads);

        setupBottomNavigationViewEx();
        init();
        add_product_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(
                        AdsActivity.this, R.style.BottonSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(getApplicationContext())
                        .inflate(
                                R.layout.layout_bottom_sheet_ads,
                                (LinearLayout)findViewById(R.id.bottomSheetContainer)
                        );
                bottomSheetView.findViewById(R.id.lin_transport).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(AdsActivity.this, LayoutTransport.class);
                        startActivity(intent);
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetView.findViewById(R.id.lin_etc).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(AdsActivity.this, LayoutEtc.class);
                        startActivity(intent);
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });


    }

    private void init(){
        add_product_btn=findViewById(R.id.add_product);
    }


    private void setupBottomNavigationViewEx() {
        Log.d(TAG,"BottomNavigationViewEX: setting up Bottom NavigationViewEx");
        BottomNavigationViewEx bottomNavigationViewEx=(BottomNavigationViewEx) findViewById(R.id.BottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem=menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);

    }
}