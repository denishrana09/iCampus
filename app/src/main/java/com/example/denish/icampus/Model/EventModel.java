package com.example.denish.icampus.Model;

/**
 * Created by denish on 8/4/18.
 */

public class EventModel {
    String desc;
    String date;

    public EventModel(){

    }

    public EventModel(String desc,String date){
        this.desc = desc;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String getDesc() {
        return desc;
    }
}
