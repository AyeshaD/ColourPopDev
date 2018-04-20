package com.example.ayesha.colourpopdev.views.grid;

import android.content.Context;
import android.view.View;

import com.example.ayesha.colourpopdev.Game;

/**
 * Created by Ayesha on 16/04/2018.
 */
public  class BaseCell extends View {


    private int value;
    private boolean isMine;
    private boolean isRevealed;
    private boolean isClicked;
    private boolean isFlagged;

    private int x, y;
    private int position;

    public BaseCell(Context context) {
        super(context);

    }


    public int getXPos() {
        return x;
    }
    public int getYPos() {
        return y;
    }
    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
        x = position % Game.xDimension;
        y = position / Game.yDimension;
        invalidate();
    }
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        isMine = false;
        isRevealed = false;
        isClicked = false;
        isFlagged = false;

        if(value == -1) isMine=true;

        this.value = value;
    }


    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked() {
        isClicked = true;
        this.isRevealed = true;
        invalidate();
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }
}
