package com.nvchung.recreatesoundrecorder.View;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.nvchung.recreatesoundrecorder.R;

public class FragmentPlayBack extends DialogFragment implements View.OnClickListener {

    private String path;
    public interface PlayAudio{
        void onPlayAudio(String path);
    }

    private PlayAudio playAudio;
    public FragmentPlayBack(String path,PlayAudio playAudio) {
        this.path = path;
        this.playAudio = playAudio;
    }

    public static FragmentPlayBack newInstance(String path,PlayAudio playAudio) {
        Bundle args = new Bundle();

        FragmentPlayBack fragment = new FragmentPlayBack(path , playAudio);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog, container, false);
        Button button = v.findViewById(R.id.play);
        button.setOnClickListener(this);
        return v;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play:
                play(this.path);
                break;

        }
    }

    private void play(String path) {
        playAudio.onPlayAudio(path);
    }
}
