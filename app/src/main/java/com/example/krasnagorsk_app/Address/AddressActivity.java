package com.example.krasnagorsk_app.Address;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.krasnagorsk_app.Categories.LayoutEtc;
import com.example.krasnagorsk_app.Categories.LayoutEtcEndPartActivity;
import com.example.krasnagorsk_app.R;
import com.example.krasnagorsk_app.Utils.StringHelper;

public class AddressActivity extends AppCompatActivity {

    String defined_address;
    MyExpandableAdapter myExpandableAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        myExpandableAdapter=new MyExpandableAdapter(this);

        ExpandableListView expandableListView=(ExpandableListView) findViewById(R.id.listview_address);
        expandableListView.setAdapter(myExpandableAdapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                defined_address=myExpandableAdapter.getGroup(i).toString()+" "+myExpandableAdapter.getChild(i,i1).toString();
                Intent intent=new Intent(AddressActivity.this, LayoutEtcEndPartActivity.class);
                StringHelper.address=defined_address;
                startActivity(intent);

                return false;
            }

        });


    }
}