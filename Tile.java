package Project3;

import java.security.InvalidParameterException;

/**
 * Tile class for making Tile Objects. Tiles hold an integer value that is a power of 2, representing a
 * single space on a 2048 board.
 */
public class Tile {

    /**
     * Instance variable value. An integer that holds the value of the board location that the tile represents.
     * Always a power of 2.
     */
    private int value;


    /************Constructors****************/

    /**
     * default constructor. Sets the value of the tile to 2.
     */
    public Tile() {
        this.value = 2;
    }

    /**
     * Constructor that takes a valid value and sets the value of the tile being created to it.
     *
     * @param value Integer representing the value of this newly created tile. Must be a factor of 2.
     */
    public Tile(int value) {

        if (power2(value)) {
            this.value = value;
        } else {
            throw new InvalidParameterException("Invalid value, not a factor of 2");
        }
    }

    /**********************Class Methods**********************/

    /**
     * getValue method returns the integer value of the tile object
     *
     * @return integer value of the tile object
     */
    public int getValue() {
        return this.value;
    }

    /**
     * setValue method takes a valid integer value in as a parameter and sets the value of the tile to it
     *
     * @param value new integer value for the tile. Must be a power of 2.
     */
    public void setValue(int value) {
        if (power2(value)) {
            this.value = value;
        } else {
            throw new InvalidParameterException("Invalid value, not a factor of 2");
        }
    }

    /**
     * toString method prints the value of the tile as a string.
     *
     * @return string representation of the integer value of the tile.
     */
    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    /*******************Private methods*********************/

    /*
    Checks that a number is a valid power of 2 by repeatedly dividing it by 2 through recursion.
     */
    private boolean power2(double value) {

        if (value % 2 != 0 || value < 0) {
            return false;
        }
        if (value / 2 == 1) {
            return true;
        }
        return power2(value / 2);


    }
}