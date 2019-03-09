package com.rizkytm.bhaskara;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class EssayActivity extends AppCompatActivity {
    public static final String EXTRA_SCORE = "extraScore";
    private static final long COUNTDOWN_IN_MILLIS = 30000;

    private static final String KEY_SCORE = "keyScore";
    private static final String KEY_QUESTION_COUNT = "keyQuestionCount";
    private static final String KEY_MILLIS_LEFT = "keyMillisLeft";
    private static final String KEY_ANSWERED = "keyAnswered";
    private static final String KEY_QUESTION_LIST = "keyQuestionList";

    private TextView textViewPertanyaan;
    private TextView textViewSkor;
    private TextView textViewNoPertanyaan;
    private TextView textViewKategori;
    private TextView textViewKesulitan;
    private TextView textViewWaktu;
    private EditText editTextJawaban;
    private Button pilihJawaban;

    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCd;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    private ArrayList<Pertanyaan> pertanyaanList;
    private int questionCounter;
    private int questionCountTotal;
    private Pertanyaan currentQuestion;

    private int score;
    private boolean answered;

    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_essay);

        textViewPertanyaan = findViewById(R.id.text_view_question);
        textViewSkor = findViewById(R.id.text_view_score);
        textViewNoPertanyaan = findViewById(R.id.text_view_question_count);
        textViewKategori = findViewById(R.id.text_view_category);
        textViewKesulitan = findViewById(R.id.text_view_difficulty);
        textViewWaktu = findViewById(R.id.text_view_countdown);
        editTextJawaban = findViewById(R.id.edit_text_jawaban);
        pilihJawaban = findViewById(R.id.button_confirm_next);

        textColorDefaultRb = textViewPertanyaan.getTextColors();
        textColorDefaultCd = textViewWaktu.getTextColors();

        Intent intent = getIntent();
        int categoryID = intent.getIntExtra(LatihanActivity.EXTRA_CATEGORY_ID, 0);
        String categoryName = intent.getStringExtra(LatihanActivity.EXTRA_CATEGORY_NAME);
        String difficulty = intent.getStringExtra(LatihanActivity.EXTRA_DIFFICULTY);

        textViewKategori.setText("Kategori: " + categoryName);
        textViewKesulitan.setText("Tingkat Kesulitan: " + difficulty);

        if (savedInstanceState == null) {
            BhaskaraDB dbHelper = BhaskaraDB.getInstance(this);
            pertanyaanList = dbHelper.getPertanyaans(categoryID, difficulty);
            questionCountTotal = pertanyaanList.size();
            Collections.shuffle(pertanyaanList);

            showNextQuestion();
        } else {
            pertanyaanList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
            questionCountTotal = pertanyaanList.size();
            questionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            currentQuestion = pertanyaanList.get(questionCounter - 1);
            score = savedInstanceState.getInt(KEY_SCORE);
            timeLeftInMillis = savedInstanceState.getLong(KEY_MILLIS_LEFT);
            answered = savedInstanceState.getBoolean(KEY_ANSWERED);

            if (!answered) {
                startCountDown();
            } else {
                updateCountDownText();
                showSolution();
            }
        }

        pilihJawaban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (!editTextJawaban.getText().toString().isEmpty()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(EssayActivity.this, "Isi jawaban terlebih dahulu", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });
    }

    private void showNextQuestion() {
        textViewPertanyaan.setTextColor(Color.BLACK);
        editTextJawaban.setTextColor(textColorDefaultRb);
        editTextJawaban.setText("");

        if (questionCounter < questionCountTotal) {
            currentQuestion = pertanyaanList.get(questionCounter);

            textViewPertanyaan.setText(currentQuestion.getPertanyaan());
//            pil1.setText(currentQuestion.getPil1());

            questionCounter++;
            textViewNoPertanyaan.setText("Pertanyaan ke: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            pilihJawaban.setText("Pilih Jawaban");

            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();
        } else {
            finishQuiz();
        }
    }

    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                checkAnswer();
            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        textViewWaktu.setText(timeFormatted);

        if (timeLeftInMillis < 10000) {
            textViewWaktu.setTextColor(Color.RED);
        } else {
            textViewWaktu.setTextColor(textColorDefaultCd);
        }
    }

    private void checkAnswer() {
        answered = true;

        countDownTimer.cancel();

        String jawaban = editTextJawaban.getText().toString();

//        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
//        int noJawaban = rbGroup.indexOfChild(rbSelected) + 1;

        if (jawaban.toLowerCase().equals(currentQuestion.getJawaban())) {
            score++;
            textViewSkor.setText("Skor: " + score);
        }

        showSolution();
    }

    private void showSolution() {
//        pil1.setTextColor(Color.RED);
//        pil2.setTextColor(Color.RED);
//        pil3.setTextColor(Color.RED);
//        pil4.setTextColor(Color.RED);

//        switch (currentQuestion.getNoJawaban()) {
//            case 1:
//                pil1.setTextColor(Color.GREEN);
//                textViewPertanyaan.setText("Jawaban 1 Benar");
//                break;
//            case 2:
//                pil2.setTextColor(Color.GREEN);
//                textViewPertanyaan.setText("Jawaban 2 Benar");
//                break;
//            case 3:
//                pil3.setTextColor(Color.GREEN);
//                textViewPertanyaan.setText("Jawaban 3 Benar");
//                break;
//            case 4:
//                pil4.setTextColor(Color.GREEN);
//                textViewPertanyaan.setText("Jawaban 4 Benar");
//                break;
//        }

        String jawaban = editTextJawaban.getText().toString();

        if (jawaban.toLowerCase().equals(currentQuestion.getJawaban())) {
            textViewPertanyaan.setText("Jawabannya adalah " + currentQuestion.getJawaban());
            textViewPertanyaan.setTextColor(Color.GREEN);
        } else {
            textViewPertanyaan.setText("Jawabannya adalah " + currentQuestion.getJawaban());
            textViewPertanyaan.setTextColor(Color.RED);
        }



        if (questionCounter < questionCountTotal) {
            pilihJawaban.setText("Selanjutnya");
        } else {
            pilihJawaban.setText("Selesai");
        }
    }

    private void finishQuiz() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, score);
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
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCORE, score);
        outState.putInt(KEY_QUESTION_COUNT, questionCounter);
        outState.putLong(KEY_MILLIS_LEFT, timeLeftInMillis);
        outState.putBoolean(KEY_ANSWERED, answered);
        outState.putParcelableArrayList(KEY_QUESTION_LIST, pertanyaanList);
    }
}
