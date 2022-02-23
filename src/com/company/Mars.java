package com.company;

import static com.company.Constants.DIMENSION;
import static com.company.Constants.moduleDimension;

import java.util.Random;

/**
 * This class is used to represent the map.
 * The map is a square 2D boolean array.
 * When a cell is set to True there is an obstacle
 * in that coordinate.
 * It uses a singleton design pattern because there is just one map of
 * Mars hence we don't want to instantiate more but always use the same.
 */
public class Mars {

    private static final Mars mars = new Mars(); //The singleton
    private final boolean[][] map = new boolean[DIMENSION][DIMENSION];
    private static final float OBSTACLE_PERCENT = 0.05F; //Percentage of obstacles in the map

    /**
     * Builder that initialize the map.
     */
    private Mars() {
        Random randGen = new Random();
        for (int i = 0; i < DIMENSION; i++)
            for (int j = 0; j < DIMENSION; j++)
                map[i][j] = (randGen.nextFloat() < OBSTACLE_PERCENT);

    }

    /**
     *
     * @return the singleton
     */
    public static Mars getMars() {
        return mars;
    }

    /**
     * Given a coordinate and a state it sets the state of that coordinate
     * @param x coordinate on x-axis
     * @param y coordinate on y-axis
     * @param state ture: if there is an obstacle
     *              false: if there is no obstacle
     */
    public void setState(int x, int y, boolean state) {
        map[moduleDimension(x)][moduleDimension(y)] = state;
    }

    /**
     * Givan a coordinate it tells if in the coordinate there is an obstacle
     *
     * @param x coordinate on x-axis
     * @param y coordinate on y-axis
     * @return true: if there is an obstacle
     *         false: if there is no obstacle
     */
    public boolean getState(int x, int y) {
        return map[moduleDimension(x)][moduleDimension(y)];
    }

}
