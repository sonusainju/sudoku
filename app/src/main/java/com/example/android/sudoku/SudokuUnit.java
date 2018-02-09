package com.example.android.sudoku;

/**
 * Created by dines on 2018-01-16.
 */

import android.util.Log;

import java.util.*;

import static android.R.string.no;

public class SudokuUnit {
    private int xPos, yPos, boxX, boxY, finalValue;
    //private static int noOfValuesFinalized=0;

    private boolean valueFinalized = false;
    private Set<Integer> possibleValues = new HashSet<Integer>();  //Set<Integer> existingValues = new HashSet<Integer>();

    public SudokuUnit() {

    }

    public SudokuUnit(int x, int y) {
        this.xPos = x;
        this.yPos = y;

        boxX = (xPos) / 3;
        boxY = (yPos) / 3;

        for (int i = 1; i < 10; i++) {
            possibleValues.add(i);
        }
    }

    /*public SudokuUnit(SudokuUnit another) {
        this.xPos = another.xPos;
        this.yPos = another.yPos;
        this.boxX = another.boxX;
        this.boxY = another.boxY;
        this.valueFinalized = another.getValueFinalized();
        this.possibleValues = another.getPossibleValues();
        if (another.valueFinalized) this.finalValue = another.getFinalValue();

    }*/

    public void setFinalValue(int val) {
        this.finalValue = val;
        this.valueFinalized = true;
        //noOfValuesFinalized++;
        this.possibleValues.removeAll(this.getPossibleValues());
        this.possibleValues.add(val);
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public boolean getValueFinalized() { return this.valueFinalized;
    }

    public int getFinalValue() {
        return finalValue;
    }

    //public static int getNoOfValuesFinalized() {return noOfValuesFinalized;}
    //public static boolean getNoSolutionAvailableForThis() {return noSolutionAvailableForThis;}
    public int getNoOfPossibleValues() {
        return possibleValues.size();
    }

    public Set<Integer> getPossibleValues() {
        return possibleValues;
    }
    public boolean containsPossibleValue(int val) {return possibleValues.contains(val);}

    public int removeTheseFromPossibleValues(Set<Integer> arr) {
        //This method will return possibleValues.size after the removal
        possibleValues.removeAll(arr);
        if (possibleValues.size() == 1) {
            for (int i : possibleValues) {
                this.finalValue = i;
            }
            this.valueFinalized = true;
        }
        return possibleValues.size();
    }
}
