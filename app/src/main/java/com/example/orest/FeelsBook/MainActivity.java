/**
 * @authors Skryt
 *
 * MainActivity is the main driver of the application, it manages the loading/saving of emotions
 * from disk, and adding/editing an emotion in the array
 *
 * returns: nothing
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
