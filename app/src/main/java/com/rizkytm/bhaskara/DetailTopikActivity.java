package com.rizkytm.bhaskara;

import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

public class DetailTopikActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_topik);

        Intent intent = getIntent();
        String title = intent.getStringExtra(BahasaFragment.EXTRA_TITLE);
        String content = intent.getStringExtra(BahasaFragment.EXTRA_CONTENT);

        TextView textViewTitle = (TextView) findViewById(R.id.detailTopik);
        TextView textViewContent = (TextView) findViewById(R.id.isiTeori);
        textViewTitle.setText(title);
        textViewContent.setText(content);
    }
}