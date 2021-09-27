package org.techtown.letseat.waiting;

public class Waiting {
    private String name;
    private String phonenum;
    private int resId;

    public Waiting(String name, String phonenum){
        this.name = name;
        this.phonenum = phonenum;
    }
    public String getName(){
        return name;
    }
    public String getPhonenum(){
        return phonenum;
    }
}
