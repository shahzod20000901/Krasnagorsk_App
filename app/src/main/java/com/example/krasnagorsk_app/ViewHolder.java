package com.example.krasnagorsk_app;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.krasnagorsk_app.Liked.LikedAdapter;

public class ViewHolder extends RecyclerView.ViewHolder {

    public LinearLayout root;
    public ImageView image_url, liked;
    public TextView product_name_item,product_price_item, id_date, id_product_address;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        root=itemView.findViewById(R.id.list_root);
        image_url=itemView.findViewById(R.id.image_url);
        product_name_item=itemView.findViewById(R.id.product_name_item);
        product_price_item=itemView.findViewById(R.id.product_price_item);
        id_date=itemView.findViewById(R.id.id_date);
        liked=itemView.findViewById(R.id.is_liked_id);
        id_product_address=itemView.findViewById(R.id.id_product_address_);



    }

    public void setTxtName(String string)
    {
        product_name_item.setText(string);
    }

    public void setTxtPrice(String string)
    {
        product_price_item.setText(string);
    }

    public void setTxtDate(String string)
    {
        id_date.setText(string);
    }

    public void setTxtaddress(String address) {
        id_product_address.setText(address);
    }
}
