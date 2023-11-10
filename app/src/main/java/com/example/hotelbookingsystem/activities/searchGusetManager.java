package com.example.hotelbookingsystem.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelbookingsystem.R;
import com.example.hotelbookingsystem.adapter.MyAdapter;
import com.example.hotelbookingsystem.model.Profile;

import java.util.ArrayList;

public class searchGusetManager extends AppCompatActivity {
//Day la Duong Le
    Button search,logout;
    EditText lastName;
    ListView lv_customerList;
    ArrayList<Profile> arrayList;
    MyAdapter myAdapter;
    TextView tvNumOfResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.search_guest_manager);
        getSupportActionBar().setTitle("Search User");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lastName = findViewById(R.id.admin_lastname);
        search = findViewById(R.id.admin_search);
        lv_customerList = findViewById(R.id.admin_list);
        tvNumOfResult = findViewById(R.id.search_guest_manager_tv_numberOfResult);
//        logout = findViewById(R.id.searchAdminLogout);

//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(searchGusetManager.this,MainActivity.class));
//            }
//        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String abc = lastName.getText().toString();
                DBManager dbManager = new DBManager(searchGusetManager.this);
                arrayList = dbManager.getAllUsers(abc);
                int number = arrayList.size();
                if(number > 0) {
                    myAdapter = new MyAdapter(searchGusetManager.this, arrayList);
                    lv_customerList.setAdapter(myAdapter);
                    myAdapter.notifyDataSetChanged();
                }
                tvNumOfResult.setText("Found " + number + " results");

//                Cursor cursor = dbManager.getEveryone(abc);
//
//
//                String fn,ln,role;
//
//                List<Profile> ans = new ArrayList<>();
//
//
//                if(cursor.getCount()>0)
//                {
//                    while(cursor.moveToNext())
//                    {
//                     //   String un = cursor.getString(1);
//                        fn = cursor.getString(3);
//                        ln = cursor.getString(4);
//                        role = cursor.getString(15);
//
//                        Profile profile = new Profile(role,ln,fn);
//                        ans.add(profile);
//                    }
//                }
//
//
//
//                ArrayAdapter newS = new ArrayAdapter<Profile>(searchGusetManager.this,android.R.layout.simple_list_item_1,ans);
//                lv_customerList.setAdapter(newS);


              //  Toast.makeText(searchGusetManager.this,profile.toString(), Toast.LENGTH_SHORT).show();


            }
        });

//        String abc = lastName.getText().toString();
    }
}
