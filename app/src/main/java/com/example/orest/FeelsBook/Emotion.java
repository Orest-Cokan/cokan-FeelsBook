/**
 * @authors Skryt
 *
 * Emotion is a class that holds data for what an emotion should have, the emotion, comments and
 * date. It has a constructor to make each emotion object, as well as getters/setters.
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

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Emotion implements Serializable{
    // attributes of an emotion
    private String emotion;
    private String comments;
    private String date;

    // construct an emotion
    public Emotion(String emotion, String comments, Date date){
        this.emotion = emotion;
        this.comments = comments;
        this.date = getFormattedDate(date);
    }

    // various getters and setters for an emotion
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
