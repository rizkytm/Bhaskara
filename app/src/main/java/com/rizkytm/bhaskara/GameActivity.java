package com.rizkytm.bhaskara;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

import com.rizkytm.bhaskara.Main2Activity;

public class GameActivity extends AppCompatActivity {

    public static final String EXTRA_SCORE_GAME = "extraScore";
    private static final String KEY_SCORE_GAME = "keyScore";
    public static final String EXTRA_CATEGORY_ID = "extraCategoryID";

    private long backPressedTime;

    private BhaskaraDB bhaskaraDB;

    ArrayList<Image> images = new ArrayList<>();

    TextView tv_p1;
    TextView tv_cd;

    ImageView iv_11, iv_12, iv_13, iv_14,
            iv_21, iv_22, iv_23, iv_24,
            iv_31, iv_32, iv_33, iv_34;

    Integer[] cardsArray = {101, 102, 103, 104, 105, 106,
            201, 202, 203, 204, 205, 206 };

    int images101, images102, images103, images104, images105, images106,
            images201, images202, images203, images204, images205, images206;

    int firstCard, secondCard;
    int clickedFirst, clickedSecond;
    int cardNumber = 1;

    int turn = 1;
    int playerPoints = 0, cpuPoints = 0;

    Chronometer simpleChronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        Drawable dbDrawable = getResources().getDrawable(R.drawable.ga);


//        int photoID = R.drawable.ka;
//        String nama = getResources().getResourceEntryName(R.drawable.ka);


//        Image image = new Image();
//        image.name = "ini huruf";
//        image.imageA = photoID;
//        image.imageB = photoID;
//        bhaskaraDB.tambah(image);

        tv_p1 = (TextView) findViewById(R.id.tv_p1);
        tv_p1.setTextColor(Color.WHITE);
//        tv_cd = (TextView) findViewById(R.id.tv_cd);

        iv_11 = (ImageView) findViewById(R.id.iv_11);
        iv_12 = (ImageView) findViewById(R.id.iv_12);
        iv_13 = (ImageView) findViewById(R.id.iv_13);
        iv_14 = (ImageView) findViewById(R.id.iv_14);
        iv_21 = (ImageView) findViewById(R.id.iv_21);
        iv_22 = (ImageView) findViewById(R.id.iv_22);
        iv_23 = (ImageView) findViewById(R.id.iv_23);
        iv_24 = (ImageView) findViewById(R.id.iv_24);
        iv_31 = (ImageView) findViewById(R.id.iv_31);
        iv_32 = (ImageView) findViewById(R.id.iv_32);
        iv_33 = (ImageView) findViewById(R.id.iv_33);
        iv_34 = (ImageView) findViewById(R.id.iv_34);

        iv_11.setTag("0");
        iv_12.setTag("1");
        iv_13.setTag("2");
        iv_14.setTag("3");
        iv_21.setTag("4");
        iv_22.setTag("5");
        iv_23.setTag("6");
        iv_24.setTag("7");
        iv_31.setTag("8");
        iv_32.setTag("9");
        iv_33.setTag("10");
        iv_34.setTag("11");

        frontOfCardsResources();

        Collections.shuffle(Arrays.asList(cardsArray));

//        tv_p2.setTextColor(Color.GRAY);

        iv_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_11, theCard);
            }
        });

        iv_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_12, theCard);
            }
        });

        iv_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_13, theCard);
            }
        });

        iv_14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_14, theCard);
            }
        });

        iv_21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_21, theCard);
            }
        });

        iv_22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_22, theCard);
            }
        });

        iv_23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_23, theCard);
            }
        });

        iv_24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_24, theCard);
            }
        });

        iv_31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_31, theCard);
            }
        });

        iv_32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_32, theCard);
            }
        });

        iv_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_33, theCard);
            }
        });

        iv_34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_34, theCard);
            }
        });

