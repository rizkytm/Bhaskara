package com.rizkytm.bhaskara;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class BahasaFragment extends Fragment {


    public BahasaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_bahasa, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.bahasaListView);
        TopikAdapter adapter = new TopikAdapter(this.getActivity(), getTopikBahasa());
        listView.setAdapter(adapter);

        return rootView;
    }


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
