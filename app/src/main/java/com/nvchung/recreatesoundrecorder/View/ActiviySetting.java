package com.nvchung.recreatesoundrecorder.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ActionBar;
import android.os.Bundle;

import com.nvchung.recreatesoundrecorder.R;

public class ActiviySetting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activiy_setting);

        Toolbar toolbar = (Toolbar) findViewById(R.id.seting_toolbar);
        setSupportActionBar(toolbar);

        getFragmentManager().beginTransaction()
                .replace(R.id.seting_container, new FragmentSetting())
        .commit();
    }
}
