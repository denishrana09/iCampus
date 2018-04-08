package com.example.denish.icampus.Model;

/**
 * Created by mek on 4/7/18.
 */

public class AnnounceDataModel {
    String desc;
    String by;

    public AnnounceDataModel() {
    }

    public AnnounceDataModel(String desc, String by){
        this.desc = desc;
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public String getDesc() {
        return desc;
    }
}
