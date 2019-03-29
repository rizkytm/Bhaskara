package com.rizkytm.bhaskara;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListTopikEssayActivity extends AppCompatActivity {

    ListView topikListView;
    public static final String EXTRA_TITLE = "extraTitle";
    public static final String EXTRA_CATEGORY_ID = "extraCategoryID";
    public List<TopikEssay> topikEssays = new ArrayList<>();
    BhaskaraDB dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_topik_essay);

        topikListView = (ListView)findViewById(R.id.list_topik);
        final TopikEssayAdapter adapter = new TopikEssayAdapter(getApplicationContext(), getTopikList());
        topikListView.setAdapter(adapter);
        topikListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListTopikEssayActivity.this, MenuEssayActivity.class);
                intent.putExtra(EXTRA_TITLE, topikEssays.get(position).getJudul());
                intent.putExtra(EXTRA_CATEGORY_ID, topikEssays.get(position).getId());
                startActivity(intent);
            }
        });
    }

    public List<TopikEssay> getTopikList() {
        dbHelper = new BhaskaraDB(getApplicationContext());
        dbHelper.getWritableDatabase();
        List<TopikEssay> topikList = dbHelper.getAllTopikEssay();
        String judul;
        int id;
        int skor;
        for (int i = 0; i<topikList.size();i++) {
            judul = topikList.get(i).getJudul();
            id = topikList.get(i).getId();
            skor = topikList.get(i).getSkor();

            TopikEssay topik = new TopikEssay(id, judul, skor);

            topikEssays.add(topik);
        }
        dbHelper.close();

        return topikEssays;
    }
}
