package com.example.orest.countbook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class AddFragment extends Fragment {
    private final static String COUNTERFILENAME = "counters.sav";
    private ArrayList<Emotion> counterArray;
    private String selected;

    public static AddFragment newInstance() {
        return new AddFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_add_pepe, container, false);
        getActivity().setTitle("Add Emotion");

        // ui definitions
        final ImageButton angryPepe = (ImageButton) rootView.findViewById(R.id.anger_pepe);
        final ImageButton fearPepe = (ImageButton) rootView.findViewById(R.id.fear_pepe);
        final ImageButton sadPepe = (ImageButton) rootView.findViewById(R.id.sad_pepe);
        final ImageButton surprisePepe = (ImageButton) rootView.findViewById(R.id.surprise_pepe);
        final ImageButton lovePepe = (ImageButton) rootView.findViewById(R.id.love_pepe);
        final EditText comments = (EditText) rootView.findViewById(R.id.comment);
        Button save = (Button) rootView.findViewById(R.id.save_button);
        Button history = (Button) rootView.findViewById(R.id.history_button);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == angryPepe) {
                    selected = "Angry";
                }
                else if (v == fearPepe){
                    selected = "Fear";
                }
                else if (v == sadPepe) {
                    selected = "Sad";
                }
                else if (v == surprisePepe) {
                    selected = "Surprised";
                }
                else {
                    selected = "Love";
                }
            }

        };

        angryPepe.setOnClickListener(listener);
        fearPepe.setOnClickListener(listener);
        sadPepe.setOnClickListener(listener);
        surprisePepe.setOnClickListener(listener);
        lovePepe.setOnClickListener(listener);

        // set onclicklistener save button
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (comments.getText().toString().length() > 30 || comments.getText().toString().trim().length() == 0) {
                    Toast.makeText(getActivity(), "You fucked up kiddo, change comments", Toast.LENGTH_LONG).show();
                    comments.getText().clear();
                }
                else {
                    Emotion emotion = new Emotion(
                            selected,
                            comments.getText().toString(),
                            new Date()
                    );
                    ((MainActivity) getActivity()).onAdd(emotion);
                    Toast.makeText(getActivity(), "You added an emotion", Toast.LENGTH_LONG).show();
                    comments.getText().clear();
                }
            }
        });


        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction =  manager.beginTransaction();
                RecyclerFragment fragment = RecyclerFragment.newInstance();
                transaction.replace(R.id.content, fragment);
                transaction.commit();
            }
        });
        return rootView;
    }

}
