package com.example.orest.countbook;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Emotion implements Serializable{

    private String emotion;
    private String comments;
    private Date date;


    public Emotion(String emotion, String comments, Date date){
        this.emotion = emotion;
        this.comments = comments;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public String getFormattedDate () {
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd", Locale.CANADA);
        return format.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
