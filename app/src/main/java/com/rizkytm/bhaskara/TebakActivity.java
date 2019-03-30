package com.rizkytm.bhaskara;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kofigyan.stateprogressbar.StateProgressBar;
import com.rizkytm.bhaskara.Adapter.GridViewAnswerAdapter;
import com.rizkytm.bhaskara.Adapter.GridViewSuggestAdapter;
import com.rizkytm.bhaskara.Common.Common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class TebakActivity extends AppCompatActivity {

    public static final String EXTRA_SCORE_ESSAY = "extraScore";
    private static final String KEY_SCORE_ESSAY = "keyScore";
    public static final String EXTRA_CATEGORY_ID = "extraCategoryID";
    public static final String EXTRA_SCORE = "extraScore";

    private long backPressedTime;

    public ArrayList<Integer> posisi = new ArrayList<Integer>();

    public int ke = 0;

    public List<String> suggestSource = new ArrayList<>();

    public GridViewAnswerAdapter answerAdapter;
    public GridViewSuggestAdapter suggestAdapter = new GridViewSuggestAdapter();

    public Button btnSubmit, btnDelete;

    public GridView gridViewAnswer, gridViewSuggest;

//    public ImageView imgViewQuestion;

    public TextView textViewSoalEssay;

//    public String[] string = {
//            "ᮖᮃᮎᮈᮘᮇᮇᮊ",
//            "ᮜᮄᮔᮈ",
//            "ᮒᮝᮄᮒᮒᮈᮛ",
//            "ᮝᮠᮃᮒᮞᮃᮕᮕ",
//            "ᮚᮇᮅᮒᮅᮘᮈ"
//    };
//
//    public String[] strings = {
//            "ᮕᮨᮞ᮪ᮘᮥᮊ᮪",
//            "ᮕᮨᮞ᮪ᮘᮥᮊ᮪",
//            "ᮕᮨᮞ᮪ᮘᮥᮊ᮪",
//            "ᮕᮨᮞ᮪ᮘᮥᮊ᮪",
//            "ᮕᮨᮞ᮪ᮘᮥᮊ᮪"
//    };
//
//    int[] image_list = {
//            R.drawable.facebook,
//            R.drawable.line,
//            R.drawable.twitter,
//            R.drawable.whatsapp,
//            R.drawable.youtube
//    };

    BhaskaraDB bhaskaraDB;
    List<SoalEssay> soalEssays = new ArrayList<>();

    public List<SoalEssay> getSoalEssay() {
        bhaskaraDB = new BhaskaraDB(getApplicationContext());
        bhaskaraDB.getWritableDatabase();
        Intent intent = getIntent();
        int categoryID = intent.getIntExtra(MenuEssayActivity.EXTRA_CATEGORY_ID, 0);
        List<SoalEssay> soalEssayList = bhaskaraDB.loadSoalEssay(categoryID);
        int id, topik_id;
        String pertanyaan, jawaban;
        for (int i = 0; i<soalEssayList.size();i++) {
            id = soalEssayList.get(i).getId();
            topik_id = soalEssayList.get(i).getTopik_id();
            pertanyaan = soalEssayList.get(i).getPertanyaan();
            jawaban = soalEssayList.get(i).getJawaban();

            SoalEssay soalEssay = new SoalEssay(topik_id, pertanyaan, jawaban);

            soalEssays.add(soalEssay);
        }
        bhaskaraDB.close();

        return soalEssays;
    }


//    int jumlahSoal = image_list.length;

    int jumlahSoal = 5;

    public char[] answer;

    String correct_answer;

    TextView textViewSkor;

    Chronometer chronometer;

    int points = 0;

    int turn = 0;

    ArrayList<Integer> numbers;

    private StateProgressBar stateProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tebak);

        soalEssays = getSoalEssay();

        textViewSkor = (TextView) findViewById(R.id.tv_skor);

        numbers = new ArrayList<Integer>();
        for (int i=0;i<jumlahSoal;i++){
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        stateProgressBar = (StateProgressBar) findViewById(R.id.your_state_progress_bar_id);

        initView();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TebakActivity.this);
        alertDialogBuilder
                .setMessage("Selamat Datang di Game Tebak Bhaskara\n" +
                        "Tebaklah Aksara Sunda"
                )
                .setCancelable(false)
                .setPositiveButton("MULAI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Game dimulai",
                                Toast.LENGTH_SHORT);
                        toast.show();
                        chronometer = (Chronometer) findViewById(R.id.simpleChronometer);
                        chronometer.setBase(SystemClock.elapsedRealtime());
                        chronometer.start();
                    }
                })
                .setNegativeButton("KELUAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void initView() {
        gridViewAnswer = (GridView) findViewById(R.id.gridViewAnswer);
        gridViewSuggest = (GridView) findViewById(R.id.gridViewSuggest);

//        imgViewQuestion = (ImageView) findViewById(R.id.imgLogo);
        textViewSoalEssay = (TextView) findViewById(R.id.soal_essay);

        setupList();

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setEnabled(false);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = "";
                for (int i=0;i< Common.user_submit_answer.length;i++) {
                    result+=String.valueOf(Common.user_submit_answer[i]);
                }
                if (result.equals(correct_answer)) {
                    Toast.makeText(getApplicationContext(), "Finish ! This is " + result, Toast.LENGTH_SHORT).show();

                    ke=0;
                    btnDelete.setEnabled(false);
                    points += 3;
                    textViewSkor.setText("Skor: " + points);
                    turn++;

                    Common.count = 0;
                    Common.user_submit_answer = new char[correct_answer.length()];

                    GridViewAnswerAdapter answerAdapter = new GridViewAnswerAdapter(setupNullList(), getApplicationContext());
                    gridViewAnswer.setAdapter(answerAdapter);
                    answerAdapter.notifyDataSetChanged();

                    GridViewSuggestAdapter suggestAdapter = new GridViewSuggestAdapter(suggestSource, getApplicationContext(), TebakActivity.this);
                    gridViewSuggest.setAdapter(suggestAdapter);
                    suggestAdapter.notifyDataSetChanged();

                    btnSubmit.setEnabled(false);

                    setupList();

                } else {
                    points--;
                    textViewSkor.setText("Skor: " + points);
                    Toast.makeText(TebakActivity.this, "Incorrect!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setEnabled(false);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ke--;
                if (ke > 0) {
                    btnDelete.setEnabled(true);
                } else {
                    btnDelete.setEnabled(false);
                    btnSubmit.setEnabled(false);
                }
                int terakhir = Arrays.asList(Common.user_submit_answer).indexOf(ke);
                //                char chara = Common.user_submit_answer[ke];
                char chara = Common.user_submit_answer[ke];
                String karakter = Character.toString(chara);
                Common.user_submit_answer[ke] = ' ';

                GridViewAnswerAdapter answerAdapter = new GridViewAnswerAdapter(Common.user_submit_answer, getApplicationContext());
                gridViewAnswer.setAdapter(answerAdapter);
                answerAdapter.notifyDataSetChanged();

                Log.i("info",String.valueOf(suggestAdapter.posisi_terakhir));

                suggestSource.set(posisi.get(posisi.size()-1),karakter);
                posisi.remove(posisi.size()-1);
                suggestAdapter = new GridViewSuggestAdapter(suggestSource,getApplicationContext(),TebakActivity.this);
                gridViewSuggest.setAdapter(suggestAdapter);
                suggestAdapter.notifyDataSetChanged();
            }
        });
    }

    private void setupList() {

//        Random random = new Random();

        if (turn < jumlahSoal) {

            switch (turn) {
                case 1:
                    stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                    break;
                case 2:
                    stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);
                    break;
                case 3:
                    stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.FOUR);
                    break;
                case 4:
                    stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.FIVE);
                    break;
            }

