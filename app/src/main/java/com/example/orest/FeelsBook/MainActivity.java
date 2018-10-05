/**
 * @authors Skryt
 *
 * MainActivity is the main driver of the application, it manages the loading/saving of emotions
 * from disk, and adding/editing an emotion in the array
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

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ArrayList<Emotion> emotionArray;
    private final static String COUNTER_FILE_NAME = "counters.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get counters from disk
        loadEmotions();

        // begin fragment transition
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction =  manager.beginTransaction();
        AddFragment fragment = AddFragment.newInstance();
        transaction.replace(R.id.content, fragment);
        transaction.commit();

    }

    // add emotion to array
    public void onAdd(Emotion emotion){
        if(emotion != null) {
            emotionArray.add(0,emotion);
        }
        saveEmotions();
    }

    // edit the emotion in the array
    public void onEdit(Emotion emotion, int index){
        emotionArray.remove(index);
        if(emotion!= null){
            emotionArray.add(0, emotion);
        }
        saveEmotions();
        onBackPressed();
    }

    // load emotions from disk
    private void loadEmotions(){
        try {
            FileInputStream stream = openFileInput(COUNTER_FILE_NAME);
            ObjectInputStream objStream = new ObjectInputStream(stream);
            emotionArray = (ArrayList<Emotion>) objStream.readObject();
            stream.close();
            objStream.close();
        }
        catch(java.io.IOException | java.lang.ClassNotFoundException e) {
            emotionArray = new ArrayList<>();
        }
    }

    // save emotions to disk
    private void saveEmotions() {
        try {
            FileOutputStream stream = openFileOutput(COUNTER_FILE_NAME,0);
            ObjectOutputStream objStream = new ObjectOutputStream(stream);
            objStream.writeObject(emotionArray);
            stream.close();
            objStream.close();
        }
        catch(java.io.IOException e) {
            //do nothing
        }
    }
}
