package com.example.android.sudoku.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.android.sudoku.R;

/**
 * Created by sonu's on 2018-01-11.
 */

public class SudokuGridViewAdapter extends BaseAdapter {

    private Context context;

    public SudokuGridViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 81;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflator = ((Activity) context).getLayoutInflater();
            v = inflator.inflate(R.layout.cell, parent, false);
        }
        return v;
    }

}
