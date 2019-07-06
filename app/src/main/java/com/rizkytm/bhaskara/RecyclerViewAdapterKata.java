package com.rizkytm.bhaskara;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class RecyclerViewAdapterKata extends RecyclerView.Adapter<RecyclerViewAdapterKata.MyViewHolder> {

    private Context mContext ;
    private List<Kata> mData ;

    public RecyclerViewAdapterKata(Context mContext, List<Kata> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_kata,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.kataIndo.setText(mData.get(position).getKataIndo());
        holder.kataSunda.setText(mData.get(position).getKataSunda());
        final String arti = mData.get(position).getKataSunda();
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast toast=Toast.makeText(mContext, arti, Toast.LENGTH_SHORT);
                toast.show();

//                Intent intent = new Intent(mContext,Book_Activity.class);
//
//                // passing data to the book activity
//                intent.putExtra("Title",mData.get(position).getTitle());
//                intent.putExtra("Description",mData.get(position).getDescription());
//                intent.putExtra("Thumbnail",mData.get(position).getThumbnail());
//                // start the activity
//                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView kataIndo;
        TextView kataSunda;
        CardView cardView ;

        public MyViewHolder(View itemView) {
            super(itemView);

            kataIndo = (TextView) itemView.findViewById(R.id.kata_indo) ;
            kataSunda = (TextView) itemView.findViewById(R.id.kata_sunda);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);


        }
    }
}
