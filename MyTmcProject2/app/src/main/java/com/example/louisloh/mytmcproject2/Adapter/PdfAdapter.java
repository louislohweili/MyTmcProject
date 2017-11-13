/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.louisloh.mytmcproject2.Adapter;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.louisloh.mytmcproject2.Methods.PDF;
import com.example.louisloh.mytmcproject2.R;

import java.util.ArrayList;
/**
 * Created by louisloh on 14/7/2017.
 */

public class PdfAdapter extends ArrayAdapter<PDF>
{
    Activity activity;
    int layoutResourceId;
    ArrayList<PDF> data=new ArrayList<PDF>();
    PDF pdf;

    public PdfAdapter(Activity activity, int layoutResourceId, ArrayList<PDF> data) {
        super(activity, layoutResourceId, data);
        this.activity=activity;
        this.layoutResourceId=layoutResourceId;
        this.data=data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        PdfHolder holder=null;
        if(row==null)
        {
            LayoutInflater inflater=LayoutInflater.from(activity);
            row=inflater.inflate(layoutResourceId,parent,false);
            holder=new PdfHolder();
            holder.textViewName=(TextView) row.findViewById(R.id.textViewName);
            holder.textViewUrl= (TextView)row.findViewById(R.id.textViewUrl);
            row.setTag(holder);
        }
        else
        {
            holder= (PdfHolder) row.getTag();
        }

        pdf = data.get(position);
        holder.textViewName.setText(pdf.getName());
        holder.textViewUrl.setText(pdf.getUrl());
        return row;
    }


    class PdfHolder
    {
        TextView textViewName,textViewUrl;
    }

}
