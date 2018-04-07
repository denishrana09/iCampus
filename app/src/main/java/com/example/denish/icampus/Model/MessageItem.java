package com.example.denish.icampus.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by denish on 7/4/18.
 */

public class MessageItem implements Parcelable {
    private String text;
    private String name;
    private String photoUrl;

    public MessageItem() {
    }

    public MessageItem(String text, String name, String photoUrl,String canAccess) {
        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.text);
        dest.writeString(this.name);
        dest.writeString(this.photoUrl);
    }

    protected MessageItem(Parcel in) {
        this.text = in.readString();
        this.name = in.readString();
        this.photoUrl = in.readString();
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
