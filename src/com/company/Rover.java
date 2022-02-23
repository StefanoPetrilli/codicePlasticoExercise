package com.company;

import static com.company.Constants.DIMENSION;
import static com.company.Constants.moduleDimension;

/**
 *Class that represent a rover.
 */
public class Rover {

    private Direction direction;
    private int x, y;
    private final Mars m;

    /**
     * Class constructor
     *
     * @param direction the direction the rover is facing when is instantiated
     * @param x the position of the rover on the x-axis
     * @param y the position of the rover on the y-axis
     * @throws Exception the exception is thrown if the drone is instantiated in an
     *                   illegal position, namely in a coordinate where an obstacle is present.
     */
    public Rover(Direction direction, int x, int y) throws Exception {

        x = moduleDimension(x);
        y = moduleDimension(y);
        m = Mars.getMars();

        //If the position does not contain an obstacle instantiate the object
        if (!Mars.getMars().getState(x, y)) {

            this.direction = direction;
            this.x = x;
            this.y = y;
            m.setState(x, y, true); //Now the space is occupied by the rover

        } else { //If the position contains an obstacle throw an exception

            throw new Exception("The coordinates are illegal. In this spot there is an obstacle.");

        }

    }

    /**
     * Function that returns true if and only if the input command
     * contains only legal character
     *
     * @param command the command that the rover has to execute
     * @return true: if contains illegal characters
     *         false: if it contains only legal characters
     */
    public static boolean isInvalid(String command) {

        return !command.replaceAll("[fblr]", "").isEmpty();

    }

    public Direction getDirection(){
        return direction;
    }

    private int getX() {
        return x;
    }

    private int getY() {
        return y;
    }

    private void setX(int x) {
        this.x = moduleDimension(x);
    }

    private void setY(int y) {
        this.y = moduleDimension(y);
    }

    private void rotateRight(){
        switch (getDirection()) {
            case NORTH:
                this.direction = Direction.EAST;
                break;
            case EAST:
                this.direction = Direction.SOUTH;
                break;
            case SOUTH:
                this.direction = Direction.WEST;
                break;
            default:
                this.direction = Direction.NORTH;
        }
    }

    private void rotateLeft(){
        switch (getDirection()) {
            case NORTH:
                this.direction = Direction.WEST;
                break;
            case EAST:
                this.direction = Direction.NORTH;
                break;
            case SOUTH:
                this.direction = Direction.EAST;
                break;
            default:
                this.direction = Direction.SOUTH;
        }
    }

    /**
     * Function that implement the moving of the rover
     *
     * @param isForward true: if the rover is moving forward
     *                  false: if the rover is moving backward
     * @return true: if the rover successfully moved
     *         false: if the rover encountered an obstacle
     */
    private boolean move(boolean isForward) throws Exception {
        /*
        *Based on the instruction that the rover execute and
        *based on the direction we calculate the offset on the
        *x-axis or in the y-axis.
        */
        int xDiff = 0, yDiff = 0;

        /*
        * If the rover is moving backward we have to multiply the
        * offset by -1.
         */
        int multiplier = -1;
        if (isForward) multiplier = 1;

        //Actually calculate the x/y offset based on the facing direction.
        switch (getDirection()) {
            case NORTH:
                xDiff = -1;
                break;
            case EAST:
                yDiff = 1;
                break;
            case SOUTH:
                xDiff = 1;
                break;
            default:
                yDiff = -1;
        }

        xDiff *= multiplier;
        yDiff *= multiplier;

        //The rover can go in the next coordinate
        if (!m.getState(getX() + xDiff, getY() + yDiff)) {
            m.setState(getX(), getY(), false); //As the rover move the previous coordinate is now empty
            m.setState(getX() + xDiff, getY() +yDiff, true); //The new position is now occupied
            System.out.println("The rover succesfully moved from " +
                    getX() +
                    ", " +
                    getY() +
                    " to " +
                    (getX() + xDiff) +
                    ", " +
                    (getY() + yDiff));
            setX(getX() + xDiff);
            setY(getY() + yDiff);

        } else { //The rover found an obstacle

            throw new Exception("The rover found an obstacle in the position: " +
                    (getX() + xDiff) +
                    " " +
                    (getY() + yDiff) +
                    ". Will no move further.");

        }
        return true;
    }

    /**
     * Function to execute the command
     *
     * @param commands is the String of command the rover receives
     * @throws Exception in the case the commands contains illegal characters or is empty
     */
    public void execute(String commands) throws Exception {

        if (isInvalid(commands)) throw new Exception("The command contains illegal characters.");
        //Split the string in a sequence of commands
        String[] commandArray = commands.split("");

        //Execute one by one the commands
        for (String s : commandArray) {
            switch (s) {
                case "f":
                    if(!this.move(true)) return;
                    break;
                case "b":
                    if (!this.move(false)) return;
                    break;
                case "l":
                    this.rotateLeft();
                    break;
                default: //case "r"
                    this.rotateRight();
            }
        }

    }

}
