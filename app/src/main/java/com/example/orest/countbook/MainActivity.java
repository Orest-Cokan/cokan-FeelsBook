package com.example.orest.countbook;

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

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction =  manager.beginTransaction();
        AddFragment fragment = AddFragment.newInstance();
        transaction.replace(R.id.content, fragment);
        transaction.commit();

    }

    public void onAdd(Emotion emotion){
        if(emotion != null) {
            emotionArray.add(emotion);
        }
        saveEmotions();
    }



    public void onEdit(Emotion emotion, int index){
        if(emotion == null)
            emotionArray.remove(index);
        else
            emotionArray.set(index, emotion);

        saveEmotions();
        onBackPressed();
    }

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

    private void saveEmotions() {
        try {
            FileOutputStream stream = openFileOutput(COUNTER_FILE_NAME,0);
            ObjectOutputStream objStream = new ObjectOutputStream(stream);
            objStream.writeObject(emotionArray);
            stream.close();
            objStream.close();
        }
        catch(java.io.IOException e) {
            emotionArray = new ArrayList<>();
        }
    }
}
