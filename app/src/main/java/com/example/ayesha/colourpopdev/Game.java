package com.example.ayesha.colourpopdev;

import android.content.Context;
import android.util.Log;

import com.example.ayesha.colourpopdev.util.GridBuild;
import com.example.ayesha.colourpopdev.util.PreviewGrid;
import com.example.ayesha.colourpopdev.views.grid.Cell;

/**
 * Created by Ayesha on 16/04/2018.
 */
public class Game {
    private static Game instance;

    public static final int bombTotal = 10;
    /*set number of buttons on grid*/
    public static final int xDimension = 10;
    public static final int yDimension = 10;


    private Context context;
    private Cell[][] mineGrid = new Cell[xDimension][yDimension];

    public static Game getInstance(){
        if( instance == null) instance = new Game();
        return instance;
    }

    private Game(){}

    public void createGrid(Context context) {
        /*checker*/
        Log.e("Game", "createGrid is working");
        this.context = context;

        /*Building grid for this Game instance using GridBuild*/
        int[][] gameGrid = GridBuild.generate(bombTotal, xDimension, yDimension);
        PreviewGrid.print(gameGrid, xDimension, yDimension);
        setGrid(context, gameGrid);
    }

    private void setGrid(final Context context, final int[][] grid){
        for(int x = 0; x < xDimension; x++){
            for(int y = 0; y < yDimension; y++){
                if (mineGrid[x][y] == null){
                    mineGrid[x][y] = new Cell(context,y * yDimension + x);
                }
                mineGrid[x][y].setValue(grid[x][y]);
                mineGrid[x][y].invalidate();
            }
        }


    }

    public Cell getCellAt(int position){
        int x = position % xDimension;
        int y = position / yDimension;


        return mineGrid[x][y];
    }
    public Cell getCellAt(int x, int y){
        return mineGrid[x][y];
    }
    /*handles all button clicks + game response*/

    public void click(int x, int y){
        if(x >=0 && x < xDimension && y >= 0 && y < yDimension && !getCellAt(x,y).isClicked()) {
            getCellAt(x,y).setClicked();
            if(getCellAt(x,y).getValue() == 0){
                for(int xt = -1; xt <= 1; xt++){
                    for(int yt =-1; yt <= 1; yt++){
                        if(xt != yt) {click(x+xt, y+yt);}
                    }
                }
            }
            if(getCellAt(x,y).isMine()){
                onGameLost();
            }
        }


        checkEndGame();


    }

    public boolean checkEndGame() {

        int bombNotFound = bombTotal;
        int notRevealed = yDimension * xDimension;

        for (int x = 0; x < xDimension; x++) {
            for (int y = 0; y < yDimension; y++) {
                if (getCellAt(x, y).isRevealed() || getCellAt(x, y).isFlagged()) {
                    notRevealed--;
                }

                if (getCellAt(x, y).isFlagged() && getCellAt(x, y).isMine()) {
                    bombNotFound--;
                }
            }

            if (bombNotFound == 0 && notRevealed ==0) {            }
                Snackbar snackbar = Snackbar.make(instance, "Game Won! Congratulations! ", Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
            return false;

        }




    public void flag(int x, int y){
        boolean isFlagged = getCellAt(x,y).isFlagged();
        getCellAt(x,y).setFlagged(!isFlagged);
        getCellAt(x,y).invalidate();

    }
    /*handles losing game*/
    private void onGameLost(){

    }
}


