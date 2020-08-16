package com.example.quanlynhansu.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.quanlynhansu.QuanLyNhanSuActivity;
import com.example.quanlynhansu.R;
import com.example.quanlynhansu.data_models.NhanSu;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<NhanSu> {
    private Activity context;
    private int layoutID;
    private ArrayList<NhanSu> listMembers;


    public MyAdapter(Activity context, int resource, ArrayList<NhanSu> list) {
        super(context, resource, list);
        this.context = context;
        this.layoutID = resource;
        this.listMembers = list;
    }

    //define view holder
    static class ViewHolder {
        ImageView imgDegree;
        EditText edtName;
        EditText edtHobbies;
        CheckBox chkListView;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //create new view for the ListView
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = context.getLayoutInflater().inflate(layoutID, parent, false);

            viewHolder.imgDegree = (ImageView) convertView.findViewById(R.id.imgDegree);
            viewHolder.edtName = (EditText) convertView.findViewById(R.id.edtName);
            viewHolder.edtHobbies = (EditText) convertView.findViewById(R.id.edtHobbies);
            viewHolder.chkListView = (CheckBox) convertView.findViewById(R.id.chkListItem);

            //binging the view in convertView coresponding
            convertView.setTag(viewHolder);
        }
        //re-uses the view in convertView
        else {
            viewHolder = (ViewHolder) convertView.getTag();
            //lấy giá trị tương ứng trong datasource
            viewHolder.chkListView.setChecked(listMembers.get(position).isCheck());
        }

        //
        NhanSu nhanSu = listMembers.get(position);
        viewHolder.edtName.setText(nhanSu.getName());
        viewHolder.edtHobbies.setText(nhanSu.getHoppies());
        if (nhanSu.getDegree().equalsIgnoreCase(QuanLyNhanSuActivity.TRUNGCAP)) {
            viewHolder.imgDegree.setBackground(context.getResources().getDrawable(R.mipmap.trungcap));
        } else if (nhanSu.getDegree().equalsIgnoreCase(QuanLyNhanSuActivity.CAODANG)) {
            viewHolder.imgDegree.setBackground(context.getResources().getDrawable(R.mipmap.caodang));
        } else if (nhanSu.getDegree().equalsIgnoreCase(QuanLyNhanSuActivity.DAIHOC)) {
            viewHolder.imgDegree.setBackground(context.getResources().getDrawable(R.mipmap.daihoc));
        } else if (nhanSu.getDegree().equalsIgnoreCase(QuanLyNhanSuActivity.KHONGBANGCAP)) {
            viewHolder.imgDegree.setBackground(context.getResources().getDrawable(R.mipmap.khongbangcap));
        }

        viewHolder.chkListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewHolder.chkListView.isChecked()) {
                    listMembers.get(position).setCheck(true);
                }
                else {
                    listMembers.get(position).setCheck(false);
                }
            }
        });
        return convertView;
    }
}
