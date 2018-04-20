package com.example.ayesha.colourpopdev.util;

import android.util.Log;

/**
 * Created by Ayesha on 16/04/2018.
 */
public class PreviewGrid {

    public static void print(final int[][] grid, final int width, final int height){
        for(int x = 0; x < width; x++){
            String printedText = "|";
            for(int y = 0; y < height; y++){
                printedText += String.valueOf(grid[x][y]).replace("-1", "B") + "|";
            }

            Log.e("", printedText);

        }
    }
}
