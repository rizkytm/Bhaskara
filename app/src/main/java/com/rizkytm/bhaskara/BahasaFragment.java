package com.rizkytm.bhaskara;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class BahasaFragment extends Fragment {

    ListView listView;
//    ArrayAdapter<String> adapter;
//    private FragmentListener listener;
//    private ArrayList<String> mSource = new ArrayList<String>();

//    DBHelper db;
    BhaskaraDB dbHelper;
    public ArrayList<Topik> topiks = new ArrayList<>();

    private FragmentListener listener;
    private ArrayList<Topik> mSource = new ArrayList<Topik>();

    public static final String EXTRA_TITLE = "extraTitle";
    public static final String EXTRA_CONTENT = "extraContent";
    public static final String EXTRA_IMAGE = "extraImage";

    public BahasaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_bahasa, container, false);

        listView = (ListView) rootView.findViewById(R.id.bahasaListView);
        final TopikAdapter adapter = new TopikAdapter(this.getActivity(), getTopikList());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DetailCollapseActivity.class);
                intent.putExtra(EXTRA_TITLE, topiks.get(position).getJudul());
                intent.putExtra(EXTRA_CONTENT, topiks.get(position).getIsi());
                intent.putExtra(EXTRA_IMAGE, topiks.get(position).getImage());
                startActivity(intent);
            }
        });

        return rootView;
    }

//    public interface FragmentListener {
//        void onItemClick(String value);
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if(listener!=null) {
//                    listener.onItemClick(mSource.get(position));
//                }
//            }
//        });
//
////        bahasaList = view.findViewById(R.id.bahasaListView);
////        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1)
//    }

    public ArrayList<Topik> getTopikList() {
//        String tableName = getTableName(dicType);
        dbHelper = new BhaskaraDB(getContext());
        dbHelper.getWritableDatabase();
        ArrayList<Topik> topikList = dbHelper.getAllTopics();
        String judul, isi;
        int image;
        for (int i = 0; i<topikList.size();i++) {
            judul = topikList.get(i).getJudul();
            isi = topikList.get(i).getIsi();
            image = topikList.get(i).getImage();


            Topik topik = new Topik(judul, isi, image);
//            topik.setName(name);

            topiks.add(topik);
        }
        dbHelper.close();

//        ArrayList<String> source = new ArrayList<>();
//        while (result.moveToNext()) {
//            source.add(result.getString(result.getColumnIndex(COL_JUDUL)));
//        }
//        return source;
        return topiks;
    }

//    private ArrayList<Topik> getTopikBahasa() {
//        //COLECTION OF CRIME MOVIES
//        ArrayList<Topik> topikBahasa=new ArrayList<>();
//
//        //SINGLE MOVIE
//        Topik topik=new Topik("Pekenalan");
//
//        //ADD ITR TO COLLECTION
//        topikBahasa.add(topik);
//
//        topik = new Topik("Percakapan di Rumah");
//        topikBahasa.add(topik);
//
//        topik=new Topik("Percakapan di Sekolah");
//        topikBahasa.add(topik);
//
//        return topikBahasa;
//    }

    @Override
    public String toString() {
        String title="Bahasa Sunda";
        return title;
    }
}