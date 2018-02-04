package com.example.android.sudoku;

import com.example.android.sudoku.view.sudokuGrid.GameGrid;

import android.content.Context;

public class GameEngine {
    private static GameEngine instance;

    private GameGrid grid = null;

    private int selectedPosX = -1, selectedPosY = -1;

    private GameEngine(){}

    public static GameEngine getInstance(){
        if( instance == null ){
            instance = new GameEngine();
        }
        return instance;
    }

    public void createGrid(Context context){
        int[][] Sudoku =  {{0,1,2,3,4,5,6,7,8},
                           {1,0,0,0,5,6,7,8,9},
                           {2,2,0,2,0,2,0,2,2},
                           {3,3,3,3,3,3,3,3,3},
                           {3,3,0,3,0,3,0,3,3},
                           {3,0,0,3,0,3,0,3,3},
                           {3,3,3,3,3,3,3,3,3},
                           {3,0,3,0,3,3,3,3,3},
                           {3,3,3,3,3,3,3,3,3}};

        grid = new GameGrid(context);
        grid.setGrid(Sudoku);
    }

    public GameGrid getGrid(){
        return grid;
    }

    public void setGrid(int[][] sudoku)
    {
        grid.setGrid(sudoku);
    }

    public void setSelectedPosition( int x , int y ){
        selectedPosX = x;
        selectedPosY = y;
    }

    public void setNumber( int number ){
        if( selectedPosX != -1 && selectedPosY != -1 ){
            grid.setItem(selectedPosX,selectedPosY,number);
        }
        //grid.checkGame();
    }
}
