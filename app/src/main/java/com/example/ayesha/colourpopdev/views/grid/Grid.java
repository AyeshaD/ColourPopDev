package com.example.ayesha.colourpopdev.views.grid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.example.ayesha.colourpopdev.Game;

/**
 * Created by Ayesha on 16/04/2018.
 */
public class Grid extends GridView {

    public Grid(Context context, AttributeSet attrs){
        super(context, attrs);

        Game.getInstance().createGrid(context);

        setNumColumns(Game.xDimension);
        setAdapter(new GridAdapter());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    private class GridAdapter extends BaseAdapter{
        @Override
        public int getCount(){
            return Game.getInstance().xDimension * Game.getInstance().yDimension;
        }

        @Override
        public Object getItem(int position){
            return null;
        }

        @Override
        public long getItemId(int position){
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            return Game.getInstance().getCellAt(position);
        }

    }
}
