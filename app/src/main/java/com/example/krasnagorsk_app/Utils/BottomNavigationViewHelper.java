package com.example.krasnagorsk_app.Utils;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.krasnagorsk_app.Ads.AdsActivity;
import com.example.krasnagorsk_app.Liked.LikedActivity;
import com.example.krasnagorsk_app.Messages.MessagesActivity;
import com.example.krasnagorsk_app.Profile.ProfileActivity;
import com.example.krasnagorsk_app.R;
import com.example.krasnagorsk_app.Search.SearchActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class BottomNavigationViewHelper {

    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx)
    {

       bottomNavigationViewEx.enableItemShiftingMode(false);
       bottomNavigationViewEx.enableShiftingMode(false);
      // bottomNavigationViewEx.enableAnimation(false);
       //bottomNavigationViewEx.setTextVisibility(true);

    }

    public static void enableNavigation(final Context context, BottomNavigationViewEx view)
    {
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.Search:
                        Intent intent1=new Intent(context, SearchActivity.class);// ACTIVITY NUM=0
                        context.startActivity(intent1);
                        break;
                    case R.id.Liked:
                        Intent intent2= new Intent(context, LikedActivity.class); // ACTIVITY NUM=1
                        context.startActivity(intent2);
                        break;
                    case R.id.Ads:
                        Intent intent3=new Intent(context, AdsActivity.class);// ACTIVITY NUM=2
                        context.startActivity(intent3);
                        break;
                    case R.id.Messages:
                        Intent intent4=new Intent(context, MessagesActivity.class);// ACTIVITY NUM=3
                        context.startActivity(intent4);
                        break;
                    case R.id.Profile:
                        Intent intent5=new Intent(context, ProfileActivity.class);// ACTIVITY NUM=4
                        context.startActivity(intent5);
                        break;
                }
                return false;
            }
        });
    }


}
