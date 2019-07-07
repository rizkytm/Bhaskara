package com.rizkytm.bhaskara;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class KataActivity extends AppCompatActivity {

//    List<Kata> kataList;
    BhaskaraDB bhaskaraDB;
    ArrayList<Kata> kataList = new ArrayList<>();
    Button buttonSelanjutnya;

    public static final String EXTRA_TITLE = "extraTitle";
    public static final String EXTRA_CATEGORY_ID = "extraCategoryID";
    public static final String EXTRA_SCORE = "extraScore";
    public static final String EXTRA_DIFFICULTY = "extraDifficulty";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kata);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttonSelanjutnya = (Button) findViewById(R.id.btnSelanjutnya);
        buttonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = getIntent();
                String topikName = intents.getStringExtra(ListTopikQuizActivity.EXTRA_TITLE);
                int topikID = intents.getIntExtra(ListTopikQuizActivity.EXTRA_CATEGORY_ID, 0 );
                int skor = intents.getIntExtra(ListTopikQuizActivity.EXTRA_SCORE, 0 );
                int diff = intents.getIntExtra(ListTopikQuizActivity.EXTRA_DIFFICULTY, 0 );

                Intent intent = new Intent(KataActivity.this, LatihanActivity.class);
//                String topik = (String) adapter.getItem(position).toString();
//                TextView textView = (TextView) view.findViewById(R.id.judul_topik_kuis);
//                intent.putExtra(EXTRA_TITLE, textView.getText().toString().trim());
                intent.putExtra(EXTRA_TITLE, topikName);
                intent.putExtra(EXTRA_CATEGORY_ID, topikID);
                intent.putExtra(EXTRA_DIFFICULTY, diff);
                intent.putExtra(EXTRA_SCORE, skor);
                startActivity(intent);
            }
        });

        kataList = loadAllKosaKata();

//        kataList = new ArrayList<>();
//        kataList.add(new Kata("Aku", "Abdi"));
//        kataList.add(new Kata("Kamu", "Maneh"));
//        kataList.add(new Kata("Dia", "Anjeunna"));
//        kataList.add(new Kata("Kalian", "Aranjeun"));
//        kataList.add(new Kata("Ayah", "Bapa"));
//        kataList.add(new Kata("Ibu", "Indung"));
//        kataList.add(new Kata("Kakak", "Raka"));
//        kataList.add(new Kata("Adik", "Rai"));
//        kataList.add(new Kata("Kakek", "Aki"));
//        kataList.add(new Kata("Nenek", "Nini"));
//        kataList.add(new Kata("Keponakan", "Alo"));
//        kataList.add(new Kata("Sepupu", "Dulur"));

        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_id);
        RecyclerViewAdapterKata myAdapter = new RecyclerViewAdapterKata(this,kataList);
        myrv.setLayoutManager(new GridLayoutManager(this,3));
        myrv.setAdapter(myAdapter);
    }

    public ArrayList<Kata> loadAllKosaKata() {
        bhaskaraDB = new BhaskaraDB(this);
        Intent intent = getIntent();
        int topikID = intent.getIntExtra(ListTopikQuizActivity.EXTRA_CATEGORY_ID, 0);
        ArrayList<Kata> kata = bhaskaraDB.getAllKosaKata(topikID);
        return kata;
    }

}
