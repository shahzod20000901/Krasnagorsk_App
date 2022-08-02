package com.example.krasnagorsk_app.Liked;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.krasnagorsk_app.R;
import com.example.krasnagorsk_app.Utils.ItemProduct;

import java.util.ArrayList;

public class LikedAdapter extends RecyclerView.Adapter<LikedAdapter.MyViewHolder> {

    ArrayList<ItemProduct> itemProducts;
    Context context;

    public LikedAdapter(ArrayList<ItemProduct> itemProducts, Context context) {
        this.itemProducts=itemProducts;
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.liked_item_product, parent, false);
        MyViewHolder myViewHolder=new MyViewHolder(view);


        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ItemProduct itemProduct= itemProducts.get(position);
        holder.id_product_name.setText(itemProduct.getProduct_name());
        holder.id_product_price.setText(itemProduct.getProduct_price());
        holder.id_product_date.setText(itemProduct.getId_date());
        Glide.with(context)
                .load(itemProduct.getImage_url())
                .placeholder(R.drawable.default_place_holder)
                .error(R.drawable.error_place_holder)
                .centerCrop()
                .into(holder.id_product_image);



    }

    @Override
    public int getItemCount() {
        return itemProducts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView id_product_image,id_product_liked_on;
        TextView id_product_name, id_product_price, id_prodcut_address, id_product_date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_product_image=itemView.findViewById(R.id.id_product_image);
            id_product_liked_on=itemView.findViewById(R.id.id_product_liked_on);
            id_product_name=itemView.findViewById(R.id.id_product_name);
            id_product_price=itemView.findViewById(R.id.id_product_price);
            id_prodcut_address=itemView.findViewById(R.id.id_prodcut_address);
            id_product_date=itemView.findViewById(R.id.id_product_date);



        }
    }
}
