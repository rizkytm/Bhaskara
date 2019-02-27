package com.rizkytm.bhaskara;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailTopikActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_topik);

        Intent intent = getIntent();
        String message = intent.getStringExtra(TopikAdapter.EXTRA_MESSAGE);

        TextView textView = findViewById(R.id.detailTopik);
        textView.setText(message);
    }
}