//            int imageSelected = image_list[numbers.get(turn)];
//            imgViewQuestion.setImageResource(imageSelected);

            String soalSelected = soalEssays.get(numbers.get(turn)).getPertanyaan();
            textViewSoalEssay.setText(soalSelected);

            correct_answer = soalEssays.get(numbers.get(turn)).getJawaban();
            correct_answer = correct_answer.substring(correct_answer.lastIndexOf("/")+1);

            answer = correct_answer.toCharArray();

            Common.user_submit_answer = new char[answer.length];

            suggestSource.clear();

            for (char item:answer) {
                suggestSource.add(String.valueOf(item));
            }

//        for (int i = answer.length; i<answer.length*2; i++) {
//            suggestSource.add(Common.alphabet_character[random.nextInt(Common.alphabet_character.length)]);
//        }

            Collections.shuffle(suggestSource);

            answerAdapter = new GridViewAnswerAdapter(setupNullList(), this);
            suggestAdapter = new GridViewSuggestAdapter(suggestSource, this, this);

            answerAdapter.notifyDataSetChanged();
            suggestAdapter.notifyDataSetChanged();

            gridViewSuggest.setAdapter(suggestAdapter);
            gridViewAnswer.setAdapter(answerAdapter);
        } else {
            selesai();
        }

    }

    private void selesai() {
        chronometer.stop();
        long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
        int minutes = (int) (elapsedMillis / 1000) / 60;
        int seconds = (int) (elapsedMillis / 1000) % 60;

        Intent intentSebelumnya = getIntent();
        int categoryID = intentSebelumnya.getIntExtra(MenuEssayActivity.EXTRA_CATEGORY_ID, 0);
        int skor = intentSebelumnya.getIntExtra(MenuEssayActivity.EXTRA_SCORE, 0);
        if (skor < points) {
            bhaskaraDB.updateSkor(points, categoryID);
        }

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TebakActivity.this);
        alertDialogBuilder
                .setMessage("PERMAINAN SELESAI!\nSkor: "+ points
//                            + "\nP2: " + cpuPoints
                                + "\nWaktu yang ditempuh: " + timeFormatted
                )
                .setCancelable(false)
                .setPositiveButton("NEW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intentSebelumnya = getIntent();

                        Intent intent = new Intent(getApplicationContext(), TebakActivity.class);
                        int categoryID = intentSebelumnya.getIntExtra(MenuEssayActivity.EXTRA_CATEGORY_ID, 0);
                        intent.putExtra(EXTRA_CATEGORY_ID, categoryID);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishQuiz();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private char[] setupNullList() {
        char result[] = new char[answer.length];
        for (int i=0; i<answer.length; i++) {
            result[i]=' ';
        }
        return result;
    }

    private void finishQuiz() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE_ESSAY, points);
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
        outState.putInt(KEY_SCORE_ESSAY, points);
    }
}
