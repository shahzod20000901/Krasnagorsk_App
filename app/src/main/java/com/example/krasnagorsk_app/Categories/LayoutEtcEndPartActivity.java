package com.example.krasnagorsk_app.Categories;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.krasnagorsk_app.Address.AddressActivity;
import com.example.krasnagorsk_app.R;
import com.example.krasnagorsk_app.Search.SearchActivity;
import com.example.krasnagorsk_app.Utils.Goods;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LayoutEtcEndPartActivity extends AppCompatActivity {
    private Button btn_add_product,  select_address;
    private ImageView help_question;
    private TextView id_tv_product_address;
    private ProgressBar progressBar;
    private StorageReference mStorageReferance;
    private DatabaseReference myRef;
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://krasnagorsk-app-default-rtdb.europe-west1.firebasedatabase.app/");
    private Uri uploadUri= StringHelper.uploadUri;
    String product_address;
    private ImageView set_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_etc_end_part);
        id_tv_product_address =findViewById(R.id.id_tv_product_address);
        myRef = database.getReference("Products");
        mStorageReferance= FirebaseStorage.getInstance().getReference("ImageDB");
        btn_add_product=findViewById(R.id.btn_add_product);
        select_address=findViewById(R.id.select_address);
        progressBar=findViewById(R.id.etc_endPart_progressBar);
        help_question=findViewById(R.id.help_question);


        set_image=StringHelper.set_image;
        select_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LayoutEtcEndPartActivity.this, AddressActivity.class);

                startActivity(intent);
            }
        });


        btn_add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadProductDB();
                Intent intent=new Intent(LayoutEtcEndPartActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        help_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(
                        LayoutEtcEndPartActivity.this, R.style.BottonSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(getApplicationContext())
                        .inflate(
                                R.layout.layout_bottom_sheet_help_question,
                                (LinearLayout)findViewById(R.id.bottomSheetContainer)
                        );

                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });

        id_tv_product_address.setText(StringHelper.address);
        product_address=id_tv_product_address.getText().toString();
    }

    private void uploadProductDB()
    {
        String product_name=StringHelper.product_name;
        String product_description=StringHelper.product_description;
        String product_price=StringHelper.product_price;
        String product_id=StringHelper.product_id;
        String currentDate = StringHelper.currentDate;

        uploadImage();
        Goods product=new Goods(product_id, product_name, product_description, product_price, uploadUri.toString(), currentDate, product_address);
        progressBar.setVisibility(View.VISIBLE);
        myRef.push().setValue(product).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(LayoutEtcEndPartActivity.this, "Товар добален!!!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(LayoutEtcEndPartActivity.this, "Ошибка!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void uploadImage()
    {
        progressBar.setVisibility(View.VISIBLE);
        Bitmap bitMap=((BitmapDrawable) set_image.getDrawable()).getBitmap();
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitMap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] byteArray=baos.toByteArray();
        final StorageReference mSRef=mStorageReferance.child(System.currentTimeMillis()+"my_Image");
        UploadTask up=mSRef.putBytes(byteArray);
        Task<Uri> task=up.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                return mSRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful())
                {
                    uploadUri=task.getResult();
                    Toast.makeText(LayoutEtcEndPartActivity.this, "Изображение загружено!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else
                {
                    Toast.makeText(LayoutEtcEndPartActivity.this, "Ошибка загрузки изображение", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }

            }
        });

    }

}