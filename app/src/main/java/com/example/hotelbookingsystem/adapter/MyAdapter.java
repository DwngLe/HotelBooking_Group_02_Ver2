package com.example.hotelbookingsystem.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

import com.example.hotelbookingsystem.activities.Profile;
import com.example.hotelbookingsystem.R;
import com.example.hotelbookingsystem.activities.adminViewGuestManager;

import org.w3c.dom.Text;

public class MyAdapter extends BaseAdapter {

    public static final String SHARED_PREF_NAME = "mypref";
    public static final String KEY_un = "username";
    SharedPreferences sharedpreferences;

    Context context;
    ArrayList<Profile> arrayList;
    Button viewGM;

    public MyAdapter(Context context, ArrayList<Profile> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.custom_layout,null);
        TextView fn = (TextView) view.findViewById(R.id.admin_fmg);
        TextView ln = (TextView) view.findViewById(R.id.admin_lmg);
        TextView rl = (TextView) view.findViewById(R.id.admin_rmg);
        final TextView un = (TextView) view.findViewById(R.id.admin_umg);
        viewGM = (Button) view.findViewById(R.id.admin_viewGM);

        Profile profile = arrayList.get(i);

        fn.setText(profile.getFirstName());
        ln.setText(profile.getLastName());
        rl.setText(profile.getRole());
        un.setText(profile.getUsername());


        viewGM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sharedpreferences = context.getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
//
                SharedPreferences.Editor session = sharedpreferences.edit();
                session.putString(KEY_un, un.getText().toString());
                session.apply();

                Intent intent = new Intent(view.getContext(), adminViewGuestManager.class);
                view.getContext().startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }
}
