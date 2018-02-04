package com.example.android.sudoku.view.buttonsGrid;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.example.android.sudoku.R;

/**
 * Created by sonu's on 2018-01-28.
 */

public class ButtonGridView extends GridView{
    public ButtonGridView(Context context, AttributeSet attr){
        super(context, attr);

        ButtonGridViewAdapter gridViewAdapter = new ButtonGridViewAdapter(context);

        setAdapter(gridViewAdapter);
    }

    class ButtonGridViewAdapter extends BaseAdapter{

        private Context context;

        public ButtonGridViewAdapter(Context context)
        {
            this.context = context;
        }

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            if(v == null){
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                v = inflater.inflate(R.layout.button, parent, false);

                NumberButton btn;
                btn = (NumberButton)v;
                btn.setTextSize(15);
                btn.setId(position);

                if(position != 9 ) {
                    btn.setText(String.valueOf(position + 1));
                    btn.setNumber(position+1);
                }
                else{
                    btn.setText("Del");
                    btn.setNumber(0);
                }
                return btn;
            }
            return v;
        }
    }
}
