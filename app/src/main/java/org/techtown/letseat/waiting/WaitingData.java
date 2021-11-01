package org.techtown.letseat.waiting;

public class WaitingData {
    int resId,userId;
    String date, waitingnum, id, people, phonenumber;

    public WaitingData(int resId, String date, String waitingnum, String id, String people, String phonenumber,int userId) {
        this.resId = resId;
        this.date = date;
        this.waitingnum = waitingnum;
        this.id = id;
        this.people = people;
        this.phonenumber = phonenumber;
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
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
}
