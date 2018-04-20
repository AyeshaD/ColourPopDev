package com.example.ayesha.colourpopdev.views.grid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import com.example.ayesha.colourpopdev.Game;
import com.example.ayesha.colourpopdev.R;

/**
 * Created by Ayesha on 16/04/2018.
 */
public class Cell extends BaseCell implements View.OnClickListener {

    public Cell(Context context, int position) {

        super(context);
        setPosition(position);
        setOnClickListener(this);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int HeightMeasureSpec){
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    public void onClick(View v){
        Game.getInstance().click(getXPos(), getYPos());
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("ColourPop!", "Cell::onDraw");
        drawButton(canvas);

        /* checks if neighbours are flagged on click, if not, runs standard process*/
        if(isFlagged()){
            drawFlag(canvas);
        }
        else if(isRevealed() && isMine() && !isClicked()){
            drawMine(canvas);
        }
        else{
            if (isClicked()) {
                if(getValue() == -1){
                    drawPop(canvas);
                }
                else drawNumber(canvas);
            }
            else drawButton(canvas);
        }
    }

    private void drawFlag (Canvas canvas){
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.flag);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }
    private void drawButton(Canvas canvas){
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.circle);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }
    private void drawMine(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.bomb);
        drawable.setBounds(0, 0, getWidth(), getHeight());
        drawable.draw(canvas);
    }
    private void drawPop(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.pop);
        drawable.setBounds(0, 0, getWidth(), getHeight());
        drawable.draw(canvas);
    }

    private void drawNumber(Canvas canvas){
        Drawable drawable = null;
        switch (getValue()){
            case 0:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_0);
                break;
            case 1:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_1);
                break;
            case 2:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_2);
                break;
            case 3:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_3);
                break;
            case 4:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_4);
                break;
            case 5:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_5);
                break;
            case 6:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_6);
                break;
            case 7:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_7);
                break;
            case 8:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_8);
                break;
        }
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }


}
