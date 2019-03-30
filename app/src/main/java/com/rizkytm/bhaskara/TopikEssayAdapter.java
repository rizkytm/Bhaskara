package com.rizkytm.bhaskara;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class TopikEssayAdapter extends BaseAdapter {

    public static final String EXTRA_MESSAGE = "com.rizkytm.bhaskara.MESSAGE";

    Context c;
    List<TopikEssay> topikEssays;
    LayoutInflater inflater;

    public TopikEssayAdapter(Context c, List<TopikEssay> topikEssays) {
        this.c = c;
        this.topikEssays = topikEssays;
    }

    @Override
    public int getCount() {
        return topikEssays.size();
    }

    @Override
    public Object getItem(int position) {
        return topikEssays.get(position);
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
        final String name = topikEssays.get(position).getJudul();
        textViewName.setText(name);

        final TextView textViewSkor = (TextView) convertView.findViewById(R.id.skor);
        final int nilai = topikEssays.get(position).getSkor();
        textViewSkor.setText("Skor tertinggi: " + nilai);

        return convertView;
    }
}
