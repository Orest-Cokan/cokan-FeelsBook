/**
 * @authors Skryt
 *
 * StatsFragment is a fragment that will display a barchart with information, it requires the
 * use of the emotionArray to know the count of each emotion.
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

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ValueFormatter;

import java.util.ArrayList;

public class StatsFragment extends Fragment {
    // attributes
    private EmotionAdapter adapter;

    public static StatsFragment newInstance() {
        return new StatsFragment();
    }

    // display the view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_stats, container, false);
        getActivity().setTitle("Statistics");
        ArrayList<Emotion> emotions = ((MainActivity) getActivity()).emotionArray;


        // instantiate the barchart
        BarChart barChart = (BarChart) rootView.findViewById(R.id.barchart);
        ArrayList<BarEntry> entries = new ArrayList<>();
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

        // add values to the barchart
        entries.add(new BarEntry(counts[5], 0)); //joy
        entries.add(new BarEntry(counts[0], 1)); //angry
        entries.add(new BarEntry(counts[1], 2)); //fear
        entries.add(new BarEntry(counts[2], 3)); //sad
        entries.add(new BarEntry(counts[3], 4)); //love
        entries.add(new BarEntry(counts[4], 5)); //surprise

        // bundle the data for the barchart
        BarDataSet barDataSet = new BarDataSet(entries, "Cells");
        barDataSet.setValueTextSize(15);
        barDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf((int) Math.floor(value));
            }
        });

        // add labels to the bar chart
        ArrayList<String> labels = new ArrayList<>();
        labels.add("joy");
        labels.add("angry");
        labels.add("fear");
        labels.add("sad");
        labels.add("love");
        labels.add("surprise");

        // make the UI look cleaner
        barChart.setDescription("");
        barChart.getAxisRight().setDrawLabels(false);
        barChart.getAxisLeft().setDrawLabels(false);
        barChart.getAxisLeft().setTextSize(20);
        barChart.getLegend().setEnabled(false);
        barChart.setDrawBorders(false);

        // display the data to the barchart
        BarData data = new BarData(labels, barDataSet);
        barChart.setData(data);

        return rootView;
    }

}
