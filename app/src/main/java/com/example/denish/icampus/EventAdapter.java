package com.example.denish.icampus;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.denish.icampus.Model.AnnounceDataModel;
import com.example.denish.icampus.Model.EventModel;

import java.util.ArrayList;

/**
 * Created by denish on 8/4/18.
 */

public class EventAdapter extends ArrayAdapter<EventModel> {

    private ArrayList<EventModel> e;
    Context context;

    public EventAdapter(ArrayList<EventModel> e, Context context){
        super(context,R.layout.event_raw,e);
        this.e = e;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.event_raw, null, false);

        TextView txtDesc = view.findViewById(R.id.eventDesc);
        TextView txtDate = view.findViewById(R.id.eventDate);
        EventModel data = e.get(position);

        txtDesc.setText(data.getDesc());
        txtDate.setText(data.getDate());
        return view;
    }
}
