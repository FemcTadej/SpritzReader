package com.hugiell.spritzreader;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class DisplayActivity extends AppCompatActivity {
    private WordView mWordView;
    private ImageButton mPlayButton;
    private ImageButton mStopButton;
    private Disposable mWordReaderDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        mPlayButton = (ImageButton)findViewById(R.id.imageButton_play);
        mStopButton = (ImageButton)findViewById(R.id.imageButton_stop);
        mPlayButton.setOnClickListener(view -> readText());
        mStopButton.setOnClickListener(view -> stopReading());
        mWordView = (WordView)findViewById(R.id.wordView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_settings:
                startActivity(new Intent(this, UserSettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void readText() {
        if((mWordReaderDisposable == null) || (mWordReaderDisposable.isDisposed())) {
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
            String readingSpeed = sharedPref.getString(UserSettingsActivity.PREFERENCE_WPM_KEY, "0");
            String textToRead = sharedPref.getString(UserSettingsActivity.PREFERENCE_TEXT_KEY, "-");
            // when user clicks "play", tell the Reader to start emitting words
            Observable<String> wordReader = Reader.getWordReader(textToRead, Integer.decode(readingSpeed));
            mWordReaderDisposable = wordReader.subscribe(word -> {
                mWordView.updateWord(word, new Word(word).getPivotLetterPosition());
            });
        }
    }

    public void stopReading() {
        if(mWordReaderDisposable != null) {
            mWordReaderDisposable.dispose();
        }
    }

}
