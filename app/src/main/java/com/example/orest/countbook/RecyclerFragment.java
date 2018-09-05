package com.example.orest.countbook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
        getActivity().setTitle(R.string.app_name);
        ArrayList<Emotion> emotions = ((MainActivity)getActivity()).emotionArray;


        // UI definitions
        TextView count = (TextView) rootView.findViewById(R.id.count_angry);
        RecyclerView counterList = (RecyclerView) rootView.findViewById(R.id.counter_list);
        counterList.setHasFixedSize(false);
        Log.d("Test", emotions.size()+"");
        adapter = new EmotionAdapter(getActivity(), emotions);
        counterList.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        counterList.setLayoutManager(manager);

        // set UI values
        count.setText(emotions.size()+"");

        return rootView;
    }

}
