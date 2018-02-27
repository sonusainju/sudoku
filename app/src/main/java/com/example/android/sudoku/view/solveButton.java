package com.example.android.sudoku.view;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;

import com.example.android.sudoku.GameEngine;
import com.example.android.sudoku.Logic;
import com.example.android.sudoku.MainActivity;
import com.example.android.sudoku.view.sudokuGrid.GameGrid;
import com.example.android.sudoku.view.sudokuGrid.SudokuCell;

import android.view.View.OnClickListener;

import static android.R.attr.x;
import static android.R.attr.y;
import static com.example.android.sudoku.R.layout.cell;

/**
 * Created by sonu's on 2018-02-01.
 */

public class solveButton extends AppCompatButton implements View.OnClickListener {
    public GameGrid grid = null;
    private int[][] sudoku = new int[9][9];

    /*public  int[][] sudoku =  {{0,1,2,3,4,5,6,7,8},
            {1,4,4,4,5,6,7,8,9},
            {2,2,0,2,0,2,0,2,2},
            {3,3,3,3,3,3,3,3,3},
            {3,3,0,3,0,3,0,3,3},
            {3,0,0,3,0,3,0,3,3},
            {3,3,3,3,3,3,3,3,3},
            {3,0,3,0,3,3,3,3,3},
            {3,3,3,3,3,3,3,3,3}};*/

  /*  private int[][] sudoku = new int[][]{
            {7, 0, 8, 0, 3, 0, 2, 0, 0},
            {6, 0, 0, 0, 0, 5, 3, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {9, 0, 0, 0, 0, 0, 0, 5, 0},
            {0, 4, 1, 0, 0, 0, 7, 3, 0},
            {0, 5, 0, 0, 0, 0, 0, 0, 9},
            {0, 0, 0, 8, 0, 0, 0, 0, 0},
            {0, 0, 5, 7, 0, 0, 0, 0, 3},
            {0, 0, 7, 0, 4, 0, 8, 0, 1}};*/

    public solveButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        grid = GameEngine.getInstance().getGrid();
        //grid.setGrid(sudoku);

        for (int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++) {
                SudokuCell cell = grid.getItem(i, j);
                sudoku[i][j] = cell.getValue();
            }
        }


        boolean solvedSudoku;
        int[][] solvedMatrix;
        Logic logicObject=new Logic(sudoku);
        solvedSudoku=logicObject.solvePuzzle();
        if(solvedSudoku){
            solvedMatrix=logicObject.getSolvedMatrix();

            //Display the solved puzzle
            grid.setGrid(solvedMatrix);
        } else if (logicObject.getPuzzleUnsolvableFlag()) {
            //Display message to say puzzle is wrong and cannot be solved

        } else{
            //Display message to say puzzle could not be solved.
        }

    }

}
