package com.rizkytm.bhaskara;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    public List<Category> topikKuis = new ArrayList<>();
    BhaskaraDB dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_topik_quiz);

        simpleList = (ListView)findViewById(R.id.list_topik);
        final TopikKuisAdapter adapter = new TopikKuisAdapter(getApplicationContext(), getTopikList());
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.listview_item, R.id.judul_topik_kuis, countryList);
        simpleList.setAdapter(adapter);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListTopikQuizActivity.this, LatihanActivity.class);
//                String topik = (String) adapter.getItem(position).toString();
//                TextView textView = (TextView) view.findViewById(R.id.judul_topik_kuis);
//                intent.putExtra(EXTRA_TITLE, textView.getText().toString().trim());
                intent.putExtra(EXTRA_TITLE, topikKuis.get(position).getName());
                intent.putExtra(EXTRA_CATEGORY_ID, topikKuis.get(position).getId());
                startActivity(intent);
            }
        });
    }

    public List<Category> getTopikList() {
        dbHelper = new BhaskaraDB(getApplicationContext());
        dbHelper.getWritableDatabase();
        List<Category> topikList = dbHelper.getAllCategories();
        String judul;
        int id;
        for (int i = 0; i<topikList.size();i++) {
            judul = topikList.get(i).getName();
            id = topikList.get(i).getId();

            Category topik = new Category(judul, id);

            topikKuis.add(topik);
        }
        dbHelper.close();

        return topikKuis;
    }
}
