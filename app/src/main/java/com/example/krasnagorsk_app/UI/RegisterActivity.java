package com.example.krasnagorsk_app.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.krasnagorsk_app.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText id_username, id_email, id_phone_number, id_password, id_password_check;
    private ProgressBar progressBar;
    private Button sign_up_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();

        sign_up_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
            }
        });



    }

    private void createAccount() {
        String username=id_username.getText().toString();
        String email=id_email.getText().toString();
        String phone_number=id_phone_number.getText().toString();
        String password=id_password.getText().toString();
        String password_check=id_password_check.getText().toString();

        if(TextUtils.isEmpty(username))
        {
            Toast.makeText(this, "Введите имя", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Введите электронную почту", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(phone_number))
        {
            Toast.makeText(this, "Введите номер телефона", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password_check))
        {
            Toast.makeText(this, "Повторно введите пароль", Toast.LENGTH_SHORT).show();
        }
        else if(!(password==password_check)){
            Toast.makeText(this, "Пароли не совпадают!!!!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            progressBar.setVisibility(View.VISIBLE);

            ValidatePhone(username, email, phone_number, password);
        }

    }

    private void init(){
        id_username=findViewById(R.id.id_username);
        id_email=findViewById(R.id.id_email);
        id_phone_number=findViewById(R.id.id_phone_number);
        id_password=findViewById(R.id.id_password);
        id_password_check=findViewById(R.id.id_password_check);
        progressBar=findViewById(R.id.id_progressBar);
        sign_up_user=findViewById(R.id.btn_sign_up_user);
    }

}