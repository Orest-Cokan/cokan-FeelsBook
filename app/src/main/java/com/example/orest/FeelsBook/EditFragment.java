package com.example.orest.FeelsBook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class EditFragment extends Fragment {

    public static EditFragment newInstance(Emotion emotion, int index){
        EditFragment fragment = new EditFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("INDEX", index);
        bundle.putSerializable("COUNTER", emotion);
        fragment.setArguments(bundle);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_edit, container, false);
        getActivity().setTitle("Edit Emotion");


        Bundle bundle = getArguments();
        final int index = bundle.getInt("INDEX");
        final Emotion emotionStr = (Emotion) bundle.getSerializable("COUNTER");

        // ui definitions
        final ImageView emotion = (ImageView) rootView.findViewById(R.id.emotion);
        final EditText date = (EditText) rootView.findViewById(R.id.date);
        final EditText comments = (EditText) rootView.findViewById(R.id.comment);
        Button save = (Button) rootView.findViewById(R.id.save_button);
        Button delete = (Button) rootView.findViewById(R.id.delete_button);

        // set ui values
        emotion.setImageDrawable(getActivity().getDrawable(getActivity().getResources().getIdentifier
                (emotionStr.getEmotion().toLowerCase(), "drawable", getActivity().getPackageName())));
        date.setText(emotionStr.getDate());
        comments.setText(emotionStr.getComments());

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comm =comments.getText().toString().trim();
                if (comments.getText().toString().length() > 100){
                    Toast.makeText(getActivity(), "You fucked up kiddo, change comments", Toast.LENGTH_LONG).show();
                    comments.getText().clear();
                }
                else {
                    emotionStr.setComments(comm);
                    emotionStr.setDate(date.getText().toString());
                    ((MainActivity) getActivity()).onEdit(emotionStr, index);
                }
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).onEdit(null, index);
            }
        });


        return rootView;
    }


}
