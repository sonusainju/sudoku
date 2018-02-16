package com.example.android.sudoku.view;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;

import com.example.android.sudoku.GameEngine;
import com.example.android.sudoku.Logic;
import com.example.android.sudoku.view.sudokuGrid.GameGrid;

/**
 * Created by sonu's on 2018-02-01.
 */

public class solveButton extends AppCompatButton implements View.OnClickListener {
    public GameGrid grid = null;

    /*public  int[][] sudoku =  {{0,1,2,3,4,5,6,7,8},
            {1,4,4,4,5,6,7,8,9},
            {2,2,0,2,0,2,0,2,2},
            {3,3,3,3,3,3,3,3,3},
            {3,3,0,3,0,3,0,3,3},
            {3,0,0,3,0,3,0,3,3},
            {3,3,3,3,3,3,3,3,3},
            {3,0,3,0,3,3,3,3,3},
            {3,3,3,3,3,3,3,3,3}};*/

    private int[][] sudoku = new int[][]{
            {7, 0, 8, 0, 3, 0, 2, 0, 0},
            {6, 0, 0, 0, 0, 5, 3, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {9, 0, 0, 0, 0, 0, 0, 5, 0},
            {0, 4, 1, 0, 0, 0, 7, 3, 0},
            {0, 5, 0, 0, 0, 0, 0, 0, 9},
            {0, 0, 0, 8, 0, 0, 0, 0, 0},
            {0, 0, 5, 7, 0, 0, 0, 0, 3},
            {0, 0, 7, 0, 4, 0, 8, 0, 1}};

    public solveButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int[][] solvedSudoku;

        GameEngine.getInstance().setGrid(sudoku);
        Logic logicObject=new Logic();

        solvedSudoku=logicObject.solvePuzzle(sudoku);
    }

}
