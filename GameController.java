package Project3;

import java.util.Random;

/**
 * Game Controller Class. Contains all the methods to run a 2048 game through a separate interface.
 */
public class GameController {

    /************************Instance Variables***********************************/


    /**
     * Object made using the Board class. Contains a doubly linked list containing tiles, which contain
     * our numbers to be displayed.
     */
    private Board board = new Board();
    /**
     * GameStatus enum to keep track of winning/losing for interfaces.
     */
    private GameStatus gameStatus;
    /**
     * Integer holding score value. Accessable via getter/setter for interfaces.
     */
    private int score;
    /**
     * Integer holding the dimension of the board. 4 by default, but can be modified via constructor parameter.
     */
    private int dimension = 4;
    /**
     * Integer holding the win value of the game. 2048 by default, hence the name of the game.
     */
    private int winValue = 2048;


    /********************************Constructors******************************/


    /**
     * Default Constructor. Runs a game with board size 4 and win condition 2048.
     */
    public GameController() {
        reset();
    }

    /**
     * Constructor for setting dimension and win value
     *
     * @param dim      Board size set by the user
     * @param winValue Win value set by the user (NYI)
     */
    public GameController(int dim, int winValue) {
        this.dimension = dim;
        this.winValue = winValue;
        reset();
    }

    /*******************************Setters/Getters**************************/

    /**
     * setter for board. Replaces existing Board object with a new Board object for the GameController's
     * private Board field.
     *
     * @param board new board to replace the old one.
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Getter for board.
     *
     * @return returns the Board object in active use by GameController.
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * Setter for score
     *
     * @param score the new integer to replace current score private field.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Getter for score
     *
     * @return returns int score of current GameController
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Setter for the games current Status
     *
     * @param gs a new GameStatus enum value to change GameControllers gamestatus enum field.
     */
    public void setStatus(GameStatus gs) {
        this.gameStatus = gs;
    }

    /**
     * Getter for the current GameController's GameStatus enum field.
     *
     * @return a GameStatus enum representing win/loss/in progress for the current GameController
     */
    public GameStatus getStatus() {
        return this.gameStatus;
    }

    /**
     * Getter for dimension.
     *
     * @return int dimension of the board
     */
    public int getDimension() {
        return this.dimension;
    }


    /************************** Methods******************************/

    /**
     * reset method. Creates a new board, resets the game status to in progress, and generates new
     * starting numbers on the board.
     */
    public void reset() {
        board = new Board(dimension);
        gameStatus = GameStatus.IN_PROGRESS;
        newTile();
        newTile();
    }

    /**
     * newTile function generates two numbers (if available) randomly on the board,
     * choosing either 2 or 4 randomly.
     */
    public void newTile() {
        Random random = new Random();
        int num = random.nextInt(2);
        while (board.hasEmpty()) {
            int row = random.nextInt(dimension);
            int col = random.nextInt(dimension);
            if (board.getTile(row, col) == null) {
                if (num == 0) {
                    board.setTile(row, col, new Tile(2));
                } else {
                    board.setTile(row, col, new Tile(4));
                }
                break;
            }
        }
    }

    /**
     * recurseDown Method. When the interface calls this method, it moves and consolidates every number to
     * the bottom most positions of the board, given space and equivalent numbers.
     *
     * @param row Row number, used to keep track of recursion.
     * @param col Column number, used to keep track of recursion.
     */


    public void recurseDown(int row, int col) {
        Tile currentTile = board.getTile(row, col);
        if (row < dimension - 1 && currentTile != null && board.getTile(row + 1, col) == null) {
            board.setTile(row + 1, col, board.getTile(row, col));
            board.setTile(row, col, null);
            if (row < dimension - 2) {
                recurseDown(row + 1, col);
            }
        } else if (row < dimension - 1 && currentTile != null && board.getValue(row, col) == board.getValue(row + 1, col)) {
            board.getTile(row + 1, col).setValue(board.getValue(row + 1, col) * 2);
            board.setTile(row, col, null);
            if (row < dimension - 2) {
                recurseDown(row + 1, col);
            }
        }

        if (col < dimension - 1 && row == 0) {
            recurseDown(dimension - 1, col + 1);
        } else if (row > 0) {
            recurseDown(row - 1, col);
        }
    }


    /**
     * recurseUp Method. When the interface calls this method, it moves and consolidates every number to
     * the uppermost positions of the board, given space and equivalent numbers.
     *
     * @param row Row number, used to keep track of recursion.
     * @param col Column number, used to keep track of recursion.
     */
    public void recurseUp(int row, int col) {
        Tile currentTile = board.getTile(row, col);
        if (row > 0 && currentTile != null && board.getTile(row - 1, col) == null) {
            board.setTile(row - 1, col, board.getTile(row, col));
            board.setTile(row, col, null);
            if (row > 1) {
                recurseUp(row - 1, col);
            }
        } else if (row > 0 && currentTile != null && board.getValue(row, col) == board.getValue(row - 1, col)) {
            board.getTile(row - 1, col).setValue(board.getValue(row - 1, col) * 2);
            board.setTile(row, col, null);
            if (row > 1) {
                recurseUp(row - 1, col);
            }
        }

        if (col < dimension - 1 && row == dimension - 1) {
            recurseUp(0, col + 1);
        }
        if (row < dimension - 1) {
            recurseUp(row + 1, col);
        }
    }

