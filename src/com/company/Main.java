package com.company;

/*
You’re part of the team that explores Mars by sending remotely controlled
vehicles to the surface of the planet. Develop an API that translates the
commands sent from earth to instructions that are understood by the rover.

Requirements

• You are given the initial starting point (x,y) of a rover and the direction (N,S,E,W) it is facing.
• The rover receives a character array of commands.
• Implement commands that move the rover forward/backward (f,b).
• Implement commands that turn the rover left/right (l,r).
• Implement wrapping from one edge of the grid to another. (planets are spheres after all)
• Implement obstacle detection before each move to a new square.
  If a given sequence of commands encounters an obstacle, the rover moves
  up to the last possible point, aborts the sequence and reports the obstacle.
*/

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.fail;

public class Main {

    /**
     * If the input is null an error is thrown
     */
    @Test
    public void nullInput(){
        try {
            Rover r1 = new Rover(Direction.NORTH, 10, 10);
            r1.execute(null);

        } catch (Exception e) {
            return;
        }
        fail("No exception thrown");
    }

    /**
     * Contains illegal character
     */
    @Test
    public void illegalInput(){
        try {
            Rover r1 = new Rover(Direction.NORTH, 10, 10);
            r1.execute("eee");
        } catch (Exception e) {
            return;
        }
        fail("No exception thrown");
    }

    /**
     * If the rover keeps moving will find an obstacle
     */
    @Test
    public void obstacleEncounter(){
        try {
            Rover r1 = new Rover(Direction.NORTH, 10, 10);
            for (int i = 0; i < 100000; i++) {
                r1.execute("frffl");
            }
        } catch (Exception e) {
            return;
        }
        fail("No obstacle encounteres, very unlikely!");
    }

    /**
     * If the rover rotate 4 times to left and 4 to right
     * in in the ned will be in the initial direction.
     */
    @Test
    public void illegalRotation(){
        try {
            Rover r1 = new Rover(Direction.NORTH, 10, 10);
            r1.execute("rrrrllll");
            assertEquals(r1.getDirection(), Direction.NORTH);
        } catch (Exception e) {
            return;
        }
        fail("No exception thrown");
    }

    /**
     * If a rover tries to move on a location where there is another rover an
     * error is thrown.
     */
    @Test
    public void occupiedPosition(){
        try {
            Rover r1 = new Rover(Direction.NORTH, 10, 10);
            Rover r2 = new Rover(Direction.NORTH, 9, 10);
            r1.execute("f");
        } catch (Exception e) {
            return;
        }
        fail("No exception thrown");
    }


    public static void main(String[] args) {
        try {
            Rover r1 = new Rover(Direction.NORTH, 10, 10);
            while (true) r1.execute("f");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
