package com.example.krasnagorsk_app.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.krasnagorsk_app.Profile.ProfileActivity;
import com.example.krasnagorsk_app.R;
import com.example.krasnagorsk_app.Utils.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText id_username, id_email, id_phone_number, id_password, id_password_check;
    public String password_checked;
    private ProgressBar progressBar;
    private Button sign_up_user;
    private FirebaseAuth mAuth;

    private FirebaseDatabase mData=FirebaseDatabase.getInstance();
    private  DatabaseReference myRef;
    private FirebaseAuth.AuthStateListener mAuthlistener;
    private final String USERDATA="Users";
    public static FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        myRef=mData.getReference(USERDATA);
        init();

        sign_up_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
            }
        });

        mAuthlistener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user =firebaseAuth.getCurrentUser();
                if(user!=null)
                {
                    Intent intent=new Intent(RegisterActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }else
                {

                }
            }
        };

        user=mAuth.getCurrentUser();
        if(user!=null)
        {
            Intent intent=new Intent(RegisterActivity.this, ProfileActivity.class);
            startActivity(intent);
        }


    }

    public void createAccount() {
        String username = id_username.getText().toString();
        String email = id_email.getText().toString();
        String phone_number = id_phone_number.getText().toString();
        String password=id_password.getText().toString();

        //int password = Integer.parseInt(id_password.getText().toString());
        //int password_check = Integer.parseInt(id_password_check.getText().toString());

        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Введите имя", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Введите электронную почту", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(phone_number)) {
            Toast.makeText(this, "Введите номер телефона", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT).show();

        }
        else {
            progressBar.setVisibility(View.VISIBLE);

            ValidatePhone(username, email, phone_number, password);

            registration(email, password);

        }

    }

    public void ValidatePhone(String username, String email, String phone_number, String password) {
        String id= myRef.getKey();
        User user=new User(id,username, email, phone_number, password);
        myRef.push().setValue(user);


    }

    public void registration(String email, String password) {
        Log.d("Register Activity:", email+password);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (  task.isSuccessful())
                {
                    Toast.makeText(RegisterActivity.this, "Регистрация прошла успешно!!!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "Регистрация провалено!!!", Toast.LENGTH_SHORT).show();
                   // Log.d("Register Activity: ", "Registration failed");
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });

    }



    private void init() {
        id_username = findViewById(R.id.id_username);
        id_email = findViewById(R.id.id_email);
        id_phone_number = findViewById(R.id.id_phone_number);
        id_password = findViewById(R.id.id_password);
        id_password_check = findViewById(R.id.id_password_check);
        progressBar = findViewById(R.id.id_progressBar);
        sign_up_user = findViewById(R.id.btn_sign_up_user);

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);

    }


}
