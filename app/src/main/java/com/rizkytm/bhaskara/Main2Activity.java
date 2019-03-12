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

    int[] imagesA = {R.drawable.ka, R.drawable.ga, R.drawable.nga, R.drawable.ca, R.drawable.ja, R.drawable.nya,
            R.drawable.fb, R.drawable.ig, R.drawable.ln, R.drawable.tw, R.drawable.wa, R.drawable.yt};
    int[] imagesB = {R.drawable.huruf_ka, R.drawable.huruf_ga, R.drawable.huruf_nga, R.drawable.huruf_ca, R.drawable.huruf_ja, R.drawable.huruf_nya,
            R.drawable.facebook, R.drawable.instagram, R.drawable.line, R.drawable.twitter, R.drawable.whatsapp, R.drawable.youtube};

    private BhaskaraDB bhaskaraDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        loadImages();

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
                        Intent intent = new Intent(Main2Activity.this,LatihanActivity.class);
//                    intent.putExtra("info","This is activity from card item index  "+finalI);
                        startActivity(intent);
                    } else if (finalI == 3) {
                        Intent intent = new Intent(Main2Activity.this,MenuEssayActivity.class);
//                    intent.putExtra("info","This is activity from card item index  "+finalI);
                        startActivity(intent);
                    } else if (finalI == 1) {
                        Intent intent = new Intent(Main2Activity.this,GameActivity.class);
//                    intent.putExtra("info","This is activity from card item index  "+finalI);
                        startActivity(intent);
                    } else if (finalI == 4) {
                        jenisKamus = "indo_sunda";
                        Intent intent = new Intent(Main2Activity.this,KamusActivity.class);
                        intent.putExtra(EXTRA_KAMUS, jenisKamus);
                        startActivity(intent);
                    } else if (finalI == 5) {
                        jenisKamus = "sunda_indo";
                        Intent intent = new Intent(Main2Activity.this,KamusActivity.class);
                        intent.putExtra(EXTRA_KAMUS, jenisKamus);
                        startActivity(intent);
                    }
                }
            });
        }
    }

    public void loadImages() {
        bhaskaraDB = new BhaskaraDB(this);
        for (int i=0; i<imagesA.length; i++) {
            String name = getResources().getResourceEntryName(imagesA[i]);
            int imageA = imagesA[i];
            int imageB = imagesB[i];
            Image image = new Image(name, imageA, imageB);
            bhaskaraDB.tambah(image);
        }
    }
}
