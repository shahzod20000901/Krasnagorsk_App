package com.example.krasnagorsk_app.Search;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.krasnagorsk_app.Full_Info_Activity;
import com.example.krasnagorsk_app.Liked.LikedActivity;
import com.example.krasnagorsk_app.R;
import com.example.krasnagorsk_app.Utils.ItemProduct;
import com.example.krasnagorsk_app.ViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class CameraFragment extends Fragment {
    private static final String TAG="CameraFragment";


    private DatabaseReference myRef;
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://krasnagorsk-app-default-rtdb.europe-west1.firebasedatabase.app/");
    private StorageReference mStorageReferance= FirebaseStorage.getInstance().getReference("ImageDB");

    private RecyclerView recyclerView;
    private FirebaseRecyclerAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    private static int local=1;
    //private ImageView liked;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_camera, container, false);
        recyclerView = view.findViewById(R.id.list);
        myRef=database.getReference();





        Query query=FirebaseDatabase.getInstance("https://krasnagorsk-app-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Products");
        FirebaseRecyclerOptions<ItemProduct> options=new FirebaseRecyclerOptions.Builder<ItemProduct>()
                .setQuery(query, new SnapshotParser<ItemProduct>() {
                    @NonNull
                    @Override
                    public ItemProduct parseSnapshot(@NonNull DataSnapshot snapshot) {
                        return new ItemProduct(snapshot.child("product_name").getValue().toString(),
                                snapshot.child("product_price").getValue().toString(),
                                snapshot.child("currentDate").getValue().toString(),
                                snapshot.child("image_uri").getValue().toString(),
                                snapshot.child("product_description").getValue().toString(),
                                snapshot.child("product_address").getValue().toString());

                    }
                })
                .build();

        adapter = new FirebaseRecyclerAdapter<ItemProduct, ViewHolder>(options) {
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_product, parent, false);

                return new ViewHolder(view);
            }


            @Override
            protected void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") final int position, ItemProduct model) {

                holder.setTxtDate(model.getId_date());
                holder.setTxtName(model.getProduct_name());
                holder.setTxtPrice(model.getProduct_price());
                holder.setTxtaddress(model.getProduct_adres());
                Glide.with(getContext())
                        .load(model.getImage_url())
                        .placeholder(R.drawable.default_place_holder)
                        .centerCrop()
                        .into(holder.image_url);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String productId=model.getProduct_name();
                        String productPrice= model.getProduct_price();
                        String imageUrl=model.getImage_url();
                        String productDes=model.getProduct_description();
                        String product_adres= model.getProduct_adres();

                        Intent intent=new Intent(getActivity(), Full_Info_Activity.class);
                        intent.putExtra("productId",productId);
                        intent.putExtra("productPrice", productPrice);
                        intent.putExtra("imageUrl", imageUrl);
                        intent.putExtra("productDescription", productDes);
                        intent.putExtra("product_address", product_adres);
                        startActivity(intent);
                    }
                });

                holder.liked.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        local++;
                        if(local%2==0)
                        {
                            holder.liked.setImageResource(R.drawable.liked_on);
                            Toast.makeText(holder.liked.getContext(), "Добавлено в список избранных", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getActivity(), LikedActivity.class);
                            String productName= model.getProduct_name();
                            intent.putExtra("productName",productName);
                            startActivity(intent);


                        }
                        else if(local%2==1)
                        {
                            holder.liked.setImageResource(R.drawable.ic_liked_off);
                        }
                    }
                });

                /*

                liked=view.findViewById(R.id.is_liked_id);

                liked.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {



                    }
                });
                */


            }

        };

        linearLayoutManager=new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        adapter.startListening();





        return view;
    }

}
