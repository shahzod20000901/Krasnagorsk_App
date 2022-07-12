package com.example.krasnagorsk_app.Search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.krasnagorsk_app.R;
import com.example.krasnagorsk_app.Utils.BottomNavigationViewHelper;
import com.google.android.material.tabs.TabLayout;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class SearchActivity extends AppCompatActivity {

String TAG="SearchActivity.this";

private Context mContext=SearchActivity.this;
public static final int ACTIVITY_NUM=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setupBottomNavigationViewEx();
        setupViewPager();


    }

    /*
    Responsible for adding 3 tabs: Camera, Home, Messages
     */
    private void setupViewPager()
    {
        SectionsPaterAdapter adapter=new SectionsPaterAdapter(getSupportFragmentManager());
        adapter.addFragment(new CameraFragment());
        adapter.addFragment(new HomeFragment());
        adapter.addFragment(new MessagesFragment());

        ViewPager viewPager=(ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(adapter);


        TabLayout tabLayout=(TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText(R.string.recomen);
        tabLayout.getTabAt(1).setText(R.string.fresh);
        tabLayout.getTabAt(2).setText(R.string.aroundyou);


    }



    /*
    BottomNavigatinView setup
     */

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