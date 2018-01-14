package com.example.android.sudoku.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

/**
 * Created by sonu's on 2018-01-11.
 */
// A gridview is a viewgroup which allows us to display items on a two-dimensional scrollable grid.
// It is populated using an adapter that fetches data from sources such as array or database.
// Here the adapter is a custom adapter.

public class SudokuGridView extends GridView {
    private final Context context;

    public SudokuGridView(final Context context, AttributeSet attr) {
        super(context, attr);

        this.context = context;

        SudokuGridViewAdapter gridViewAdapter = new SudokuGridViewAdapter(context);
        setAdapter(gridViewAdapter);

// setonItemClickLister() method sets the new onItemClickListner which defines a callback method
// - onItemClick() that calls a toast(its like a pop up msg) to show the position of the click.
        setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int x = position % 9;
                int y = position / 9;

                Toast.makeText(context, "Selected item -x: " + x + " y: " + y, Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    private void TestUplpoad(){
        int i = 0;
    }
}

