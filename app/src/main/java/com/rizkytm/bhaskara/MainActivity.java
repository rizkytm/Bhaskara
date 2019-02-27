package com.rizkytm.bhaskara;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int[] images = {R.drawable.ic_teori, R.drawable.ic_latihan, R.drawable.ic_kamus};
    String[] judul = {"Teori", "Latihan", "Kamus"};
    String[] desc = {"Ini adalah deskripsi teori", "Ini adalah deskripsi latihan", "Ini adalah deskripsi kamus"};

    ListView listView;
    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        listAdapter = new ListAdapter(MainActivity.this, judul, desc, images);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(MainActivity.this, TheoryActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
