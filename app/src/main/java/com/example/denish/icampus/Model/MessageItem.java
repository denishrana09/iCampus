package com.example.denish.icampus.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by denish on 7/4/18.
 */

public class MessageItem implements Parcelable {
    private String text;
    private String name;

    public MessageItem() {
    }

    public MessageItem(String text, String name, String photoUrl,String canAccess) {
        this.text = text;
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.text);
        dest.writeString(this.name);
    }

    protected MessageItem(Parcel in) {
        this.text = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<MessageItem> CREATOR = new Parcelable.Creator<MessageItem>() {
        @Override
        public MessageItem createFromParcel(Parcel source) {
            return new MessageItem(source);
        }

        @Override
        public MessageItem[] newArray(int size) {
            return new MessageItem[size];
        }
    };
}
