package com.rizkytm.bhaskara;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class KataActivity extends AppCompatActivity {

    List<Kata> kataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kata);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        kataList = new ArrayList<>();
        kataList.add(new Kata("Aku", "Abdi"));
        kataList.add(new Kata("Kamu", "Maneh"));
        kataList.add(new Kata("Dia", "Anjeunna"));
        kataList.add(new Kata("Kalian", "Aranjeun"));
        kataList.add(new Kata("Ayah", "Bapa"));
        kataList.add(new Kata("Ibu", "Indung"));
        kataList.add(new Kata("Kakak", "Raka"));
        kataList.add(new Kata("Adik", "Rai"));
        kataList.add(new Kata("Kakek", "Aki"));
        kataList.add(new Kata("Nenek", "Nini"));
        kataList.add(new Kata("Keponakan", "Alo"));
        kataList.add(new Kata("Sepupu", "Dulur"));

        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_id);
        RecyclerViewAdapterKata myAdapter = new RecyclerViewAdapterKata(this,kataList);
        myrv.setLayoutManager(new GridLayoutManager(this,3));
        myrv.setAdapter(myAdapter);
    }

}
