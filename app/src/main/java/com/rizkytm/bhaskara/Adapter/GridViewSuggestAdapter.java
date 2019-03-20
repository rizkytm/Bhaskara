package com.rizkytm.bhaskara.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.rizkytm.bhaskara.Common.Common;
import com.rizkytm.bhaskara.MainActivity;
import com.rizkytm.bhaskara.TebakActivity;

import java.util.List;

public class GridViewSuggestAdapter extends BaseAdapter {

    private List<String> suggestSource;
    public Context context;
    private TebakActivity tebakActivity;
    public int posisi_terakhir = 0;

    public GridViewSuggestAdapter() {

    }

    public GridViewSuggestAdapter(List<String> suggestSource, Context context, TebakActivity tebakActivity) {
        this.suggestSource = suggestSource;
        this.context = context;
        this.tebakActivity = tebakActivity;
    }

    @Override
    public int getCount() {
        return suggestSource.size();
    }

    @Override
    public Object getItem(int position) {
        return suggestSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Button button;
        if(convertView == null)
        {
            if(suggestSource.get(position).equals("null"))
            {
                button = new Button(context);
                button.setLayoutParams(new GridView.LayoutParams(100,100));
                button.setPadding(8,8,8,8);
                button.setBackgroundColor(Color.DKGRAY);
            }
            else
            {
                button = new Button(context);
                button.setLayoutParams(new GridView.LayoutParams(100,100));
                button.setPadding(8,8,8,8);
                button.setBackgroundColor(Color.DKGRAY);
                button.setTextColor(Color.YELLOW);
                button.setText(suggestSource.get(position));

//                button.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        //If correct answer contains character user selected
//                        if(String.valueOf(mainActivity.answer).contains(suggestSource.get(position)))
//                        {
//                            char compare = suggestSource.get(position).charAt(0); // Get char
//
//                            for(int i =0;i<mainActivity.answer.length;i++)
//                            {
//                                if(compare == mainActivity.answer[i])
//                                    Common.user_submit_answer[i] = compare;
//                            }
//
//                            //Update UI
//                            GridViewAnswerAdapter answerAdapter = new GridViewAnswerAdapter(Common.user_submit_answer,context);
//                            mainActivity.gridViewAnswer.setAdapter(answerAdapter);
//                            answerAdapter.notifyDataSetChanged();
//
//                            //Remove from suggest source
//                            mainActivity.suggestSource.set(position,"null");
//                            mainActivity.suggestAdapter = new GridViewSuggestAdapter(mainActivity.suggestSource,context,mainActivity);
//                            mainActivity.gridViewSuggest.setAdapter(mainActivity.suggestAdapter);
//                            mainActivity.suggestAdapter.notifyDataSetChanged();
//                        }
//                        else // else
//                        {
//                            //Remove from suggest source
//                            mainActivity.suggestSource.set(position,"null");
//                            mainActivity.suggestAdapter = new GridViewSuggestAdapter(mainActivity.suggestSource,context,mainActivity);
//                            mainActivity.gridViewSuggest.setAdapter(mainActivity.suggestAdapter);
//                            mainActivity.suggestAdapter.notifyDataSetChanged();
//                        }
//                    }
//                });
                if (tebakActivity.ke != Common.user_submit_answer.length) {

                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            char jawaban = suggestSource.get(position).charAt(0);

                            Common.user_submit_answer[tebakActivity.ke] = jawaban;

                            tebakActivity.ke++;
                            if (tebakActivity.ke > 0) {
                                tebakActivity.btnDelete.setEnabled(true);
                                tebakActivity.btnSubmit.setEnabled(true);
                            } else {
                                tebakActivity.btnDelete.setEnabled(false);
                                tebakActivity.btnSubmit.setEnabled(false);
                            }

                            setPosisiTerakhir(position);
                            Log.i("info ke", String.valueOf(tebakActivity.ke));

                            GridViewAnswerAdapter answerAdapter = new GridViewAnswerAdapter(Common.user_submit_answer, context);
                            tebakActivity.gridViewAnswer.setAdapter(answerAdapter);
                            answerAdapter.notifyDataSetChanged();

                            tebakActivity.posisi.add(setPosisiTerakhir(position));

                            tebakActivity.suggestSource.set(position,"null");
                            tebakActivity.suggestAdapter = new GridViewSuggestAdapter(tebakActivity.suggestSource, context, tebakActivity);
                            tebakActivity.gridViewSuggest.setAdapter(tebakActivity.suggestAdapter);
                            tebakActivity.suggestAdapter.notifyDataSetChanged();


                        }
                    });
                }
            }
        }
        else
            button = (Button)convertView;
        return button;

    }

    public int setPosisiTerakhir(int position) {
        this.posisi_terakhir = position;
        return posisi_terakhir;
    }

    public int getPosisiTerakhir() {
        return posisi_terakhir;
    }
}
