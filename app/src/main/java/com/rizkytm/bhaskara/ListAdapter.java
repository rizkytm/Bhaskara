package com.rizkytm.bhaskara;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

    Context context;
    private final String[] values;
    private final String[] desc;
    private final int[] images;

    public ListAdapter(Context context, String[] values, String[] desc, int[] images) {

        this.context = context;
        this.values = values;
        this.desc = desc;
        this.images = images;
    }

    @Override
    public int getCount() {
        return values.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_list, parent, false);
            viewHolder.textViewJudul = (TextView) convertView.findViewById(R.id.textViewJudul);
            viewHolder.textViewDesc = (TextView) convertView.findViewById(R.id.textViewDesc);
            viewHolder.ic_android = (ImageView) convertView.findViewById(R.id.icon);

            result = convertView;

            convertView.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.textViewJudul.setText(values[position]);
        viewHolder.textViewDesc.setText(desc[position]);
        viewHolder.ic_android.setImageResource(images[position]);

        return convertView;
    }

    private static class ViewHolder {

        TextView textViewJudul;
        TextView textViewDesc;
        ImageView ic_android;
    }
}
