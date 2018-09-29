package com.example.orest.FeelsBook;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Emotion implements Serializable{

    private String emotion;
    private String comments;
    private String date;


    public Emotion(String emotion, String comments, Date date){
        this.emotion = emotion;
        this.comments = comments;
        this.date = getFormattedDate(date);
    }


    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }


    private String getFormattedDate (Date date) {
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD'T'H:mm:ss", Locale.CANADA);
        return format.format(date);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

}
