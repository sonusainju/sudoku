package com.example.android.sudoku.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import static android.R.attr.width;
import static android.graphics.Color.BLACK;

/**
 * Created by sonu's on 2018-02-13.
 */

public class sudokuLayout extends View {
    public static final int BOLD_WIDTH = 8;
    private Paint mBoldLine;
    private float top, bottom, left, right;
    private float rectDimens;
    private float unitDim;


    public sudokuLayout(Context context, AttributeSet attr)
    {
        super (context,attr);
        mBoldLine = new Paint();
        mBoldLine.setColor(BLACK);
        mBoldLine.setStrokeWidth(BOLD_WIDTH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // get dimensions for sudoku grid
        rectDimens = (float) (this.getWidth());
        top = 0;
        left = 0;
        bottom = top + rectDimens;
        right = left + rectDimens;
        unitDim = rectDimens / 9;

        // draw lines
        drawBoldLines(canvas);

    }

     public void drawBoldLines(Canvas canvas){
        // Horizontal and vertical lines
         for (int i = 1; i <= 2; i++)
         {
             canvas.drawLine(left, 3*i*unitDim, right, 3*i*unitDim , mBoldLine);
             canvas.drawLine(3*i*unitDim, top, 3*i*unitDim , bottom, mBoldLine);
         }
    }
}
