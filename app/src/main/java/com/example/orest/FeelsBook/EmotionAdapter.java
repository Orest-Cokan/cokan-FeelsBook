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

    public EmotionAdapter(FragmentActivity activity, ArrayList<Emotion> emotionArrayList) {
        this.activity = activity;
        this.emotionArrayList = emotionArrayList;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("Test", "Counteradapater");
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View counterView = inflater.inflate(R.layout.entry_pepe, parent, false);
        return new ViewHolder(counterView, this);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String emotion = emotionArrayList.get(position).getEmotion();
        holder.emotion.setImageDrawable(activity.getDrawable(activity.getResources().getIdentifier
                (emotion.toLowerCase(), "drawable", activity.getPackageName())));
        holder.comment.setText(emotionArrayList.get(position).getComments());
        holder.date.setText(emotionArrayList.get(position).getDate());
    }


    @Override
    public int getItemCount() {
        Log.d("Test", emotionArrayList.size()+"random");
        return emotionArrayList.size();
    }



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
