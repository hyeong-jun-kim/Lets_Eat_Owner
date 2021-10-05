package org.techtown.letseat.waiting;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.techtown.letseat.R;

public class Waiting {
    String date,waitingnum, id, people, phonenumber;
    int src;

    public Waiting(String date, String waitingnum, String id, String people, String phonenumber, int src) {
        this.date = date;
        this.waitingnum = waitingnum;
        this.id = id;
        this.people = people;
        this.phonenumber = phonenumber;
        this.src = src;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWaitingnum() {
        return waitingnum;
    }

    public void setWaitingnum(String waitingnum) {
        this.waitingnum = waitingnum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }
}
