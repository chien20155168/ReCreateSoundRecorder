package com.nvchung.recreatesoundrecorder.View;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.nvchung.recreatesoundrecorder.R;
import com.nvchung.recreatesoundrecorder.SharePreference.PreferHelper;

import static com.nvchung.recreatesoundrecorder.SharePreference.PreferHelper.setQuality;

public class FragmentSetting extends PreferenceFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);

        //
        Preference aboutPre = findPreference("about");
        aboutPre.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Toast.makeText(getActivity(), "Aout", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        //

        CheckBoxPreference highQualityPre = (CheckBoxPreference) findPreference("high_quality");
        highQualityPre.setChecked(PreferHelper.getQuality(getActivity()));
        highQualityPre.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                PreferHelper.setQuality(getActivity(),(Boolean)newValue);
                return true;
            }
        });

    }
}
