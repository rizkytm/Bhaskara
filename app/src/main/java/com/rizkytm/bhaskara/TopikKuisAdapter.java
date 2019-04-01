package com.rizkytm.bhaskara;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TopikKuisAdapter extends BaseAdapter {

    public static final String EXTRA_MESSAGE = "com.rizkytm.bhaskara.MESSAGE";

    Context c;
    List<TopikKuis> topikKuis;
    LayoutInflater inflater;

    public TopikKuisAdapter(Context c, List<TopikKuis> topikKuis) {
        this.c = c;
        this.topikKuis = topikKuis;
    }

    @Override
    public int getCount() {
        return topikKuis.size();
    }

    @Override
    public Object getItem(int position) {
        return topikKuis.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater==null) {
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView==null) {
            convertView = inflater.inflate(R.layout.listview_item,parent, false);
        }

        final TextView textViewName = (TextView) convertView.findViewById(R.id.judul_topik_kuis);
        final String name = topikKuis.get(position).getJudul();
        int difficulty_id = topikKuis.get(position).getDifficulty_id();
        String difficulty = "";
        if (difficulty_id == 1) {
            difficulty = "mudah";
        } else if (difficulty_id == 2)
        {
            difficulty = "sedang";
        } else if (difficulty_id == 3){
            difficulty = "sulit";
        }
        textViewName.setText(name);

        final TextView textViewDifficulty = (TextView) convertView.findViewById(R.id.difficulty);
        textViewDifficulty.setText(difficulty);

        final TextView textViewSkor = (TextView) convertView.findViewById(R.id.skor);
        final int nilai = topikKuis.get(position).getSkor();
        textViewSkor.setText("Skor tertinggi: " + nilai);

        return convertView;
    }
}
