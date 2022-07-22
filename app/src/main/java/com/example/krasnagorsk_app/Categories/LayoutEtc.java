package com.example.krasnagorsk_app.Categories;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.krasnagorsk_app.Ads.AdsActivity;
import com.example.krasnagorsk_app.R;
import com.example.krasnagorsk_app.Search.SearchActivity;
import com.example.krasnagorsk_app.Utils.Goods;
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

public class LayoutEtc extends AppCompatActivity {

    private ImageView help_question, set_image;
    private static final int GALLERYPICK=1;
    private TextView text_add_photo;
    private StorageReference mStorageReferance;
    private ProgressBar progressBar;
    private Uri uploadUri;
    private EditText edit_product_name, edit_product_description,edit_product_price;
    private Button btn_add_product;
    private DatabaseReference myRef;
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://krasnagorsk-app-default-rtdb.europe-west1.firebasedatabase.app/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_etc);
        init();

        help_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(
                        LayoutEtc.this, R.style.BottonSheetDialogTheme
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

        btn_add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadProductDB();
                Intent intent=new Intent(LayoutEtc.this, AdsActivity.class);
                startActivity(intent);
            }
        });

        set_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenGallery();
            }
        });
    }

    private void init(){
        help_question=findViewById(R.id.help_question);
        set_image=findViewById(R.id.set_image);
        text_add_photo=findViewById(R.id.text_add_photo);
        mStorageReferance= FirebaseStorage.getInstance().getReference("ImageDB");
        progressBar=findViewById(R.id.etc_progressBar);
        edit_product_name=findViewById(R.id.id_edit_product_name);
        edit_product_description=findViewById(R.id.id_edit_product_description);
        edit_product_price=findViewById(R.id.id_edit_product_price);
        btn_add_product=findViewById(R.id.btn_add_productDB);
        myRef = database.getReference("Products");
    }

    private void OpenGallery() {
        Intent galleryIntent= new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GALLERYPICK);

    }

    private void uploadProductDB()
    {
        String product_name=edit_product_name.getText().toString();
        String product_description=edit_product_description.getText().toString();
        String product_price=edit_product_price.getText().toString();

        String product_id=myRef.getKey();

        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        uploadImage();
        Goods product=new Goods(product_id, product_name, product_description, product_price, uploadUri.toString(), currentDate);
        progressBar.setVisibility(View.VISIBLE);
        myRef.push().setValue(product).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(LayoutEtc.this, "Товар добален!!!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(LayoutEtc.this, "Ошибка!!!", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(LayoutEtc.this, "Изображение загружено!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else
                {
                    Toast.makeText(LayoutEtc.this, "Ошибка загрузки изображение", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GALLERYPICK && resultCode==RESULT_OK && data!=null)
        {
            uploadUri=data.getData();
            set_image.setImageURI(uploadUri);
            text_add_photo.setVisibility(View.INVISIBLE);
        }
    }

}