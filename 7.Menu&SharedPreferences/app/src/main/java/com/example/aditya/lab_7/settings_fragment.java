package com.example.aditya.lab_7;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;

public class settings_fragment extends PreferenceFragment {
    public settings_fragment(){

    }
    public static settings_fragment newInstance() {
        settings_fragment fragment = new settings_fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);
        EditTextPreference pref= (EditTextPreference) findPreference("User");
        pref.setSummary(pref.getText());
    }
}
