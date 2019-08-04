package com.nvchung.recreatesoundrecorder.View;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import com.nvchung.recreatesoundrecorder.DB.DBHelper;
import com.nvchung.recreatesoundrecorder.SharePreference.PreferHelper;

import java.io.File;
import java.io.IOException;

import static android.media.MediaRecorder.AudioEncoder.AAC;
import static android.media.MediaRecorder.OutputFormat.MPEG_4;

public class ServiceRecord extends Service {
    private MediaRecorder mRecord =null;
    private String mFilePath;
    private DBHelper dbHelper;


    public ServiceRecord() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startRecord();
        return START_STICKY;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        dbHelper = new DBHelper(getApplicationContext());
    }

    @Override
    public void onDestroy() {
        if (mRecord != null) {
            stopRecord();
        }
        super.onDestroy();
    }

    private void stopRecord() {
        mRecord.stop();
        mRecord.release();
        mRecord = null;


        try {
            dbHelper.addRecord("Record_"+System.currentTimeMillis(), mFilePath, 1000);

        } catch (Exception e){
            Log.e("LOG_TAG", "exception", e);
        }
    }

    private void startRecord() {
        mRecord = new MediaRecorder();
        File root = Environment.getExternalStorageDirectory().getAbsoluteFile();
        File f = new File(root + "/mrecord" + System.currentTimeMillis()+".mp4");
        mFilePath = f.getAbsolutePath();
        mRecord.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecord.setOutputFormat(MPEG_4);
        mRecord.setOutputFile(mFilePath);

        mRecord.setAudioEncoder(AAC);
        mRecord.setAudioChannels(1);

        if (PreferHelper.getQuality(getApplicationContext())) {
            mRecord.setAudioSamplingRate(44100);
            mRecord.setAudioEncodingBitRate(192000);
        }

//
        try {
            mRecord.prepare();
            mRecord.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
