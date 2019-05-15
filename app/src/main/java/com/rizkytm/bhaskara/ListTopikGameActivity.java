package com.rizkytm.bhaskara;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListTopikGameActivity extends AppCompatActivity {

    ListView topikListView;
    public static final String EXTRA_TITLE = "extraTitle";
    public static final String EXTRA_CATEGORY_ID = "extraCategoryID";
    public static final String EXTRA_SCORE = "extraScore";
    public List<TopikGame> topikGames = new ArrayList<>();
    BhaskaraDB dbHelper;
    public TopikGameAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_topik_game);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    public List<TopikGame> getTopikList() {
        topikGames.clear();
        dbHelper = new BhaskaraDB(getApplicationContext());
        dbHelper.getWritableDatabase();
        List<TopikGame> topikList = dbHelper.getAllTopikGame();
        String judul;
        int id;
        int skor;
        for (int i = 0; i<topikList.size();i++) {
            judul = topikList.get(i).getJudul();
            id = topikList.get(i).getId();
            skor = topikList.get(i).getSkor();

            TopikGame topik = new TopikGame(id, judul, skor);

            topikGames.add(topik);
        }
        dbHelper.close();

        return topikGames;
    }

    @Override
    protected void onResume() {
        super.onResume();
        topikListView = (ListView)findViewById(R.id.list_topik);
        adapter = new TopikGameAdapter(getApplicationContext(), getTopikList());
        adapter.notifyDataSetChanged();
        topikListView.setAdapter(adapter);
        topikListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListTopikGameActivity.this, MenuGameActivity.class);
                intent.putExtra(EXTRA_TITLE, topikGames.get(position).getJudul());
                intent.putExtra(EXTRA_CATEGORY_ID, topikGames.get(position).getId());
                intent.putExtra(EXTRA_SCORE, topikGames.get(position).getSkor());
                startActivity(intent);
            }
        });
    }
}
