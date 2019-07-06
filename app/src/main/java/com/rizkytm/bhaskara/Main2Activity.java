package com.rizkytm.bhaskara;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    public static final String EXTRA_KAMUS = "indo_sunda";
    private String jenisKamus;

    GridLayout mainGrid;

    TextView textView;
    Typeface typeface;


    private BhaskaraDB bhaskaraDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mainGrid = (GridLayout) findViewById(R.id.mainGrid);

        textView = (TextView) findViewById(R.id.textGrid);
        typeface = Typeface.createFromAsset(getAssets(), "ciung_wanara.ttf");
        textView.setTypeface(typeface);

        setSingleEvent(mainGrid);
    }

//    private void setToggleEvent(GridLayout mainGrid) {
//        //Loop all child item of Main Grid
//        for (int i = 0; i < mainGrid.getChildCount(); i++) {
//            //You can see , all child item is CardView , so we just cast object to CardView
//            final CardView cardView = (CardView) mainGrid.getChildAt(i);
//            cardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
//                        //Change background color
//                        cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"));
//                        Toast.makeText(MainActivity.this, "State : True", Toast.LENGTH_SHORT).show();
//
//                    } else {
//                        //Change background color
//                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
//                        Toast.makeText(MainActivity.this, "State : False", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        }
//    }

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (finalI == 0) {
                        Intent intent = new Intent(Main2Activity.this,TheoryActivity.class);
//                    intent.putExtra("info","This is activity from card item index  "+finalI);
                        startActivity(intent);
                    } else if (finalI == 2) {
                        Intent intent = new Intent(Main2Activity.this,ListTopikQuizActivity.class);
//                    intent.putExtra("info","This is activity from card item index  "+finalI);
                        startActivity(intent);
                    } else if (finalI == 3) {
                        Intent intent = new Intent(Main2Activity.this,ListTopikEssayActivity.class);
//                    intent.putExtra("info","This is activity from card item index  "+finalI);
                        startActivity(intent);
                    } else if (finalI == 1) {
                        Intent intent = new Intent(Main2Activity.this,ListTopikGameActivity.class);
//                    intent.putExtra("info","This is activity from card item index  "+finalI);
                        startActivity(intent);
                    } else if (finalI == 4) {
                        jenisKamus = "indo_sunda";
                        Intent intent = new Intent(Main2Activity.this,KamusActivity.class);
                        intent.putExtra(EXTRA_KAMUS, jenisKamus);
                        startActivity(intent);
                    } else if (finalI == 5) {
                        jenisKamus = "sunda_indo";
                        Intent intent = new Intent(Main2Activity.this,KataActivity.class);
                        intent.putExtra(EXTRA_KAMUS, jenisKamus);
                        startActivity(intent);
                    }
                }
            });
        }
    }


}
