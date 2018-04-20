package com.example.ayesha.colourpopdev.util;

import java.util.Random;

/**
 * Created by Ayesha on 16/04/2018.
 */
public class GridBuild {

    public static int[][] generate(int bombCount, final int width, final int height){
        /*Generate random bomb placement*/
        Random r = new Random();
        /*set up grid*/
        int[][] grid = new int[width][height];
        for(int x = 0; x < width; x++){
            grid[x] = new int[height];
        }
        /*populate grid*/
        while(bombCount > 0){
            int x = r.nextInt(width);
            int y = r.nextInt(height);

            if(grid[x][y] != -1){
                grid[x][y] = -1;
                bombCount--;
            }
        }

        grid = calculateWarning(grid, width, height);
        return grid;
    }

    /*gives number shown in mine-warning display*/
    private static int[][] calculateWarning(int[][] grid, final int width, final int height) {
        for(int x = 0; x < width; x++){
            for(int y =0; y < width; y++){
                grid[x][y] = getNeighbour(grid,x,y,width,height);
            }
        }
        return grid;
    }

    /*adds total bombs in neighbouring cells together for mine-warning display*/
    private static int getNeighbour(final int grid[][], final int x, final int y, final int width, final int height){
        if(grid[x][y] == -1){
            return -1;
        }
        int count = 0;

        /*Uses mineCheck function to ensure neighbouring cells are not off grid*/
        if(mineCheck(grid,x+1,y,width,height)) count++; /*right*/
        if(mineCheck(grid,x+1,y-1,width,height)) count++; /*diagonal up right*/
        if(mineCheck(grid,x+1,y+1,width,height)) count++; /*diagonal down right*/
        if(mineCheck(grid,x-1,y,width,height)) count++; /*left*/
        if(mineCheck(grid,x-1,y-1,width,height)) count++; /*diagonal up left*/
        if(mineCheck(grid,x-1,y+1,width,height)) count++; /*diagonal down left*/
        if(mineCheck(grid,x,y-1,width,height)) count++; /*up*/
        if(mineCheck(grid,x,y+1,width,height)) count++; /*down*/

        return count;

    }

    /*checks if mine exists in cell + checks cell is not off the grid*/
    private static boolean mineCheck(final int [][] grid, final int x, final int y, final int width, final int height){
        if(x >= 0 && y >= 0 && x < width && y < height){
            if(grid[x][y] == -1) {
                return true;
            }
        }
        return false;

    }
}
