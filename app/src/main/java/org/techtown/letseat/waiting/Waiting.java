package org.techtown.letseat.waiting;

public class Waiting {
    private String waiting_number;
    private String name;
    private String phonenum;
    private String person_number;
    private String reception_time;


    public Waiting(String waiting_number, String name, String phonenum, String person_number,
                   String reception_time){
        this.waiting_number = waiting_number;
        this.name = name;
        this.phonenum = phonenum;
        this.person_number = person_number;
        this.reception_time = reception_time;
    }
    public String getWaiting_number(){return  waiting_number;}
    public String getName(){
        return name;
    }
    public String getPhonenum(){
        return phonenum;
    }
    public String getPerson_number(){
        return person_number;
    }
    public String getReception_time(){
        return reception_time;
    }
}
