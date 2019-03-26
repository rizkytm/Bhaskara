package com.rizkytm.bhaskara;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class AksaraFragment extends Fragment {

    public ArrayList<Topik> topiks = new ArrayList<>();
//    DBHelper db;
    BhaskaraDB dbHelper;
    public ArrayList<TopikAksara> topikAksaras = new ArrayList<>();
    ListView listView;

    public static final String EXTRA_TITLE = "extraTitle";
    public static final String EXTRA_CONTENT = "extraContent";

    public AksaraFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_aksara, container, false);

        listView = (ListView) rootView.findViewById(R.id.aksaraListView);
        final TopikAksaraAdapter adapter = new TopikAksaraAdapter(this.getActivity(), getTopicAksara());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DetailCollapseActivity.class);
                intent.putExtra(EXTRA_TITLE, topikAksaras.get(position).getJudul());
                intent.putExtra(EXTRA_CONTENT, topikAksaras.get(position).getIsi());
                startActivity(intent);
            }
        });

        return rootView;
    }

    public ArrayList<TopikAksara> getTopicAksara() {
//        String tableName = getTableName(dicType);
        dbHelper = new BhaskaraDB(getContext());
        dbHelper.getWritableDatabase();
        ArrayList<TopikAksara> topikAksaraList = dbHelper.getAllTopicAksara();
        String judul, isi;
        for (int i = 0; i<topikAksaraList.size();i++) {
            judul = topikAksaraList.get(i).getJudul();
            isi = topikAksaraList.get(i).getIsi();


            TopikAksara topikAksara = new TopikAksara(judul, isi);
//            topik.setName(name);

            topikAksaras.add(topikAksara);
        }
        dbHelper.close();

//        ArrayList<String> source = new ArrayList<>();
//        while (result.moveToNext()) {
//            source.add(result.getString(result.getColumnIndex(COL_JUDUL)));
//        }
//        return source;
        return topikAksaras;
    }


    private ArrayList<Topik> getTopikAksara() {
        //COLECTION OF CRIME MOVIES
        ArrayList<Topik> topikAksara=new ArrayList<>();

        //SINGLE MOVIE
        Topik topik=new Topik("ᮕᮨᮛ᮪ᮊᮨᮔᮜᮔ᮪", "artina");

        //ADD ITR TO COLLECTION
        topikAksara.add(topik);

        topik = new Topik("ᮛᮥᮙᮠ᮪", "nyaeta");
        topikAksara.add(topik);

        topik=new Topik("ᮞᮨᮊᮧᮜᮠ᮪", "naon");
        topikAksara.add(topik);

        return topikAksara;
    }

    @Override
    public String toString() {
        String title="Aksara Sunda";
        return title;
    }
}