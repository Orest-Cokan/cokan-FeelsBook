/**
 * @authors Skryt
 *
 * EditFragment allows for users to edit an emotion in history.
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.orest.FeelsBook.R;


public class EditFragment extends Fragment {

    // create a newInstance of EditFragment
    public static EditFragment newInstance(Emotion emotion, int index){
        EditFragment fragment = new EditFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("INDEX", index);
        bundle.putSerializable("COUNTER", emotion);
        fragment.setArguments(bundle);
        return fragment;
    }


    // display the view to the screen
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_edit, container, false);
        getActivity().setTitle("Edit Emotion");


        Bundle bundle = getArguments();
        // get the position and emotion from the list
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

        // create and set onclicker listener for edit save
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comm =comments.getText().toString().trim();
                if (comments.getText().toString().length() > 100){
                    Toast.makeText(getActivity(), "You messed up kiddo, change comments", Toast.LENGTH_LONG).show();
                    comments.getText().clear();
                }
                else {
                    emotionStr.setComments(comm);
                    emotionStr.setDate(date.getText().toString());
                    ((MainActivity) getActivity()).onEdit(emotionStr, index);
                }
            }
        });

        // create and set onclicker listener for delete
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).onEdit(null, index);
            }
        });


        return rootView;
    }


}
