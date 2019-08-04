package com.nvchung.recreatesoundrecorder.View;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nvchung.recreatesoundrecorder.Adapter.AdapterSaveRecord;
import com.nvchung.recreatesoundrecorder.R;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListenerHistory} interface
 * to handle interaction events.
 * Use the {@link FragmentRecoderGellary#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentRecoderGellary extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListenerHistory mListener;

    public FragmentRecoderGellary() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentRecoderGellary.
     */


    // TODO: Rename and change types and number of parameters
    public static FragmentRecoderGellary newInstance(String param1, String param2) {
        FragmentRecoderGellary fragment = new FragmentRecoderGellary();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_recoder_gellary, container, false);
        initRcv(v);

        return v;

    }


    private FragmentPlayBack fragmentPlayBack;

    private void playAudio(String path) {
        if (fragmentPlayBack == null) {
            fragmentPlayBack = FragmentPlayBack.newInstance(path, new FragmentPlayBack.PlayAudio() {
                @Override
                public void onPlayAudio(String path) {
                    Log.d("TAG", "onPlayAudio: "+path);

                    play(path);
                }
            });
        }
        fragmentPlayBack.show(getActivity().getSupportFragmentManager(), "tag");

    }

    MediaPlayer mediaPlayer;

    private void play(String path) {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initRcv(View v) {
        RecyclerView rcvSave = v.findViewById(R.id.list_save_rcv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        AdapterSaveRecord adapterSaveRecord = new AdapterSaveRecord(getActivity(), new AdapterSaveRecord.ItemSaveclick() {
            @Override
            public void onItemSaveClik(String path) {
                playAudio(path);
                Log.d("TAG", "onItemSaveClik: "+path);
            }
        });
        rcvSave.setLayoutManager(linearLayoutManager);
        rcvSave.setItemAnimator(new DefaultItemAnimator());
        rcvSave.setAdapter(adapterSaveRecord);

    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteractionHistory(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListenerHistory) {
            mListener = (OnFragmentInteractionListenerHistory) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListenerHistory");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListenerHistory {
        // TODO: Update argument type and name
        void onFragmentInteractionHistory(Uri uri);
    }
}
