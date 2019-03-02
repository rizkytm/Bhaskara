package com.rizkytm.bhaskara;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class AksaraFragment extends Fragment {

    public ArrayList<Topik> topiks = new ArrayList<>();
    DBHelper db;
    public AksaraFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_aksara, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.aksaraListView);
        TopikAdapter adapter = new TopikAdapter(this.getActivity(), getTopikAksara());
        listView.setAdapter(adapter);

        return rootView;
    }

    public ArrayList<Topik> getTopikList() {
//        String tableName = getTableName(dicType);
        db = new DBHelper(getContext());
        db.getWritableDatabase();
        ArrayList<Topik> topikList = db.getTopikList();
        String judul, isi;
        for (int i = 0; i<topikList.size();i++) {
            judul = topikList.get(i).getJudul();
            isi = topikList.get(i).getIsi();

            Topik topik = new Topik(judul, isi);
//            topik.setName(name);

            topiks.add(topik);
        }
        db.close();

//        ArrayList<String> source = new ArrayList<>();
//        while (result.moveToNext()) {
//            source.add(result.getString(result.getColumnIndex(COL_JUDUL)));
//        }
//        return source;
        return topiks;
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