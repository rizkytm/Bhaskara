package com.rizkytm.bhaskara;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.TextView;

public class DetailCollapseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_collapse);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_clp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.col_toolbar);

        Intent intent = getIntent();
        String title = intent.getStringExtra(BahasaFragment.EXTRA_TITLE);
        String content = intent.getStringExtra(BahasaFragment.EXTRA_CONTENT);

//        TextView textViewTitle = (TextView) findViewById(R.id.judul_teori);
        toolbar.setTitle(title);
        collapsingToolbarLayout.setTitle(title);
        WebView webViewContent = (WebView) findViewById(R.id.wv_teori);
//        textViewTitle.setText(title);
        webViewContent.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
    }
}
