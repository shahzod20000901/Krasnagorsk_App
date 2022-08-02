package com.example.krasnagorsk_app.Liked;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.krasnagorsk_app.R;
import com.example.krasnagorsk_app.Utils.BottomNavigationViewHelper;
import com.example.krasnagorsk_app.Utils.ItemProduct;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

public class LikedActivity extends AppCompatActivity {

    private static final int ACTIVITY_NUM=1;
    private Context mContext=LikedActivity.this;
    private String TAG="LikedActivity.this";
    private ImageView id_menu_liked;
    private RecyclerView liked_recyclerview;
    private ArrayList<ItemProduct> itemProducts=new ArrayList<>();
    private static int counter=0;

    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked);
        init();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setupBottomNavigationViewEx();



        String product_name=getIntent().getExtras().get("productName").toString();
        String[] data={product_name};

        data[counter]=product_name;
        // ItemProduct itemProduct= new ItemProduct();
        //itemProduct= new ItemProduct(product_name, "","","","");
        //itemProducts.add(new ItemProduct(product_name, "","","",""));
        LikedAdapter likedAdapter=new LikedAdapter(itemProducts, this);

        ItemProduct itemProduct=new ItemProduct(product_name, "","","","","");
        itemProducts.add(itemProduct);
        likedAdapter.notifyDataSetChanged();


        linearLayoutManager=new LinearLayoutManager(this);
        liked_recyclerview.setLayoutManager(linearLayoutManager);
        liked_recyclerview.setHasFixedSize(true);


        liked_recyclerview.setAdapter(likedAdapter);




    }

    private void init(){
        id_menu_liked=findViewById(R.id.id_menu_liked);
        liked_recyclerview=findViewById(R.id.liked_recyclerview);

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
