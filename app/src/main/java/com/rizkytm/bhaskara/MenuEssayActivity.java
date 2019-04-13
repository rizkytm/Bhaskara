package com.rizkytm.bhaskara;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class MenuEssayActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_QUIZ_ESSAY = 2;
    public static final String EXTRA_CATEGORY_ID = "extraCategoryID";
    public static final String EXTRA_CATEGORY_NAME = "extraCategoryName";
    public static final String EXTRA_DIFFICULTY = "extraDifficulty";
    public static final String EXTRA_SCORE = "extraScore";

    public static final String SHARED_PREFS_ESSAY = "sharedPrefsEssay";
    public static final String KEY_HIGHSCORE_ESSAY = "keyHighscoreEssay";

    private TextView textViewHighscore;
    private Spinner spinnerCategory;
    private Spinner spinnerDifficulty;

    private TextView textViewTopik;

    private int highscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latihan_essay_constraint);

        Intent intent = getIntent();
        String topikName = intent.getStringExtra(ListTopikEssayActivity.EXTRA_TITLE);
        textViewTopik = (TextView) findViewById(R.id.text_view_topik);
        textViewTopik.setText("Topik : " + topikName);

        textViewHighscore = findViewById(R.id.text_view_highscore_essay);
        textViewHighscore.setEnabled(false);
        textViewHighscore.setVisibility(View.INVISIBLE);
        spinnerCategory = findViewById(R.id.spinner_category);
        spinnerCategory.setEnabled(false);
        spinnerCategory.setVisibility(View.INVISIBLE);
        spinnerDifficulty = findViewById(R.id.spinner_difficulty);
        spinnerDifficulty.setEnabled(false);
        spinnerDifficulty.setVisibility(View.INVISIBLE);

        loadCategories();
        loadDifficultyLevels();
        loadHighscoreEssay();

        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                startQuiz();
            }
        });
    }

    private void startQuiz() {
//        Intent intents = getIntent();
//        String topikName = intents.getStringExtra(ListTopikEssayActivity.EXTRA_TITLE);
//        int topikID = intents.getIntExtra(ListTopikEssayActivity.EXTRA_CATEGORY_ID, 0 );
//
//        int categoryID = topikID;
//        String categoryName = topikName;
//
//        Category selectedCategory = (Category) spinnerCategory.getSelectedItem();
////        int categoryID = selectedCategory.getId();
////        String categoryName = selectedCategory.getName();
//        String difficulty = spinnerDifficulty.getSelectedItem().toString();
//
//        Intent intent = new Intent(MenuEssayActivity.this, TebakActivity.class);
//        intent.putExtra(EXTRA_CATEGORY_ID, categoryID);
//        intent.putExtra(EXTRA_CATEGORY_NAME, categoryName);
//        intent.putExtra(EXTRA_DIFFICULTY, difficulty);
//        startActivityForResult(intent, REQUEST_CODE_QUIZ_ESSAY);

        Intent intents = getIntent();
        String topikName = intents.getStringExtra(ListTopikEssayActivity.EXTRA_TITLE);
        int topikID = intents.getIntExtra(ListTopikEssayActivity.EXTRA_CATEGORY_ID, 0 );
        int skor = intents.getIntExtra(ListTopikEssayActivity.EXTRA_SCORE, 0 );
        Log.i("info topik id", Integer.toString(topikID));
//        Category selectedCategory = (Category) spinnerCategory.getSelectedItem();
        int categoryID = topikID;
        String categoryName = topikName;
        String difficulty = spinnerDifficulty.getSelectedItem().toString();

        Intent intent = new Intent(MenuEssayActivity.this, TebakActivity.class);
        intent.putExtra(EXTRA_CATEGORY_ID, categoryID);
        intent.putExtra(EXTRA_CATEGORY_NAME, categoryName);
        intent.putExtra(EXTRA_DIFFICULTY, difficulty);
        intent.putExtra(EXTRA_SCORE, skor);
        startActivityForResult(intent, REQUEST_CODE_QUIZ_ESSAY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUIZ_ESSAY) {
            if (resultCode == RESULT_OK) {
                int score = data.getIntExtra(TebakActivity.EXTRA_SCORE_ESSAY, 0 );
                if (score > highscore) {
                    updateHighscoreEssay(score);
                }
            }
        }
    }

    private void loadCategories() {
//        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
//        List<Category> categories = dbHelper.getAllCategories();
//
//        ArrayAdapter<Category> adapterCategories = new ArrayAdapter<>(this,
//                android.R.layout.simple_spinner_item, categories);
//        adapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerCategory.setAdapter(adapterCategories);

        BhaskaraDB dbHelper = BhaskaraDB.getInstance(this);
        List<Category> categories = dbHelper.getAllCategories();

        ArrayAdapter<Category> adapterCategories = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categories);
        adapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapterCategories);

    }

    private void loadDifficultyLevels() {
        String[] difficultyLevels = Question.getAllDifficultyLevels();

        ArrayAdapter<String> adapterDifficulty = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, difficultyLevels);
        adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficulty.setAdapter(adapterDifficulty);
    }

    private void loadHighscoreEssay() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS_ESSAY, MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE_ESSAY, 0);
        textViewHighscore.setText("Highscore: " + highscore);
    }

    private void updateHighscoreEssay(int highscoreNew) {
        highscore = highscoreNew;
        textViewHighscore.setText("Highscore: " + highscore);

        SharedPreferences prefsEssay = getSharedPreferences(SHARED_PREFS_ESSAY, MODE_PRIVATE);
        SharedPreferences.Editor editorEssay = prefsEssay.edit();
        editorEssay.putInt(KEY_HIGHSCORE_ESSAY, highscore);
        editorEssay.apply();
    }
}
