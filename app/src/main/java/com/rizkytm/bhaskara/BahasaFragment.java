package com.rizkytm.bhaskara;

import android.content.Context;
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

    public BahasaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_bahasa, container, false);

        listView = (ListView) rootView.findViewById(R.id.bahasaListView);
        TopikAdapter adapter = new TopikAdapter(this.getActivity(), getTopikBahasa());
        listView.setAdapter(adapter);

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

    private ArrayList<Topik> getTopikBahasa() {
        //COLECTION OF CRIME MOVIES
        ArrayList<Topik> topikBahasa=new ArrayList<>();

        //SINGLE MOVIE
        Topik topik=new Topik("Pekenalan");

        //ADD ITR TO COLLECTION
        topikBahasa.add(topik);

        topik = new Topik("Percakapan di Rumah");
        topikBahasa.add(topik);

        topik=new Topik("Percakapan di Sekolah");
        topikBahasa.add(topik);

        return topikBahasa;
    }

    @Override
    public String toString() {
        String title="Bahasa Sunda";
        return title;
    }
}
