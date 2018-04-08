package com.example.denish.icampus;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.denish.icampus.Model.FriendlyMessage;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<FriendlyMessage> {
    String curUser;
    public MessageAdapter(Context context, int resource, List<FriendlyMessage> objects,String curUser) {
        super(context, resource, objects);
        this.curUser = curUser;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_message, parent, false);
        }

        //ImageView photoImageView = (ImageView) convertView.findViewById(R.id.photoImageView);
        TextView messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
        TextView authorTextView = (TextView) convertView.findViewById(R.id.nameTextView);

        FriendlyMessage message = (FriendlyMessage)getItem(position);

        if(curUser.equals(message.getName())){
            messageTextView.setGravity(Gravity.END);
            authorTextView.setGravity(Gravity.END);
        }
        messageTextView.setVisibility(View.VISIBLE);
        messageTextView.setText(message.getText());

        authorTextView.setText(message.getName());

        return convertView;
    }
}