//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(GameActivity.this);
//        alertDialogBuilder
//                .setMessage("Selamat Datang di Game Bhaskara\n" +
//                        "Cocokkan Aksara Sunda dengan huruf latinnya"
//                )
//                .setCancelable(false)
//                .setPositiveButton("MULAI", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast toast = Toast.makeText(getApplicationContext(),
//                                "Game dimulai",
//                                Toast.LENGTH_SHORT);
//                        toast.show();
//                    }
//                })
//                .setNegativeButton("KELUAR", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
//                    }
//                });
//        AlertDialog alertDialog = alertDialogBuilder.create();
//        alertDialog.show();


        simpleChronometer = (Chronometer) findViewById(R.id.simpleChronometer);
        simpleChronometer.setBase(SystemClock.elapsedRealtime());
        simpleChronometer.start();

    }

    private void doStuff(ImageView iv, int card) {
        if (cardsArray[card] == 101) {
            iv.setImageResource(images101);
        } else if (cardsArray[card] == 102) {
            iv.setImageResource(images102);
        } else if (cardsArray[card] == 103) {
            iv.setImageResource(images103);
        } else if (cardsArray[card] == 104) {
            iv.setImageResource(images104);
        } else if (cardsArray[card] == 105) {
            iv.setImageResource(images105);
        } else if (cardsArray[card] == 106) {
            iv.setImageResource(images106);
        } else if (cardsArray[card] == 201) {
            iv.setImageResource(images201);
        } else if (cardsArray[card] == 202) {
            iv.setImageResource(images202);
        } else if (cardsArray[card] == 203) {
            iv.setImageResource(images203);
        } else if (cardsArray[card] == 204) {
            iv.setImageResource(images204);
        } else if (cardsArray[card] == 205) {
            iv.setImageResource(images205);
        } else if (cardsArray[card] == 206) {
            iv.setImageResource(images206);
        }

        if (cardNumber == 1) {
            firstCard = cardsArray[card];
            if (firstCard > 200) {
                firstCard = firstCard - 100;
            }
            cardNumber = 2;
            clickedFirst = card;

            iv.setEnabled(false);
        } else if (cardNumber == 2) {
            secondCard = cardsArray[card];
            if (secondCard > 200) {
                secondCard = secondCard - 100;
            }
            cardNumber = 1;
            clickedSecond = card;

            iv_11.setEnabled(false);
            iv_12.setEnabled(false);
            iv_13.setEnabled(false);
            iv_14.setEnabled(false);
            iv_21.setEnabled(false);
            iv_22.setEnabled(false);
            iv_23.setEnabled(false);
            iv_24.setEnabled(false);
            iv_31.setEnabled(false);
            iv_32.setEnabled(false);
            iv_33.setEnabled(false);
            iv_34.setEnabled(false);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    calculate();
                }
            }, 1000);
        }
    }

    private void calculate() {
        if (firstCard == secondCard) {
            if (clickedFirst == 0) {
                iv_11.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 1) {
                iv_12.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 2) {
                iv_13.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 3) {
                iv_14.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 4) {
                iv_21.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 5) {
                iv_22.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 6) {
                iv_23.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 7) {
                iv_24.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 8) {
                iv_31.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 9) {
                iv_32.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 10) {
                iv_33.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 11) {
                iv_34.setVisibility(View.INVISIBLE);
            }

            if (clickedSecond == 0) {
                iv_11.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 1) {
                iv_12.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 2) {
                iv_13.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 3) {
                iv_14.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 4) {
                iv_21.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 5) {
                iv_22.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 6) {
                iv_23.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 7) {
                iv_24.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 8) {
                iv_31.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 9) {
                iv_32.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 10) {
                iv_33.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 11) {
                iv_34.setVisibility(View.INVISIBLE);
            }

            if (turn == 1) {
                playerPoints += 3;
                tv_p1.setText("Skor: " + playerPoints);
                tv_p1.setTextColor(Color.WHITE);
            } else if (turn == 2) {
//                cpuPoints++;
//                tv_p2.setText("P2: " + cpuPoints);
            }
        } else {
            playerPoints--;
            tv_p1.setText("Skor: " + playerPoints);
            tv_p1.setTextColor(Color.WHITE);

            iv_11.setImageResource(R.drawable.q_mark);
            iv_12.setImageResource(R.drawable.q_mark);
            iv_13.setImageResource(R.drawable.q_mark);
            iv_14.setImageResource(R.drawable.q_mark);
            iv_21.setImageResource(R.drawable.q_mark);
            iv_22.setImageResource(R.drawable.q_mark);
            iv_23.setImageResource(R.drawable.q_mark);
            iv_24.setImageResource(R.drawable.q_mark);
            iv_31.setImageResource(R.drawable.q_mark);
            iv_32.setImageResource(R.drawable.q_mark);
            iv_33.setImageResource(R.drawable.q_mark);
            iv_34.setImageResource(R.drawable.q_mark);

//            if (turn == 1) {
//                turn = 2;
//                tv_p1.setTextColor(Color.GRAY);
//                tv_p2.setTextColor(Color.BLACK);
//            } else if (turn == 2) {
//                turn = 1;
//                tv_p2.setTextColor(Color.GRAY);
//                tv_p1.setTextColor(Color.BLACK);
//            }
        }

        iv_11.setEnabled(true);
        iv_12.setEnabled(true);
        iv_13.setEnabled(true);
        iv_14.setEnabled(true);
        iv_21.setEnabled(true);
        iv_22.setEnabled(true);
        iv_23.setEnabled(true);
        iv_24.setEnabled(true);
        iv_31.setEnabled(true);
        iv_32.setEnabled(true);
        iv_33.setEnabled(true);
        iv_34.setEnabled(true);

        checkEnd();
    }

    private void checkEnd() {
        if (iv_11.getVisibility() == View.INVISIBLE &&
                iv_12.getVisibility() == View.INVISIBLE &&
                iv_13.getVisibility() == View.INVISIBLE &&
                iv_14.getVisibility() == View.INVISIBLE &&
                iv_21.getVisibility() == View.INVISIBLE &&
                iv_22.getVisibility() == View.INVISIBLE &&
                iv_23.getVisibility() == View.INVISIBLE &&
                iv_24.getVisibility() == View.INVISIBLE &&
                iv_31.getVisibility() == View.INVISIBLE &&
                iv_32.getVisibility() == View.INVISIBLE &&
                iv_33.getVisibility() == View.INVISIBLE &&
                iv_34.getVisibility() == View.INVISIBLE) {

            simpleChronometer.stop();
            long elapsedMillis = SystemClock.elapsedRealtime() - simpleChronometer.getBase();
            int minutes = (int) (elapsedMillis / 1000) / 60;
            int seconds = (int) (elapsedMillis / 1000) % 60;

            String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

            updateScore();

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(GameActivity.this);
            alertDialogBuilder
                    .setMessage("PERMAINAN SELESAI!!!\nSkor: "+ playerPoints
//                            + "\nP2: " + cpuPoints
//                            + "\nWaktu yang ditempuh: " + timeFormatted
                    )
                    .setCancelable(false)
//                    .setPositiveButton("NEW", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            Intent intent = new Intent(getApplicationContext(), GameActivity.class);
//                            Intent intentSebelumnya = getIntent();
//
//                            int categoryID = intentSebelumnya.getIntExtra(MenuGameActivity.EXTRA_CATEGORY_ID, 0);
//                            intent.putExtra(EXTRA_CATEGORY_ID, categoryID);
//                            startActivity(intent);
//                            finish();
//                        }
//                    })
                    .setNegativeButton("SELESAI", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finishQuiz();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    public void updateScore() {
        BhaskaraDB dbHelper = BhaskaraDB.getInstance(this);
        Intent intentSebelumnya = getIntent();
        int categoryID = intentSebelumnya.getIntExtra(LatihanActivity.EXTRA_CATEGORY_ID, 0);
        int skor = intentSebelumnya.getIntExtra(LatihanActivity.EXTRA_SCORE, 0);
        if (skor < playerPoints) {
            dbHelper.updateSkorGame(playerPoints, categoryID);
        }
    }

    private void frontOfCardsResources() {

//        int[] imagesA = {R.drawable.ka, R.drawable.ga, R.drawable.nga, R.drawable.ca, R.drawable.ja, R.drawable.nya,
//                R.drawable.fb, R.drawable.ig, R.drawable.ln, R.drawable.tw, R.drawable.wa, R.drawable.yt};
//        int[] imagesB = {R.drawable.huruf_ka, R.drawable.huruf_ga, R.drawable.huruf_nga, R.drawable.huruf_ca, R.drawable.huruf_ja, R.drawable.huruf_nya,
//                R.drawable.facebook, R.drawable.instagram, R.drawable.line, R.drawable.twitter, R.drawable.whatsapp, R.drawable.youtube};


        ArrayList<Integer> numbers = new ArrayList<Integer>();
//        ArrayList<String> imagesA = new ArrayList<String>();
//        ArrayList<String> imagesB = new ArrayList<String>();

        images = loadAllImages();

        int count = images.size();

        for (int i=0;i<count;i++){
            numbers.add(i);
//            imagesA.add(images.get(i).getImageA());
//            imagesB.add(images.get(i).getImageB());
        }
        Collections.shuffle(numbers);

        int pertama = numbers.get(0);
        int kedua = numbers.get(1);
        int ketiga = numbers.get(2);
        int keempat = numbers.get(3);
        int kelima = numbers.get(4);
        int keenam = numbers.get(5);

//        String string = images.get(0).getImageA();
//        int nomer = Integer.parseInt(string);



        images101 = images.get(pertama).getImageA();
        images201 = images.get(pertama).getImageB();

        images102 = images.get(kedua).getImageA();
        images202 = images.get(kedua).getImageB();

        images103 = images.get(ketiga).getImageA();
        images203 = images.get(ketiga).getImageB();

        images104 = images.get(keempat).getImageA();
        images204 = images.get(keempat).getImageB();

        images105 = images.get(kelima).getImageA();
        images205 = images.get(kelima).getImageB();

        images106 = images.get(keenam).getImageA();
        images206 = images.get(keenam).getImageB();


    }

    public ArrayList<Image> loadAllImages() {
        bhaskaraDB = new BhaskaraDB(this);
        Intent intent = getIntent();
        int categoryID = intent.getIntExtra(MenuGameActivity.EXTRA_CATEGORY_ID, 0);
        ArrayList<Image> gambar = bhaskaraDB.getAllSoalGame(categoryID);
        return gambar;
    }

    private void finishQuiz() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE_GAME, playerPoints);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishQuiz();
        } else {
            Toast.makeText(this, "Tekan sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCORE_GAME, playerPoints);
    }
}