    /**
     * recurseRight Method. When the interface calls this method, it moves and consolidates every number to
     * the right most positions of the board, given space and equivalent numbers.
     *
     * @param row Row number, used to keep track of recursion.
     * @param col Column number, used to keep track of recursion.
     */

    public void recurseRight(int row, int col) {
        Tile currentTile = board.getTile(row, col);
        if (col < dimension - 1 && currentTile != null && board.getTile(row, col + 1) == null) {
            board.setTile(row, col + 1, board.getTile(row, col));
            board.setTile(row, col, null);
            if (col < dimension - 2) {
                recurseRight(row, col + 1);
            }
        } else if (col < dimension - 1 && currentTile != null && board.getValue(row, col) == board.getValue(row, col + 1)) {
            board.getTile(row, col + 1).setValue(board.getValue(row, col + 1) * 2);
            board.setTile(row, col, null);
            if (col < dimension - 2) {
                recurseRight(row, col + 1);
            }
        }
        if (row < dimension - 1 && col == 0) {
            recurseRight(row + 1, dimension - 1);
        } else if (col > 0) {
            recurseRight(row, col - 1);
        }

    }

    /**
     * recurseLeft Method. When the interface calls this method, it moves and consolidates every number to
     * the left most positions of the board, given space and equivalent numbers.
     *
     * @param row Row number, used to keep track of recursion.
     * @param col Column number, used to keep track of recursion.
     */
    public void recurseLeft(int row, int col) {
        Tile currentTile = board.getTile(row, col);
        //check for current tile and check for empty next tile, then move and follow
        if (col > 0 && currentTile != null && board.getTile(row, col - 1) == null) {
            board.setTile(row, col - 1, board.getTile(row, col));
            board.setTile(row, col, null);
            if (col > 1) {
                recurseLeft(row, col - 1);
            }
        }
        //check current tile and check next tile, then combine and follow
        else if (col > 0 && currentTile != null && board.getValue(row, col) == board.getValue(row, col - 1)) {
            board.getTile(row, col - 1).setValue(board.getValue(row, col - 1) * 2);
            board.setTile(row, col, null);
            if (col > 1) {
                recurseLeft(row, col - 1);
            }
        }
        //if end of column and not at end of rows, move to next row
        if (row < dimension - 1 && col == dimension - 1) {
            recurseLeft(row + 1, 0);
        }
        //if not at end of column, increase column
        else if (col < dimension - 1) {
            recurseLeft(row, col + 1);
        }
    }

    /**
     * Checks for a win or loss and updates the GameStatus variable of the gamecontroller
     */
    public void updateStatus() {
        checkWin();
        checkLoss();
    }

    /********************* PRIVATE HELPER METHODS ***********************/


    /*
    Check win scans the whole board, checking to see if there is a match for win condition.
     */
    private void checkWin() {
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                if (board.getTile(i, j) != null && board.getValue(i, j) == this.winValue) {
                    gameStatus = GameStatus.WON;
                }
            }
        }
    }

    /*
    Check loss confirms an empty space exists, and if not, checks for a similar neighbor on each value.
    If a similar value exists, the game continues. If not, the game status is declared lost.
     */
    private void checkLoss() {
        if (!board.hasEmpty()) {
            for (int i = 0; i < this.dimension; i++) {
                for (int j = 0; j < this.dimension; j++) {
                    if (findSimilarNeighbors(i, j)) {
                        gameStatus = GameStatus.IN_PROGRESS;
                        return;
                    }
                }
            }
            gameStatus = GameStatus.LOST;
        }
    }

    /*
    Private method findSimilarNeighbors checks each position around a given row x col for an identical value.
    Returns true if there is a neighbor with same value.
    False if there is not a neighbor of same value.
     */
    private boolean findSimilarNeighbors(int row, int col) {
        int val = board.getValue(row, col);
        int above = 0;
        int below = 0;
        int left = 0;
        int right = 0;
        if (row > 0) {
            above = board.getValue(row - 1, col);
        }
        if (row < this.dimension - 1) {
            below = board.getValue(row + 1, col);
        }
        if (col > 1) {
            left = board.getValue(row, col - 1);
        }
        if (col < this.dimension - 1) {
            right = board.getValue(row, col + 1);
        }

        if (val == above || val == below || val == left || val == right) {
            return true;
        }
        return false;
    }
}
