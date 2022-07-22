package com.example.krasnagorsk_app.Profile;

import static com.example.krasnagorsk_app.UI.RegisterActivity.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.krasnagorsk_app.R;
import com.example.krasnagorsk_app.UI.RegisterActivity;
import com.example.krasnagorsk_app.Utils.BottomNavigationViewHelper;
import com.example.krasnagorsk_app.Utils.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class ProfileActivity extends AppCompatActivity {

    private static final int ACTIVITY_NUM=4;
    private Context mContext=ProfileActivity.this;
    private String TAG="ProfileActivity.this";
    private ImageView profileMenu;
    public static Button btn_sign_up;
    private TextView id_username, id_user_email, id_number_of_username, user_date;

    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://krasnagorsk-app-default-rtdb.europe-west1.firebasedatabase.app/");
    private String USERDATA="Users";
    private DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setupBottomNavigationViewEx();
        init();
        getDataFromDataBase();



        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ProfileActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        if(user==null)
        {
            btn_sign_up.setVisibility(View.VISIBLE);
        }




    }

    private void getDataFromDataBase() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user=null;
                for(DataSnapshot ds: snapshot.getChildren())
                {
                    user=ds.getValue(User.class);
                    assert user!=null;


                }

                id_username.setText(user.username);
                id_number_of_username.setText("Номер телефона: "+user.phone_number);
                id_user_email.setText(user.email);
                user_date.setText("В приложении с "+user.date);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(mContext, "Ошибка при получении данных", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void init(){
        btn_sign_up=findViewById(R.id.btn_sign_up);
        myRef = database.getReference(USERDATA);
        id_username=findViewById(R.id.username);
        id_number_of_username=findViewById(R.id.number_of_username);
        id_user_email=findViewById(R.id.user_email);
        user_date=findViewById(R.id.user_date);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);


        return true;
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