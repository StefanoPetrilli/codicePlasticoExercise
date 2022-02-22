package com.company;

public class Rover {

    private Direction direction;
    private int x, y;
    private Mars m;

    public Rover(Direction direction, int x, int y) throws Exception {

        x %= Constants.DIMENSION;
        y %= Constants.DIMENSION;
        m = Mars.getMars();

        if (!Mars.getMars().getState(x, y)) {
            this.direction = direction;
            this.x = x;
            this.y = y;
            m.setState(x, y, true); //Now the space is occupied by the rover
        } else {
            throw new Exception("The coordinates are illegal. In this spot there is an obstacle.");
        }

    }

    /**
     * Function that returns true if and only if the input command
     * matches the regex string.
     *
     * @param command
     * @return
     */
    public static boolean isInvalid(String command) {
        return command.matches("fblr");
    }

    private Direction getDirection(){
        return direction;
    }

    private int getX() {
        return x;
    }

    private int getY() {
        return y;
    }

    private void setX(int x) {
        this.x = x;
    }

    private void setY(int y) {
        this.y = y;
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
                this.direction = Direction.SOUTH;
                break;
            default:
                this.direction = Direction.EAST;
        }
    }

    private void changePos(int oldX, int oldY, int newX, int newY) {
        if (!m.getState(getX(), getY() + 1)) {
            m.setState(getX(), getY(), false);
            m.setState(getX(), getY() + 1, true);
            setX(getX() + 1);
            setY(getY() + 1);
        }
    }

    private boolean move(boolean isForward) {
        int xDiff = 0, yDiff = 0;

        int multiplier = -1;
        if (isForward) multiplier = 1;

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
            System.out.println("The rover found an obstacle in the position: " +
                    String.valueOf(getX() + xDiff) +
                    " " +
                    String.valueOf(getY() + yDiff) +
                    ". Will no move further.");
            return false;
        }
        return true;
    }

    public void execute(String commands) throws Exception {

        if (isInvalid(commands)) throw new Exception("The command contains illegal characters.");
        String[] commandArray = commands.split("");

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
