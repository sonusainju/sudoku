package com.example.android.sudoku;

/**
 * Created by dines on 2018-01-27.
 */

import android.util.Log;

import com.example.android.sudoku.MainActivity;

import java.util.*;

import static android.R.id.input;
import static android.view.View.Y;

public class SudokuGrid {
    //private int noOfValuesFinalized=0;
    private boolean noSolutionAvailableForThis=false;
    private SudokuUnit[][] sudokuUnit = new SudokuUnit[9][9];
    private final static String LOG_TAG = MainActivity.class.getSimpleName();

    public SudokuGrid(){}
    public SudokuGrid(int X[][]){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuUnit[i][j] = new com.example.android.sudoklogic.SudokuUnit(i, j);
                if (X[i][j] != 0) {
                    sudokuUnit[i][j].setFinalValue(X[i][j]);
                    //this.noOfValuesFinalized++;
                }

            }
        }
    }

    public boolean  getNoSolutionAvailableForThis(){return noSolutionAvailableForThis;}
    public int getNoOfValuesFinalized() {
        //return noOfValuesFinalized;
        int sum=0;
        for (int i = 0; i <9 ; i++) {
            for (int j = 0; j < 9; j++) {
                if(sudokuUnit[i][j].getValueFinalized()){
                    sum++;
                }
            }
        }
        return sum;
    }
    public int getNoOfPossibleValues() {
        int sum=0;
        for (int i = 0; i <9 ; i++) {
            for (int j = 0; j < 9; j++) {
                sum=sum+sudokuUnit[i][j].getPossibleValues().size();
            }

        }
        return sum;
    }

    public Set<Integer> getPossibleValues(int X, int Y) {return sudokuUnit[X][Y].getPossibleValues();}
    //public void setNoOfValuesFinalized(int val) { this.noOfValuesFinalized=val;}

    public int[][] getMatrix(){
        int A[][]=new int[9][9];
        for (int i = 0; i <9 ; i++) {
            for (int j = 0; j < 9; j++) {
                if(this.sudokuUnit[i][j].getValueFinalized()){ A[i][j]=this.sudokuUnit[i][j].getFinalValue();}
                else{ A[i][j]=0; }
            }
        }
        return A;
    }

    public boolean performFirstLogic(){
        //Sole candidate method
        //This will return true if additional values were finalized or some possible values were eliminated within this method
        int previousNoOfValuesFinalized = this.getNoOfValuesFinalized();
        int previousNoOfPossibleValues=this.getNoOfPossibleValues();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!sudokuUnit[i][j].getValueFinalized()) {
                    Set<Integer> existingValues = new HashSet<Integer>();
                    //check values in other cells vertical to this cell
                    for (int k = 0; k < 9; k++) {
                        if (sudokuUnit[k][j].getValueFinalized()) {
                            existingValues.add(sudokuUnit[k][j].getFinalValue());
                        }
                    }
                    //check values in other cells horizontal to this cell
                    for (int k = 0; k < 9; k++) {
                        if (sudokuUnit[i][k].getValueFinalized()) {
                            existingValues.add(sudokuUnit[i][k].getFinalValue());
                        }
                    }
                    //check values in other cells of the same box
                    int thisBoxX = i / 3;
                    int thisBoxY = j / 3;
                    for (int k = 0; k < 3; k++) {
                        for (int l = 0; l < 3; l++) {
                            if (sudokuUnit[thisBoxX * 3 + k][thisBoxY * 3 + l].getValueFinalized()) {
                                existingValues.add(sudokuUnit[thisBoxX * 3 + k][thisBoxY * 3 + l].getFinalValue());
                            }
                        }
                    }
                    int possibleValueSize=sudokuUnit[i][j].removeTheseFromPossibleValues(existingValues);
                    if (possibleValueSize==0) noSolutionAvailableForThis=true;
                    //if (possibleValueSize==1) noOfValuesFinalized++;

                }
            }
        }
        if (this.getNoOfValuesFinalized() > previousNoOfValuesFinalized|| this.getNoOfPossibleValues() < previousNoOfPossibleValues) return true;
        else return false;
    }

    public boolean performUniqueCandidate(){
        //Unique candidate method
        //This will return true if additional values were finalized within this method
        Log.d(LOG_TAG, "Before Unique candidate");
//        this.printSudoku();
//        this.printPossibleValues();
        int previousNoOfValuesFinalized = this.getNoOfValuesFinalized();
        int previousNoOfPossibleValues=this.getNoOfPossibleValues();
        //For each column
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!sudokuUnit[i][j].getValueFinalized()) {
                    //Set<Integer> existingValues = new HashSet<Integer>();

                    for (int val = 1; val < 10; val++) {
                        boolean flag = true;
                        for (int k = 0; k < 9; k++) {       //check column
                            if (k != i && sudokuUnit[k][j].containsPossibleValue(val)) {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            sudokuUnit[i][j].setFinalValue(val);
                            //noOfValuesFinalized++;
                            break;
                        }
                        flag = true;
                        for (int k = 0; k < 9; k++) {       //check row
                            if (k != j && sudokuUnit[i][k].containsPossibleValue(val)) {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            sudokuUnit[i][j].setFinalValue(val);
                            //noOfValuesFinalized++;
                            break;
                        }
                        ///check box
                        flag = true;
                        int thisBoxX = i / 3;
                        int thisBoxY = j / 3;
                        for (int k = 0; k < 3; k++) {
                            for (int l = 0; l < 3; l++) {
                                //if(i!=thisBoxX*3+k)
                                if ((i != thisBoxX * 3 + k || j != thisBoxY*3 + l) && sudokuUnit[thisBoxX * 3 + k][thisBoxY * 3 + l].containsPossibleValue(val)) {
                                    flag = false;
                                    break;
                                }
                            }
                            if (!flag) break;
                        }
                        if (flag) {
                            sudokuUnit[i][j].setFinalValue(val);
                            //noOfValuesFinalized++;
                            break;
                        }
                    }

                }
            }
        }
        if (this.getNoOfValuesFinalized() > previousNoOfValuesFinalized|| this.getNoOfPossibleValues() < previousNoOfPossibleValues) return true;
        else return false;
    }

    public boolean performBlockAndColumnRowInteraction() {
        /*this.printSudoku();
        this.printPossibleValues();*/
        int previousNoOfValuesFinalized = this.getNoOfValuesFinalized();
        int previousNoOfPossibleValues=this.getNoOfPossibleValues();
        for (int i = 0; i < 3; i++) {       //Block X axis
            for (int j = 0; j < 3; j++) {      // Block Y axis
                for (int val = 1; val < 10; val++) {   //check for each value
                    Integer[] rowList = containsPossibleValInBlockRow(val, i, j).toArray(new Integer[0]);
                    Integer[] colList = containsPossibleValInBlockColumn(val, i, j).toArray(new Integer[0]);
                    if (rowList.length == 1) {
                        eliminateValFromEntireRowExceptBlock(val, i, j, rowList[0]);
                    }
                    if (colList.length == 1) {
                        eliminateValFromEntireColumnExceptBlock(val, i, j, colList[0]);
                    }
                }
            }
        }
        if (this.getNoOfValuesFinalized() > previousNoOfValuesFinalized|| this.getNoOfPossibleValues() < previousNoOfPossibleValues) return true;
        else return false;
    }

    public void eliminateValFromEntireRowExceptBlock(int val,int BoxX,int BoxY, int rowNo){
        for (int j = 0; j <9 ; j++) {
            if(j/3 !=BoxY && !sudokuUnit[BoxX*3+rowNo][j].getValueFinalized() &&sudokuUnit[BoxX*3+rowNo][j].containsPossibleValue(val)) {
                Set<Integer> arr = new HashSet<Integer>();
                arr.add(val);
                int possibleValueSize = sudokuUnit[BoxX * 3 + rowNo][j].removeTheseFromPossibleValues(arr);
                /*if (possibleValueSize == 1) {
                    noOfValuesFinalized++;
                }*/
            }
        }
    }
    public void eliminateValFromEntireColumnExceptBlock(int val,int BoxX,int BoxY, int colNo){
        Set<Integer> arr = new HashSet<Integer>();
        arr.add(val);
        for (int i = 0; i <9 ; i++) {

            if(i/3 !=BoxX && !sudokuUnit[i][BoxY*3+colNo].getValueFinalized() && sudokuUnit[i][BoxY*3+colNo].containsPossibleValue(val)) {

                int possibleValueSize = sudokuUnit[i][BoxY * 3 + colNo].removeTheseFromPossibleValues(arr);
                //if (possibleValueSize == 1) { noOfValuesFinalized++; }
            }
        }
    }
    public Set<Integer> containsPossibleValInBlockRow(int val, int BlockX, int BlockY){
        Set<Integer> rowsContainingThisVal = new HashSet<Integer>();
        for (int RowNo = 0; RowNo < 3; RowNo++) {
            boolean flag =false;
            for (int i = 0; i <3 ; i++) {  //column no
                if(!sudokuUnit[BlockX*3+RowNo][BlockY*3 + i].getValueFinalized() && sudokuUnit[BlockX*3+RowNo][BlockY*3 + i].getPossibleValues().contains(val)){
                    flag=true;
                    break;
                }
            }
            if(flag){rowsContainingThisVal.add(RowNo);}
        }

        return rowsContainingThisVal;
    }
    public Set<Integer> containsPossibleValInBlockColumn(int val, int BlockX, int BlockY){
        Set<Integer> columnsContainingThisVal = new HashSet<Integer>();
        for (int colNo = 0; colNo < 3; colNo++) {
            boolean flag =false;
            for (int j = 0; j <3 ; j++) {  //row no
                if(!sudokuUnit[BlockX*3+j][BlockY*3 + colNo].getValueFinalized() && sudokuUnit[BlockX*3+j][BlockY*3 + colNo].getPossibleValues().contains(val)){
                    flag=true;
                    break;
                }
            }
            if(flag){columnsContainingThisVal.add(colNo);}
        }

        return columnsContainingThisVal;
    }

    public boolean performBlockBlockInteraction(){
        int previousNoOfValuesFinalized = this.getNoOfValuesFinalized();
        int previousNoOfPossibleValues=this.getNoOfPossibleValues();
        this.printSudoku();
        this.printPossibleValues();
        ///Blocks in a row
        for (int blockX = 0; blockX < 3; blockX++) {
            for (int blockY = 0; blockY < 3; blockY++) {
                for (int val = 1; val <10 ; val++) {
                    Integer[] rowList1 = containsPossibleValInBlockRow(val, blockX, blockY).toArray(new Integer[0]);
                    for (int colNo = 0; colNo < 3; colNo++) {
                        Integer[] rowList2 = containsPossibleValInBlockRow(val, blockX, colNo).toArray(new Integer[0]);
                        if(blockY!=colNo && rowList1.length==2 && Arrays.equals(rowList1,rowList2)) {
                            eliminateValFromAllRowsExceptOwnTwoBlocks(val,blockX,blockY,colNo,rowList1);
                        }
                    }
                }
            }
        }
        //Blocks in a column
        for (int blockY = 0; blockY < 3; blockY++) {
            for (int blockX = 0; blockX < 3; blockX++) {
                for (int val = 1; val <10 ; val++) {
                    Integer[] colList1 = containsPossibleValInBlockColumn(val, blockX, blockY).toArray(new Integer[0]);
                    for (int rowNo = 0; rowNo < 3; rowNo++) {
                        Integer[] colList2 = containsPossibleValInBlockColumn(val, rowNo, blockY).toArray(new Integer[0]);
                        if(blockX!=rowNo && colList1.length==2 && Arrays.equals(colList1, colList2)) {
                            eliminateValFromAllColumnsExceptOwnTwoBlocks(val,blockX,rowNo,blockY,colList1);
                        }
                    }
                }
            }
        }
        if (this.getNoOfValuesFinalized() > previousNoOfValuesFinalized|| this.getNoOfPossibleValues() < previousNoOfPossibleValues) return true;
        else return false;
    }

    private void eliminateValFromAllRowsExceptOwnTwoBlocks(int val,int blockX,int blockY1,int blockY2,Integer[] rowList){
        for (int colNo = 0; colNo < 3; colNo++) {
            if(colNo!=blockY1 && colNo != blockY2){
                for (int i = 0; i < rowList.length; i++) {
                    for (int j = 0; j < 3; j++) {
                        if(!sudokuUnit[blockX*3+rowList[i]][colNo*3+j].getValueFinalized() && sudokuUnit[blockX*3+rowList[i]][colNo*3+j].containsPossibleValue(val)){
                            Set<Integer> arr = new HashSet<Integer>();
                            arr.add(val);
                            int possibleValueSize = sudokuUnit[blockX*3+rowList[i]][colNo*3+j].removeTheseFromPossibleValues(arr);
                        }
                    }
                }
            }
        }
    }
    private void eliminateValFromAllColumnsExceptOwnTwoBlocks(int val,int blockX1,int blockX2,int blockY,Integer[] colList){
        for (int rowNo = 0; rowNo < 3; rowNo++) {
            if(rowNo!=blockX1 && rowNo != blockX2){
                for (int j = 0; j < colList.length; j++) {
                    for (int i = 0; i < 3; i++) {
                        if(!sudokuUnit[rowNo*3+i][blockY*3+colList[j]].getValueFinalized() && sudokuUnit[rowNo*3+i][blockY*3+colList[j]].containsPossibleValue(val)){
                            Set<Integer> arr = new HashSet<Integer>();
                            arr.add(val);
                            int possibleValueSize = sudokuUnit[rowNo*3+i][blockY*3+colList[j]].removeTheseFromPossibleValues(arr);
                        }
                    }
                }
            }
        }
    }
    public boolean performSecondLogic(){
        // Naked paid method
        //returns true if any new value is finalized within this method
        int previousNoOfValuesFinalized = this.getNoOfValuesFinalized();
        int previousNoOfPossibleValues=this.getNoOfPossibleValues();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!sudokuUnit[i][j].getValueFinalized() && sudokuUnit[i][j].getNoOfPossibleValues() == 2) {
                    //Check entire row
                    for (int k = 0; k < 9; k++) {
                        if (k != j && !sudokuUnit[i][k].getValueFinalized() && sudokuUnit[i][k].getNoOfPossibleValues() == 2) {
                            if (sudokuUnit[i][j].getPossibleValues().equals(sudokuUnit[i][k].getPossibleValues())) {
                                //if naked pair is found, eliminate these two values from all other possible values in this row
                                Set<Integer> existingValues = new HashSet<Integer>();
                                existingValues = sudokuUnit[i][j].getPossibleValues();
                                for (int l = 0; l < 9; l++) {
                                    if (l != j && l != k && !sudokuUnit[i][l].getValueFinalized()) {      //cannot eliminate values from the cells where naked pair were found
                                        //sudokuUnit[i][l].removeTheseFromPossibleValues(existingValues);
                                        int possibleValueSize=sudokuUnit[i][l].removeTheseFromPossibleValues(existingValues);
                                        if (possibleValueSize==0) noSolutionAvailableForThis=true;
                                        //if (possibleValueSize==1) noOfValuesFinalized++;
                                    }
                                }
                            }
                        }
                    }
                    //Check entire column
                    for (int k = 0; k < 9; k++) {
                        if (k != i && !sudokuUnit[k][j].getValueFinalized() && sudokuUnit[k][j].getNoOfPossibleValues() == 2) {
                            if (sudokuUnit[i][j].getPossibleValues().equals(sudokuUnit[k][j].getPossibleValues())) {
                                //if naked pair is found, eliminate these two values from all other possible values in this column
                                Set<Integer> existingValues = new HashSet<Integer>();
                                existingValues = sudokuUnit[i][j].getPossibleValues();
                                for (int l = 0; l < 9; l++) {
                                    if (l != i && l != k && !sudokuUnit[l][j].getValueFinalized()) {      //cannot eliminate values from the cells were naked pair were found
                                        //sudokuUnit[i][l].removeTheseFromPossibleValues(existingValues);
                                        int possibleValueSize=sudokuUnit[l][j].removeTheseFromPossibleValues(existingValues);
                                        if (possibleValueSize==0) noSolutionAvailableForThis=true;
                                        //if (possibleValueSize==1) noOfValuesFinalized++;
                                    }
                                }
                            }
                        }
                    }
                    //Check entire Box
                    int thisBoxX = i / 3;
                    int thisBoxY = j / 3;
                    for (int k = 0; k < 3; k++) {
                        for (int l = 0; l < 3; l++) {
                            if ((3 * thisBoxX + k != i || 3 * thisBoxY + l != j) && !sudokuUnit[3 * thisBoxX + k][3 * thisBoxY + l].getValueFinalized() && sudokuUnit[3 * thisBoxX + k][3 * thisBoxY + l].getNoOfPossibleValues() == 2) {
                                if (sudokuUnit[i][j].getPossibleValues().equals(sudokuUnit[3 * thisBoxX + k][3 * thisBoxY + l].getPossibleValues())) {
                                    //if naked pair is found, eliminate these two values from all other possible values in this Box
                                    Set<Integer> existingValues = new HashSet<Integer>();
                                    existingValues = sudokuUnit[i][j].getPossibleValues();
                                    for (int m = 0; m < 3; m++) {
                                        for (int n = 0; n < 3; n++) {
                                            if ((m != k || n != l) && (3 * thisBoxX + m != i || 3 * thisBoxY + n != j) && !sudokuUnit[3 * thisBoxX + m][3 * thisBoxY + n].getValueFinalized()) { //cannot eliminate values from the cells were naked pair were found
                                                int possibleValueSize=sudokuUnit[3 * thisBoxX + m][3 * thisBoxY + n].removeTheseFromPossibleValues(existingValues);
                                                //sudokuUnit[i][l].removeTheseFromPossibleValues(existingValues);
                                                //int possibleValueSize=sudokuUnit[i][j].removeTheseFromPossibleValues(existingValues);
                                                if (possibleValueSize==0) noSolutionAvailableForThis=true;
                                                //if (possibleValueSize==1) noOfValuesFinalized++;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                //if (sudokuUnit[i][j].getValueFinalized()) A[i][j] = sudokuUnit[i][j].getFinalValue();
            }
        }
        if (this.getNoOfValuesFinalized() > previousNoOfValuesFinalized|| this.getNoOfPossibleValues() < previousNoOfPossibleValues) return true;
        else return false;
    }


    public boolean solveGridWithAllLogics() {
        do {
            do {
                do {
                    do {
                        while (this.performFirstLogic()) {
                            //while (this.performBlockBlockInteraction());
                        } //perform first logic until no more values are finalized
                    } while (this.performUniqueCandidate());
                } while (this.performBlockAndColumnRowInteraction());
            } while (this.performBlockBlockInteraction());
        } while(this.performSecondLogic());
        if (this.getNoOfValuesFinalized() == 81) {
            return true;
        } else return false;
    }


    public boolean makeOneGuess(int i, int j, int val){
        if(this.sudokuUnit[i][j].getValueFinalized()){
            return false;   // no use of guessing if value is already finalized for this cell
        }
        else {
            this.sudokuUnit[i][j].setFinalValue(val);
            //noOfValuesFinalized++;
            return this.solveGridWithAllLogics();
        }
    }

    public boolean makeTwoGuesses(int i, int j, int val, int l, int m, int val2){
        if(this.sudokuUnit[i][j].getValueFinalized() && this.sudokuUnit[l][m].getValueFinalized()){
            return false;   // no use of guessing if both values are already finalized
        }
        else {
            this.sudokuUnit[i][j].setFinalValue(val);
            //noOfValuesFinalized++;
            this.sudokuUnit[l][m].setFinalValue(val2);
            return this.solveGridWithAllLogics();
        }
    }

    public void printSudoku(){
        String str = "";
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                //System.out.print(Sudoku[x][y] + "|");
                if (!this.sudokuUnit[x][y].getValueFinalized()) {
                    str = str + " " + "|";
                } else {
                    str = str + this.sudokuUnit[x][y].getFinalValue() + "|";
                }
            }
            str = str + '\n';
        }
        Log.d(LOG_TAG, str);
    }

    public void printPossibleValues(){
        String str = "";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.sudokuUnit[i][j].getValueFinalized()) {
                    str = str + this.sudokuUnit[i][j].getFinalValue() + "        ";
                } else {
                    int count=9-this.sudokuUnit[i][j].getNoOfPossibleValues();
                    for (Integer integer : this.sudokuUnit[i][j].getPossibleValues()) {
                        str = str + integer;
                    }
                    for (int k = 0; k < count; k++) {
                        str=str+' ';
                    }
                }
                str = str + "|";
            }
            str = str + '\n';
        }
        Log.d(LOG_TAG, str);
    }


}
