package com.rizkytm.bhaskara;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class LatihanActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_QUIZ_LATIHAN = 1;
    public static final String EXTRA_CATEGORY_ID = "extraCategoryID";
    public static final String EXTRA_CATEGORY_NAME = "extraCategoryName";
    public static final String EXTRA_DIFFICULTY = "extraDifficulty";
    public static final String EXTRA_SCORE = "extraScore";

    public static final String SHARED_PREFS_LATIHAN = "sharedPrefs";
    public static final String KEY_HIGHSCORE_LATIHAN = "keyHighscore";

    private TextView textViewHighscore;
    private Spinner spinnerCategory;
    private Spinner spinnerDifficulty;
    private TextView textViewBhaskara;
    private TextView textViewTopik;

    private Typeface typeface;

    private int highscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latihan);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String topikName = intent.getStringExtra(ListTopikQuizActivity.EXTRA_TITLE);

        textViewTopik = (TextView) findViewById(R.id.text_view_topik);
        textViewTopik.setText("Topik : " + topikName);

        textViewHighscore = findViewById(R.id.text_view_highscore);
        textViewHighscore.setEnabled(false);
        textViewHighscore.setVisibility(View.INVISIBLE);

        spinnerCategory = findViewById(R.id.spinner_category);
        spinnerCategory.setEnabled(false);
        spinnerCategory.setVisibility(View.INVISIBLE);
        spinnerDifficulty = findViewById(R.id.spinner_difficulty);
        spinnerDifficulty.setVisibility(View.INVISIBLE);

        textViewBhaskara = findViewById(R.id.text_bhaskara);
        typeface = Typeface.createFromAsset(getAssets(), "ciung_wanara.ttf");
        textViewBhaskara.setTypeface(typeface);

        loadCategories();
        loadDifficultyLevels();
        loadHighscoreQuiz();

        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                startQuiz();
            }
        });
    }

    private void startQuiz() {
        Intent intents = getIntent();
        String topikName = intents.getStringExtra(ListTopikQuizActivity.EXTRA_TITLE);
        int topikID = intents.getIntExtra(ListTopikQuizActivity.EXTRA_CATEGORY_ID, 0 );
        int skor = intents.getIntExtra(ListTopikQuizActivity.EXTRA_SCORE, 0 );
//        Category selectedCategory = (Category) spinnerCategory.getSelectedItem();
        int categoryID = topikID;
        String categoryName = topikName;
        int difficulty = 1;
//        String difficulty = spinnerDifficulty.getSelectedItem().toString();

        Intent intent = new Intent(LatihanActivity.this, QuizActivity.class);
        intent.putExtra(EXTRA_CATEGORY_ID, categoryID);
        intent.putExtra(EXTRA_CATEGORY_NAME, categoryName);
        intent.putExtra(EXTRA_DIFFICULTY, difficulty);
        intent.putExtra(EXTRA_SCORE, skor);
        startActivityForResult(intent, REQUEST_CODE_QUIZ_LATIHAN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUIZ_LATIHAN) {
            if (resultCode == RESULT_OK) {
                int score = data.getIntExtra(QuizActivity.EXTRA_SCORE, 0 );
                if (score > highscore) {
                    updateHighscoreQuiz(score);
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
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) view).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerCategory.setAdapter(adapterCategories);

    }

    private void loadDifficultyLevels() {
        String[] difficultyLevels = Question.getAllDifficultyLevels();

        ArrayAdapter<String> adapterDifficulty = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, difficultyLevels);
        adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficulty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) view).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerDifficulty.setAdapter(adapterDifficulty);
    }

    private void loadHighscoreQuiz() {
        SharedPreferences prefsQuiz = getSharedPreferences(SHARED_PREFS_LATIHAN, MODE_PRIVATE);
        highscore = prefsQuiz.getInt(KEY_HIGHSCORE_LATIHAN, 0);
        textViewHighscore.setText("Highscore: " + highscore);
    }

    private void updateHighscoreQuiz(int highscoreNew) {
        highscore = highscoreNew;
        textViewHighscore.setText("Highscore: " + highscore);

        SharedPreferences prefsQuiz = getSharedPreferences(SHARED_PREFS_LATIHAN, MODE_PRIVATE);
        SharedPreferences.Editor editorQuiz = prefsQuiz.edit();
        editorQuiz.putInt(KEY_HIGHSCORE_LATIHAN, highscore);
        editorQuiz.apply();
    }
}
