package com.example.krasnagorsk_app;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Context;
import com.google.firebase.database.core.Tag;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class Full_Info_Activity extends AppCompatActivity {

    private static final String TAG="Full_Info_Activity";
    public ImageView product_image;
    private TextView product_name, product_price, product_address, show_in_map, description_product;
    private Button btn_ask_delivery, btn_call_id, btn_message_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_info);
        init();


            String product_name_=getIntent().getExtras().get("productId").toString();
            String product_price_=getIntent().getExtras().get("productPrice").toString();
            String productImage=getIntent().getExtras().get("imageUrl").toString();
            String productDes=getIntent().getExtras().get("productDescription").toString();
            String product_addres=getIntent().getExtras().get("product_address").toString();

            product_name.setText(product_name_);
          product_price.setText(product_price_);
          description_product.setText(productDes);
          product_address.setText(product_addres);

        Glide.with(this)
                .load(productImage)
                .apply(
                        new RequestOptions()
                                .error(R.drawable.error_place_holder)
                                .centerCrop()
                )

                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        //on load failed
                        Toast.makeText(Full_Info_Activity.this, "Ошибка"+e.toString(), Toast.LENGTH_LONG).show();
                        Log.d(TAG,"Image Error: "+e);
                        Log.d(TAG, "URL IMAGE: "+productImage);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        //on load success
                        Toast.makeText(Full_Info_Activity.this, "Готово", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .transition(withCrossFade())
                .into(product_image);





    }

    private void init()
    {
        product_image=findViewById(R.id.product_image);
        product_name=findViewById(R.id.product_name);
        product_price=findViewById(R.id.product_price);
        product_address=findViewById(R.id.product_address);
        show_in_map=findViewById(R.id.show_in_map);
        description_product=findViewById(R.id.description_product);
        btn_ask_delivery=findViewById(R.id.btn_ask_delivery);
        btn_call_id=findViewById(R.id.btn_call_id);
        btn_message_id=findViewById(R.id.btn_message_id);
    }
}