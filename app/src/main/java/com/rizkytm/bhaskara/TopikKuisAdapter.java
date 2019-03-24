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
    List<Category> topikKuis;
    LayoutInflater inflater;

    public TopikKuisAdapter(Context c, List<Category> topikKuis) {
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

        final String name = topikKuis.get(position).getName();

        textViewName.setText(name);

//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(c,name,Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(c, DetailTopikActivity.class);
//                String message = textViewName.getText().toString();
//                intent.putExtra(EXTRA_MESSAGE, message);
//                c.startActivity(intent);
//            }
//        });

        return convertView;
    }
}
