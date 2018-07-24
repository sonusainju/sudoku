package com.example.android.sudoku.view;

import android.support.v7.widget.AppCompatButton;
import android.view.View;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;

import com.example.android.sudoku.GameEngine;
import com.example.android.sudoku.Logic;
import com.example.android.sudoku.view.sudokuGrid.GameGrid;
import com.example.android.sudoku.view.sudokuGrid.SudokuCell;

/**
 * Created by sonu's on 2018-07-20.
 */

public class clearButton extends AppCompatButton implements View.OnClickListener {
    public GameGrid grid = null;

    public clearButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        grid = GameEngine.getInstance().getGrid();
        grid.clearGrid();

    }
}