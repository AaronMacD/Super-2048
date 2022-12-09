package Project3;

/**
 * Board Class - Creates a linked list of linked list of tiles to represent the board grid. Has functions to
 * retrieve and interact with the tiles holding the numbers on the board.
 */
public class Board {

    /**************************Instance Variables****************************/

    /*
     * A linked list of linked list of tiles holding the numbers for the board.
     */
    private java.util.LinkedList<java.util.LinkedList<Tile>> board = new java.util.LinkedList<java.util.LinkedList<Tile>>();
    /*
     * Integer holding the dimension of the board. Used to define the size of the linked lists.
     */
    private int dimension;


    /**************************Constructors***********************************/

    /**
     * Default constructor. Creates a board object with default dimension 4.
     */
    public Board() {
        new Board(4);
    }

    /**
     * Board constructor that takes a dimension in as a parameter to change the board size
     *
     * @param dim integer dimension for the board size desired.
     */
    public Board(int dim) {
        setDimension(dim);
        setupLinkedLists(this.dimension);
    }


    /***********************Setters/Getters************************************/

    /**
     * setter for the dimension. Used to set the dimension of the board object manually. Throws
     * an illegal argument exception if the dimension is invalid
     *
     * @param dim integer dimension. Must be between 4 and 10, inclusive on both.
     */
    public void setDimension(int dim) {
        if (dim < 11) {
            this.dimension = dim;
        } else {
            throw new IllegalArgumentException("Invalid Dimension");
        }
    }

    /**
     * Getter for dimension. Returns the currently set dimension of the board object.
     *
     * @return integer, the current dimension used in the board object.
     */
    public int getDimension() {
        return this.dimension;
    }


    /*******************************Class Methods*****************************/

    /**
     * hasEmpty method. Scans through the linkedlists to check if there is a null (empty) space
     *
     * @return true if a null space exists in the board. False if there are no null spaces.
     */
    public boolean hasEmpty() {
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                if (board.get(i).get(j) == null) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * getTile Method. Takes a row and column position and returns the tile object at that position
     *
     * @param row the row position
     * @param col the column position
     * @return returns the Tile object located at the position entered
     */
    public Tile getTile(int row, int col) {
        checkRowCol(row, col);
        if (board.get(row).get(col) == null) {
            return null;
        }
        return board.get(row).get(col);
    }

    /**
     * setTile Method. Takes entered position and sets there to the tile given as a parameter
     *
     * @param row the row position of the tile that needs to be set
     * @param col the column position of the tile that needs to be set
     * @param t   the new tile that will be replacing the old one
     */
    public void setTile(int row, int col, Tile t) {
        checkRowCol(row, col);
        board.get(row).set(col, t);
    }

    /**
     * getValue method. Takes entered position, gets the tile there, and then returns the value of that tile.
     *
     * @param row the row position of the tile that we want the value of
     * @param col the column position of the tile that we want the value of
     * @return the integer value of the tile.
     */
    public int getValue(int row, int col) {
        checkRowCol(row, col);
        return board.get(row).get(col).getValue();
    }

    /**
     * Print board method. Prints to the console a visual representation of the board.
     */
    public void printBoard() {
        for (int i = 0; i < this.dimension; ++i) {
            System.out.print("[");
            for (int j = 0; j < this.dimension; ++j) {
                if (getTile(i, j) == null) {
                    System.out.print("  ");
                } else {
                    System.out.print(getTile(i, j).toString() + " ");
                }
            }
            System.out.print("]\n");
        }
    }

    /*********************Private Methods*****************************/

    /*
    Sets all the inner linked list values to null, representing empty spaces.
     */
    private void setupLinkedLists(int dim) {
        for (int i = 0; i < dim; i++) {
            board.add(new java.util.LinkedList<Tile>());
            for (int j = 0; j < dim; j++) {
                board.get(i).add(null);
            }
        }
    }

    /*
    Method to check for valid entry of rows and columns. Used in every public method that takes row/col
    as a parameter.
     */
    private void checkRowCol(int row, int col) {
        if (row > this.dimension - 1 || row < 0 || col > this.dimension - 1 || col < 0) {
            throw new IllegalArgumentException("Invalid row or column entered.");
        }
    }


}