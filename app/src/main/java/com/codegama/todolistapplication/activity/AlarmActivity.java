package com.codegama.todolistapplication.activity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.codegama.todolistapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlarmActivity extends BaseActivity {

    @SuppressLint("StaticFieldLeak")
    private static AlarmActivity inst;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.title)
    TextView title;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.description)
    TextView description;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.timeAndData)
    TextView timeAndData;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.closeButton)
    Button closeButton;
    MediaPlayer mediaPlayer;

    public static AlarmActivity instance() {
        return inst;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        ButterKnife.bind(this);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.notification);
        mediaPlayer.start();

        if(getIntent().getExtras() != null) {
            title.setText(getIntent().getStringExtra("TITLE"));
            description.setText(getIntent().getStringExtra("DESC"));
            timeAndData.setText(getIntent().getStringExtra("DATE") + ", " + getIntent().getStringExtra("TIME"));
        }

        closeButton.setOnClickListener(view -> finish());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
    }
}
