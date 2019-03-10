package com.rizkytm.bhaskara;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int[] images = {R.drawable.ic_teori, R.drawable.ic_latihan, R.drawable.ic_wrap_text, R.drawable.ic_videogame ,R.drawable.ic_kamus};
    String[] judul = {"Teori", "Latihan", "Essay", "Game", "Kamus"};
    String[] desc = {"Ini adalah deskripsi teori", "Ini adalah deskripsi latihan", "Ini adalah deskripsi essay", "Ini adalah deskripsi game", "Ini adalah deskripsi kamus"};

    int[] imagesA = {R.drawable.ka, R.drawable.ga, R.drawable.nga, R.drawable.ca, R.drawable.ja, R.drawable.nya,
            R.drawable.fb, R.drawable.ig, R.drawable.ln, R.drawable.tw, R.drawable.wa, R.drawable.yt};
    int[] imagesB = {R.drawable.huruf_ka, R.drawable.huruf_ga, R.drawable.huruf_nga, R.drawable.huruf_ca, R.drawable.huruf_ja, R.drawable.huruf_nya,
            R.drawable.facebook, R.drawable.instagram, R.drawable.line, R.drawable.twitter, R.drawable.whatsapp, R.drawable.youtube};


    ListView listView;
    ListAdapter listAdapter;

    private BhaskaraDB bhaskaraDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadImages();
        listView = (ListView) findViewById(R.id.listView);
        listAdapter = new ListAdapter(MainActivity.this, judul, desc, images);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(MainActivity.this, TheoryActivity.class);
                    startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(MainActivity.this, LatihanActivity.class);
                    startActivity(intent);
                } else if (position == 2) {
                    Intent intent = new Intent(MainActivity.this, MenuEssayActivity.class);
                    startActivity(intent);
                } else if (position == 3) {
                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
                    startActivity(intent);
                } else if (position == 4) {
                    Intent intent = new Intent(MainActivity.this, KamusActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void loadImages() {
        bhaskaraDB = new BhaskaraDB(this);
        for (int i=0; i<imagesA.length; i++) {
            String name = getResources().getResourceEntryName(imagesA[i]);
            int imageA = imagesA[i];
            int imageB = imagesB[i];
            Image image = new Image(name, imageA, imageB);
            bhaskaraDB.tambah(image);
        }
    }
}
