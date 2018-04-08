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

import java.util.ArrayList;

/**
 * Created by mek on 4/7/18.
 */

public class AnnounceAdapter extends ArrayAdapter<AnnounceDataModel>{

    private ArrayList<AnnounceDataModel> arrayList;
    Context context;
    int r;

    public AnnounceAdapter(ArrayList<AnnounceDataModel> arrayList, int r, Context context){
        super(context,r,arrayList);
        this.arrayList = arrayList;
        this.context = context;
        this.r = r;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(r, null, false);

        TextView txtDesc = view.findViewById(R.id.annoucDesc);
        TextView txtBy = view.findViewById(R.id.annoucBy);
        AnnounceDataModel data = arrayList.get(position);

        txtDesc.setText(data.getDesc());
        txtBy.setText(data.getBy());

        return view;
    }
}
