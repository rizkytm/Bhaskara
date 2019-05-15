package com.rizkytm.bhaskara;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListTopikQuizActivity extends AppCompatActivity {

    ListView simpleList;
    String countryList[] = {"India", "China", "australia", "Portugle", "America", "NewZealand"};
    public static final String EXTRA_TITLE = "extraTitle";
    public static final String EXTRA_CATEGORY_ID = "extraCategoryID";
    public static final String EXTRA_SCORE = "extraScore";
    public static final String EXTRA_DIFFICULTY = "extraDifficulty";
    public List<TopikKuis> topikKuis = new ArrayList<>();
    BhaskaraDB dbHelper;
    public TopikKuisAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_topik_quiz);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    public List<TopikKuis> getTopikList() {
        topikKuis.clear();
        dbHelper = new BhaskaraDB(getApplicationContext());
        dbHelper.getWritableDatabase();
        List<TopikKuis> topikList = dbHelper.getAllTopikKuis();
        String judul;
        int id;
        int skor;
        int difficulty_id;
        for (int i = 0; i<topikList.size();i++) {
            judul = topikList.get(i).getJudul();
            id = topikList.get(i).getId();
            skor = topikList.get(i).getSkor();
            difficulty_id = topikList.get(i).getDifficulty_id();

            TopikKuis topik = new TopikKuis(id, judul, skor, difficulty_id);

            topikKuis.add(topik);
        }
        dbHelper.close();

        return topikKuis;
    }

    @Override
    protected void onResume() {
        super.onResume();
        simpleList = (ListView)findViewById(R.id.list_topik);
        adapter = new TopikKuisAdapter(getApplicationContext(), getTopikList());
        adapter.notifyDataSetChanged();
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.listview_item, R.id.judul_topik_kuis, countryList);
        simpleList.setAdapter(adapter);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListTopikQuizActivity.this, LatihanActivity.class);
//                String topik = (String) adapter.getItem(position).toString();
//                TextView textView = (TextView) view.findViewById(R.id.judul_topik_kuis);
//                intent.putExtra(EXTRA_TITLE, textView.getText().toString().trim());
                intent.putExtra(EXTRA_TITLE, topikKuis.get(position).getJudul());
                intent.putExtra(EXTRA_CATEGORY_ID, topikKuis.get(position).getId());
                intent.putExtra(EXTRA_DIFFICULTY, topikKuis.get(position).getDifficulty_id());
                intent.putExtra(EXTRA_SCORE, topikKuis.get(position).getSkor());
                startActivity(intent);
            }
        });
    }
}
