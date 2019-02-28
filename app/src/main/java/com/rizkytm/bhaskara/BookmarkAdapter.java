package com.rizkytm.bhaskara;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class BookmarkAdapter extends BaseAdapter {

    private ListItemListener listener;
    private ListItemListener listenerBtnDelete;

    Context mContext;
    ArrayList<String> mSource;

    public BookmarkAdapter(Context context, ArrayList<String> source) {
        this.mSource = source;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mSource.size();
    }

    @Override
    public Object getItem(int i) {
        return mSource.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void removeItem(int position) {
        mSource.remove(position);
    }

    protected static class ViewHolderItems
    {
        private ImageView mStoreImage;
        private TextView mStoreName;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolderItems viewHolder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.bookmark_layout,viewGroup,false);
            viewHolder = new ViewHolderItems();
            viewHolder.mStoreImage = (ImageView) view.findViewById(R.id.btnDelete);
            viewHolder.mStoreName = (TextView) view.findViewById(R.id.tvWord);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderItems) view.getTag();
        }

        viewHolder.mStoreName.setText(mSource.get(i));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener!=null)
                    listener.onItemClick(i);
            }
        });

        viewHolder.mStoreImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listenerBtnDelete!=null)
                    listenerBtnDelete.onItemClick(i);
            }
        });
        return view;
    }

    public void setOnItemClick(ListItemListener listItemListener) {
        this.listener = listItemListener;
    }

    public void setOnItemDeleteClick(ListItemListener listItemListener) {
        this.listenerBtnDelete = listItemListener;
    }
}
