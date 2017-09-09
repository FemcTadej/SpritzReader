package com.hugiell.spritzreader;

import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UserSettingsActivity extends AppCompatActivity {
    public static final String PREFERENCE_WPM_KEY = "preference_wpm_key";
    public static final String PREFERENCE_TEXT_KEY = "preference_text_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.user_preferences, new SettingsFragment())
                .commit();
    }

    public static class SettingsFragment extends PreferenceFragment {
        public SettingsFragment() {
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }
    }

}
