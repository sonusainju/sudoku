package com.example.android.sudoku.view.sudokuGrid;

//import com.example.android.sudoku.view.SudokuGridViewAdapter;
//import com.example.android.sudoku.GameEngine;
import com.example.android.sudoku.GameEngine;
import com.example.android.sudoku.MainActivity;
import com.example.android.sudoku.R;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

import static com.example.android.sudoku.R.layout.cell;

public class SudokuGridView extends GridView{

    private Context context;

    public SudokuGridView(Context context , AttributeSet attrs) {
        super(context,attrs);

        this.context = context;

        SudokuGridViewAdapter gridViewAdapter = new SudokuGridViewAdapter(context);

        setAdapter(gridViewAdapter);

        setOnItemClickListener(new OnItemClickListener() {

           @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int x = position % 9;
                int y = position / 9;

                GameEngine.getInstance().setSelectedPosition(x, y);
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    class SudokuGridViewAdapter extends BaseAdapter{

        private Context context;

        public SudokuGridViewAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return 81;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return GameEngine.getInstance().getGrid().getItem(position);
        }
    }
}