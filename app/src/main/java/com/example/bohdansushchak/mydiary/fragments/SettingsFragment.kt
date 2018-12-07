package com.example.bohdansushchak.mydiary.fragments

import android.os.Bundle
import android.support.v7.preference.PreferenceFragmentCompat
import com.example.bohdansushchak.mydiary.R


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.pref_settings, rootKey)
    }
}