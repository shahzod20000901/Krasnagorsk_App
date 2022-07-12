package com.example.krasnagorsk_app.Messages;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.krasnagorsk_app.R;
import com.example.krasnagorsk_app.Utils.BottomNavigationViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class MessagesActivity extends AppCompatActivity {

    public static final int ACTIVITY_NUM=3;
    public Context mContext=MessagesActivity.this;
    public String TAG="MessagesActivity.this";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setupBottomNavigationViewEx();


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