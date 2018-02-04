package com.example.android.sudoku.view;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;

import com.example.android.sudoku.GameEngine;
import com.example.android.sudoku.view.sudokuGrid.GameGrid;

/**
 * Created by sonu's on 2018-02-01.
 */

public class solveButton extends AppCompatButton implements View.OnClickListener {
    public GameGrid grid = null;

    public  int[][] sudoku =  {{0,1,2,3,4,5,6,7,8},
            {1,4,4,4,5,6,7,8,9},
            {2,2,0,2,0,2,0,2,2},
            {3,3,3,3,3,3,3,3,3},
            {3,3,0,3,0,3,0,3,3},
            {3,0,0,3,0,3,0,3,3},
            {3,3,3,3,3,3,3,3,3},
            {3,0,3,0,3,3,3,3,3},
            {3,3,3,3,3,3,3,3,3}};


    public solveButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        GameEngine.getInstance().setGrid(sudoku);
    }

}
