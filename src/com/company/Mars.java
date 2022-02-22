package com.company;

import static com.company.Constants.DIMENSION;
import static com.company.Constants.OBSTACLE_PERCENT;
import java.util.Random;

/*
This class is used to represent the map.
The map is a 100x100 2D boolean array.
When a cell is set to True there is an obstacle
in that coordinate.
 */

public class Mars {

    private static Mars mars = new Mars();
    private boolean[][] map = new boolean[DIMENSION][DIMENSION];

    private Mars() {
        Random randGen = new Random();
        for (int i = 0; i < DIMENSION; i++)
            for (int j = 0; j < DIMENSION; j++)
                map[i][j] = (randGen.nextFloat() < OBSTACLE_PERCENT);
    }

    public static Mars getMars() {
        return mars;
    }

    public void setState(int x, int y, boolean state) {
        map[x % DIMENSION][y % DIMENSION] = state;
    }

    public boolean getState(int x, int y) {
        return map[x % DIMENSION][y % DIMENSION];
    }

}
