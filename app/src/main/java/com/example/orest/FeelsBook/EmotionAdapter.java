/**
 * @authors Skryt
 *
 * EmotionAdapter is an adapter class that simply takes data and places it into specified locations
 * in the view. It also has a method, getItemCount, which will simply return the size of the
 * emotionArray
 *
 * returns: nothing
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

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.orest.FeelsBook.R;

import java.util.ArrayList;

public class EmotionAdapter extends RecyclerView.Adapter<EmotionAdapter.ViewHolder>{
    private ArrayList<Emotion> emotionArrayList;
    private FragmentActivity activity;

    // constructor
    public EmotionAdapter(FragmentActivity activity, ArrayList<Emotion> emotionArrayList) {
        this.activity = activity;
        this.emotionArrayList = emotionArrayList;
    }


    // display the view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View counterView = inflater.inflate(R.layout.entry_pepe, parent, false);
        return new ViewHolder(counterView, this);
    }


    // set the data into each viewHolder (ie. place what each emotion has into the view)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String emotion = emotionArrayList.get(position).getEmotion();
        holder.emotion.setImageDrawable(activity.getDrawable(activity.getResources().getIdentifier
                (emotion.toLowerCase(), "drawable", activity.getPackageName())));
        holder.comment.setText(emotionArrayList.get(position).getComments());
        holder.date.setText(emotionArrayList.get(position).getDate());
    }

    // return the size of the list
    @Override
    public int getItemCount() {
        Log.d("Test", emotionArrayList.size()+"random");
        return emotionArrayList.size();
    }


    // place each emotion into its corresponding view
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private EmotionAdapter adapter;
        public ImageView emotion;
        public TextView comment;
        public TextView date;

        public ViewHolder(View itemView, final EmotionAdapter adapter){
            super(itemView);
            comment = itemView.findViewById(R.id.comment);
            emotion = itemView.findViewById(R.id.emotion);
            date = itemView.findViewById(R.id.date);
            itemView.setOnClickListener(this);
            this.adapter = adapter;

        }

        // set onClick listener for each emotion, so they can be edited
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            FragmentManager manager = adapter.activity.getSupportFragmentManager();
            FragmentTransaction transaction =  manager.beginTransaction();
            EditFragment fragment = EditFragment.newInstance(adapter.emotionArrayList.get(position), position);
            transaction.addToBackStack(null);
            transaction.replace(R.id.content, fragment);
            transaction.commit();
        }
    }
}
