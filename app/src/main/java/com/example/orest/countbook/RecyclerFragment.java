package com.example.orest.countbook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class RecyclerFragment extends Fragment {
    private EmotionAdapter adapter;
    public static RecyclerFragment newInstance() {
        return new RecyclerFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_recycler, container, false);
        getActivity().setTitle("History");
        ArrayList<Emotion> emotions = ((MainActivity)getActivity()).emotionArray;


        // UI definitions
        TextView countAngry = (TextView) rootView.findViewById(R.id.count_angry);
        TextView countLove = (TextView) rootView.findViewById(R.id.count_love);
        TextView countSurprise = (TextView) rootView.findViewById(R.id.count_surprise);
        TextView countSad = (TextView) rootView.findViewById(R.id.count_sad);
        TextView countFear = (TextView) rootView.findViewById(R.id.count_fear);
        TextView countJoy = (TextView) rootView.findViewById(R.id.count_joy);
        RecyclerView counterList = (RecyclerView) rootView.findViewById(R.id.counter_list);
        counterList.setHasFixedSize(false);
        Log.d("Test", emotions.size()+"");
        adapter = new EmotionAdapter(getActivity(), emotions);
        counterList.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        counterList.setLayoutManager(manager);
        manager = new LinearLayoutManager(getActivity());
        counterList.setLayoutManager(manager);
        DividerItemDecoration decoration = new DividerItemDecoration(
                counterList.getContext(),
                manager.getOrientation()
        );

        counterList.addItemDecoration(decoration);
        counterList.setItemAnimator(new DefaultItemAnimator());

        // set UI values
        int[] counts = new int[6];
        for(int i = 0; i < emotions.size(); i++) {
            switch (emotions.get(i).getEmotion()){
                case "angry":
                    counts[0]++;
                    break;
                case "fear":
                    counts[1]++;
                    break;
                case "sad":
                    counts[2]++;
                    break;
                case "love":
                    counts[3]++;
                    break;
                case "surprise":
                    counts[4]++;
                    break;
                case "joy":
                    counts[5]++;
            }
        }
        countAngry.setText(counts[0]+"");
        countFear.setText(counts[1]+"");
        countSad.setText(counts[2]+"");
        countLove.setText(counts[3]+"");
        countSurprise.setText(counts[4]+"");
        countJoy.setText(counts[5]+"");


        return rootView;
    }

}
