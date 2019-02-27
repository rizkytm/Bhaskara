package com.rizkytm.bhaskara;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class AksaraFragment extends Fragment {


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


    private ArrayList<Topik> getTopikAksara() {
        //COLECTION OF CRIME MOVIES
        ArrayList<Topik> topikAksara=new ArrayList<>();

        //SINGLE MOVIE
        Topik topik=new Topik("ᮕᮨᮛ᮪ᮊᮨᮔᮜᮔ᮪");

        //ADD ITR TO COLLECTION
        topikAksara.add(topik);

        topik = new Topik("ᮛᮥᮙᮠ᮪");
        topikAksara.add(topik);

        topik=new Topik("ᮞᮨᮊᮧᮜᮠ᮪");
        topikAksara.add(topik);

        return topikAksara;
    }

    @Override
    public String toString() {
        String title="Aksara Sunda";
        return title;
    }
}
