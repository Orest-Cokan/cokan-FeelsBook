/**
 * @authors Skryt
 *
 * RecyclerFragment is a fragment that will simply update information in the history, such as
 * the counts and place a small line separating each emotion
 *
 * returns: rootView
 */
/**
 *   Displays the number of unique feeling types stored in the application
 *
 *     Copyright (C) 2018 Orest Cokan
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.example.orest.FeelsBook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.orest.FeelsBook.R;

import java.util.ArrayList;


public class RecyclerFragment extends Fragment {
    // attributes
    private EmotionAdapter adapter;

    // make a new instance of RecyclerFragment
    public static RecyclerFragment newInstance() {
        return new RecyclerFragment();
    }

    // display the view
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

        // set UI values (counts for each emotion)
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

        // set the text for each emotion and its count
        countAngry.setText(counts[0]+"");
        countFear.setText(counts[1]+"");
        countSad.setText(counts[2]+"");
        countLove.setText(counts[3]+"");
        countSurprise.setText(counts[4]+"");
        countJoy.setText(counts[5]+"");


        return rootView;
    }

}
