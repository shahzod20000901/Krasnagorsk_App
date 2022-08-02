package com.example.krasnagorsk_app.Categories;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.krasnagorsk_app.Address.AddressActivity;
import com.example.krasnagorsk_app.R;
import com.example.krasnagorsk_app.Utils.StringHelper;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LayoutEtc extends AppCompatActivity {

    private ImageView set_image;
    private static final int GALLERYPICK=1;
    private TextView text_add_photo;
    private StorageReference mStorageReferance;
    private ProgressBar progressBar;
    private Uri uploadUri;
    private EditText edit_product_name, edit_product_description,edit_product_price;
    private Button btn_continue_add_product;
    private DatabaseReference myRef;
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://krasnagorsk-app-default-rtdb.europe-west1.firebasedatabase.app/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_etc);
        init();





        btn_continue_add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringHelper.product_name= edit_product_name.getText().toString();
                StringHelper.product_description=edit_product_description.getText().toString();
                StringHelper.product_price=edit_product_price.getText().toString();
                StringHelper.product_id=myRef.getKey();
                StringHelper.currentDate=new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

                Intent intent=new Intent(LayoutEtc.this, LayoutEtcEndPartActivity.class);


                startActivity(intent);

            }
        });

        set_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenGallery();
            }
        });

        StringHelper.set_image=set_image;


    }

    private void init(){

        set_image=findViewById(R.id.set_image);
        text_add_photo=findViewById(R.id.text_add_photo);
        mStorageReferance= FirebaseStorage.getInstance().getReference("ImageDB");
        progressBar=findViewById(R.id.etc_progressBar);
        edit_product_name=findViewById(R.id.id_edit_product_name);
        edit_product_description=findViewById(R.id.id_edit_product_description);
        edit_product_price=findViewById(R.id.id_edit_product_price);

        btn_continue_add_product =findViewById(R.id.btn_continue);

        myRef = database.getReference("Products");
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
            uploadUri=data.getData();
            set_image.setImageURI(uploadUri);
            text_add_photo.setVisibility(View.INVISIBLE);
            StringHelper.uploadUri=uploadUri;
        }
    }

}