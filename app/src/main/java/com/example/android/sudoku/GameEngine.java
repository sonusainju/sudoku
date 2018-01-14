package com.example.android.sudoku;

/**
 * Created by sonu's on 2018-01-13.
 */

public class GameEngine {
    private static GameEngine instance;

    private int[][] Sudoku;

    private static GameEngine getInstance(){
        if(instance == null){
            instance = new GameEngine();
        }
        return instance;
    }

    public int[][] getSudoku(){
        return Sudoku;
    }

    public void setSudoku(int sudoku[][]){
        Sudoku = sudoku;
    }

}
