package com.example.orest.FeelsBook;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class AddFragment extends Fragment {
    // attributes
    private String selected;

    public static AddFragment newInstance() {
        return new AddFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_add_pepe, container, false);
        getActivity().setTitle("cokan - FeelsBook");

        // ui definitions
        final ImageView angry = (ImageView) rootView.findViewById(R.id.angry);
        final ImageView fear = (ImageView) rootView.findViewById(R.id.fear);
        final ImageView sad = (ImageView) rootView.findViewById(R.id.sad);
        final ImageView surprise = (ImageView) rootView.findViewById(R.id.surprise);
        final ImageView love = (ImageView) rootView.findViewById(R.id.love);
        final ImageView joy = (ImageView) rootView.findViewById(R.id.joy);
        final EditText comments = (EditText) rootView.findViewById(R.id.comment);
        Button history = (Button) rootView.findViewById(R.id.history_button);
        Button statistics = (Button) rootView.findViewById(R.id.stats_button);

        // set onclicker listeners for emotions
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fear.setColorFilter(null);
                sad.setColorFilter(null);
                surprise.setColorFilter(null);
                love.setColorFilter(null);
                angry.setColorFilter(null);
                joy.setColorFilter(null);
                if (v == angry) {
                    selected = "angry";
                    angry.setColorFilter(Color.parseColor("#55000000"));
                    checkTrigger(comments);
                }

                else if (v == fear){
                    selected = "fear";
                    fear.setColorFilter(Color.parseColor("#55000000"));
                    checkTrigger(comments);
                }

                else if (v == sad) {
                    selected = "sad";
                    sad.setColorFilter(Color.parseColor("#55000000"));
                    checkTrigger(comments);
                }

                else if (v == surprise) {
                    selected = "surprise";
                    surprise.setColorFilter(Color.parseColor("#55000000"));
                    checkTrigger(comments);
                }
                else if (v == joy){
                    selected = "joy";
                    joy.setColorFilter(Color.parseColor("#55000000"));
                    checkTrigger(comments);
                }
                else {
                    selected = "love";
                    love.setColorFilter(Color.parseColor("#55000000"));
                    checkTrigger(comments);
                }
                delayFilter((ImageView) v);
            }

        };

        // attach onclick listener to emotions
        angry.setOnClickListener(listener);
        fear.setOnClickListener(listener);
        sad.setOnClickListener(listener);
        surprise.setOnClickListener(listener);
        love.setOnClickListener(listener);
        joy.setOnClickListener(listener);


        // set onclick listener for history button
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction =  manager.beginTransaction();
                transaction.addToBackStack(null);
                RecyclerFragment fragment = RecyclerFragment.newInstance();
                transaction.replace(R.id.content, fragment);
                transaction.commit();
            }
        });

        statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction =  manager.beginTransaction();
                transaction.addToBackStack(null);
                StatsFragment statsFragment = StatsFragment.newInstance();
                transaction.replace(R.id.content, statsFragment);
                transaction.commit();
            }
        });

        return rootView;
    }

    // check if conditions are met, if so, create the emotion
    public void checkTrigger(EditText comments) {
        if (comments.getText().toString().trim().length() > 100) {
            Toast.makeText(getActivity(), "You messed up kiddo, change comments", Toast.LENGTH_LONG).show();
            comments.getText().clear();
        }
        else {
            Emotion emotion = new Emotion(
                    selected,
                    comments.getText().toString().trim(),
                    new Date()
            );
            ((MainActivity) getActivity()).onAdd(emotion);
            Toast.makeText(getActivity(), "You added an emotion", Toast.LENGTH_LONG).show();
            comments.getText().clear();
        }
    }

    // add a slick 1 second filter to the emotion clicked
    public void delayFilter(final ImageView v) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                v.clearColorFilter();
            }
        }, 1000);
    }

}
