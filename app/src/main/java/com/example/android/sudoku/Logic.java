package com.example.android.sudoku;

import android.util.Log;


/**
 * Created by dines on 2018-02-08.
 */

public class Logic {

    private final static String LOG_TAG = MainActivity.class.getSimpleName();
    private boolean puzzleSolved = false;
    private boolean puzzleUnsolvable=false;
    private int[][] puzzleMatrix;
    private int[][] solvedMatrix;
    //private SudokuGrid sudokuGrid;

    /*private int[][] A = new int[][]{
            {7, 0, 8, 0, 3, 0, 2, 0, 0},
            {6, 0, 0, 0, 0, 5, 3, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {9, 0, 0, 0, 0, 0, 0, 5, 0},
            {0, 4, 1, 0, 0, 0, 7, 3, 0},
            {0, 5, 0, 0, 0, 0, 0, 0, 9},
            {0, 0, 0, 8, 0, 0, 0, 0, 0},
            {0, 0, 5, 7, 0, 0, 0, 0, 3},
            {0, 0, 7, 0, 4, 0, 8, 0, 1}};*/

    public Logic(int[][] A){
        puzzleMatrix=A;
    }

    public boolean getPuzzleSolvedFlag(){return puzzleSolved;}

    public boolean solvePuzzle() {
        SudokuGrid sudokuGrid=new SudokuGrid(puzzleMatrix);

        Log.d(LOG_TAG, "Before processing start");
        sudokuGrid.printSudoku();
        sudokuGrid.printPossibleValues();

        puzzleSolved = sudokuGrid.solveGridWithAllLogics();

        /*if(puzzleSolved){
            return sudokuGrid.getMatrix();
        }*/
        puzzleUnsolvable=sudokuGrid.getNoSolutionAvailableForThis();
        if (puzzleUnsolvable) {Log.d(LOG_TAG, "No solution available for this grid. Wrong puzzle...");}

        Log.d(LOG_TAG, "After All logic");
        sudokuGrid.printSudoku();
        sudokuGrid.printPossibleValues();


        //If the sudoku hasn't been solved yet, we will start guessing

        if (!puzzleSolved && sudokuGrid.getNoOfValuesFinalized() < 81)

        {
            //SudokuGrid sudokuGridTemp=sudokuGrid;
            Log.d(LOG_TAG, "Guessing one number..");

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    //Set<Integer> possibleValues= sudokuGrid.getPossibleValues(i,j);
                    Integer[] possibleValues = sudokuGrid.getPossibleValues(i, j).toArray(new Integer[0]);
                    //for (Integer integer : possibleValues) {  // this iterator is not working somehow. fix this tomorrow.
                    for (int k = 0; k < possibleValues.length; k++) {
                        SudokuGrid sudokuGridTemp = new SudokuGrid(sudokuGrid.getMatrix());
                        puzzleSolved = sudokuGridTemp.makeOneGuess(i, j, possibleValues[k]);
                        if (puzzleSolved) {
                            Log.d(LOG_TAG, "After guessing at i=" + i + " j=" + j + "Guessed value =" + possibleValues[k]);
                            sudokuGridTemp.printSudoku();
                            sudokuGridTemp.printPossibleValues();
                            return  sudokuGridTemp.getMatrix();
                            //break;  //exit loop
                        }
                    }
                    if (puzzleSolved) break;
                }
                if (puzzleSolved) break;

            }

        }

        //If the sudoku hasn't been solved yet, we will start guessing two variables
        if (!puzzleSolved && sudokuGrid.getNoOfValuesFinalized() < 81)

        {
            //SudokuGrid sudokuGridTemp=sudokuGrid;
            Log.d(LOG_TAG, "Guessing two numbers..");
            //boolean puzzleSolved = false;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    //Set<Integer> possibleValues= sudokuGrid.getPossibleValues(i,j);
                    Integer[] possibleValues = sudokuGrid.getPossibleValues(i, j).toArray(new Integer[0]);
                    //for (Integer integer : possibleValues) {  // this iterator is not working somehow. fix this tomorrow.
                    for (int k = 0; k < possibleValues.length; k++) {
                        for (int l = 0; l < 9; l++) {
                            for (int m = 0; m < 9; m++) {
                                Integer[] possibleValues2 = sudokuGrid.getPossibleValues(l, m).toArray(new Integer[0]);
                                SudokuGrid sudokuGridTemp2 = new SudokuGrid(sudokuGrid.getMatrix());
                                for (int n = 0; n < possibleValues2.length; n++) {
                                    puzzleSolved = sudokuGridTemp2.makeTwoGuesses(i, j, possibleValues[k], l, m, possibleValues2[n]);
                                    //Log.d(LOG_TAG, "After making two guesses at i=" + i + " j=" + j + "Guessed value =" + possibleValues[k] + " l=" + l+ " m=" + m + "possibleValue2[n]="+ possibleValues2[n]  );
                                    if (puzzleSolved) {
                                        Log.d(LOG_TAG, "After making two guesses at i=" + i + " j=" + j + "Guessed value =" + possibleValues[k] + " l=" + l + " m=" + m + "possibleValue2[n]=" + possibleValues2[n]);
                                        sudokuGridTemp2.printSudoku();
                                        sudokuGridTemp2.printPossibleValues();
                                        return  sudokuGridTemp2.getMatrix();
                                        //break;  //exit loop
                                    }
                                }
                                if (puzzleSolved) break;
                            }
                            if (puzzleSolved) break;
                        }
                        if (puzzleSolved) break;
                    }
                    if (puzzleSolved) break;
                }
                if (puzzleSolved) break;
            }

        }
        Log.d(LOG_TAG, "Program Completed....");
    return sudokuGrid.getMatrix(); //If the puzzle hasn't solved so far, the latest result from sudokuGrid will be returned.
    }
}

//Log.d(LOG_TAG,"Program Completed....");

