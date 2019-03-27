package com.sulistyo.mobile2;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    DatabaseHelper mDatabase;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mDatabase = new DatabaseHelper(this);

        listView = (ListView)findViewById(R.id.lvData);

        ArrayList<String> list = new ArrayList<>();
        Cursor data = mDatabase.lihatData();
        if (data.getCount() == 0){
            Toast.makeText(this,"Data tidak ditemukan", Toast.LENGTH_SHORT).show();
        }else {
            while (data.moveToNext()){
                list.add(data.getString(0));
                list.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
                listView.setAdapter(listAdapter);
            }
        }

    }
}
