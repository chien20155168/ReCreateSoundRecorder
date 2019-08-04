package com.nvchung.recreatesoundrecorder.View;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.nvchung.recreatesoundrecorder.Adapter.MainAdapter;
import com.nvchung.recreatesoundrecorder.DB.DBHelper;
import com.nvchung.recreatesoundrecorder.Model.InforSaveRecord;
import com.nvchung.recreatesoundrecorder.R;
import com.nvchung.recreatesoundrecorder.View.FragmentRecoderGellary;
import com.nvchung.recreatesoundrecorder.View.FragmentRecorder;

public class MainActivity extends AppCompatActivity implements FragmentRecorder.OnFragmentInteractionListener, FragmentRecoderGellary.OnFragmentInteractionListenerHistory {
    private DBHelper mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        //setupTab andPage

        ViewPager page = findViewById(R.id.act_maint_page);
        page.setAdapter(new MainAdapter(getSupportFragmentManager()));
        TabLayout tabLayout = findViewById(R.id.act_main_tab);
        tabLayout.setupWithViewPager(page);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activtity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_setting:
                Intent intent = new Intent(this, ActiviySetting.class);
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onFragmentInteractionHistory(Uri uri) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
