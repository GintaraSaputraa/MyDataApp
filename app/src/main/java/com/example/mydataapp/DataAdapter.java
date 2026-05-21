package com.example.mydataapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DataAdapter extends ArrayAdapter<DataModel> {

    Activity context;
    ArrayList<DataModel> list;

    public DataAdapter(Activity context, ArrayList<DataModel> list) {
        super(context, R.layout.item_data, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_data, parent, false);
        }

        TextView tv = convertView.findViewById(R.id.tvItem);

        DataModel m = list.get(position);

        tv.setText(
                "NIM: " + m.nim + "\n" +
                        "Nama: " + m.nama + "\n" +
                        "Prodi: " + m.prodi + "\n" +
                        "Kelas: " + m.kelas + "\n" +
                        "Alamat: " + m.alamat + "\n" +
                        "Email: " + m.email
        );

        return convertView;
    }
}