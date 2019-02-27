package com.rizkytm.bhaskara;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TopikAdapter extends BaseAdapter {

    public static final String EXTRA_MESSAGE = "com.rizkytm.bhaskara.MESSAGE";

    Context c;
    ArrayList<Topik> topiks;
    LayoutInflater inflater;

    public TopikAdapter(Context c, ArrayList<Topik> topiks) {
        this.c = c;
        this.topiks = topiks;
    }

    @Override
    public int getCount() {
        return topiks.size();
    }

    @Override
    public Object getItem(int position) {
        return topiks.get(position);
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
            convertView = inflater.inflate(R.layout.model,parent, false);
        }

        final TextView textViewName = (TextView) convertView.findViewById(R.id.nameTxt);

        final String name = topiks.get(position).getName();

        textViewName.setText(name);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(c,name,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(c, DetailTopikActivity.class);
                String message = textViewName.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                c.startActivity(intent);
            }
        });

        return convertView;
    }
}
