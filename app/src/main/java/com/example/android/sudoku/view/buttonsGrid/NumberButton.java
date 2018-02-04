package com.example.android.sudoku.view.buttonsGrid;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.example.android.sudoku.GameEngine;

import static android.R.attr.onClick;
import static android.os.Build.VERSION_CODES.O;

/**
 * Created by sonu's on 2018-01-28.
 */

public class NumberButton extends AppCompatButton implements View.OnClickListener{

    private int number;

    public NumberButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        GameEngine.getInstance().setNumber(number);
    }
    public void setNumber(int number) {
        this.number = number;
    }
}
