package Project3;

import java.util.Arrays;
import java.util.Random;

public class GameController {
    private Board board = new Board();
    private int score;
    private int dimension;
    private int winValue;

    public GameController(){
        board = new Board();
        dimension = 4;
        winValue = 2048;
        reset();
    }
    public GameController(int dim, int winValue){
        this.dimension = dim;
        this.winValue = winValue;
        board = new Board(dim);
        reset();
    }

    public void setBoard(Board board){this.board = board;}
    public Board getBoard(){return this.board;}
    public void setScore(int score){this.score = score;}
    public int getScore(){return this.score;}

    public void reset() {
        for (int row = 0; row < 4; row++){
            for (int col = 0; col < 4; col++) {
                board[row][col] = 0;
            }
        }
        random2Placement();
        random2Placement();
    }
    public boolean gameOver(){
        if(boardFull() && noMoveAvailableLoop()){
            return true;
        }
        return false;
    }
    public void selectedUp() {
        int[][] previousBoard = deepCopy(board);
        for (int col = 0; col < 4; col++) {
            collapseUp2(3, col);
            collapseUp2(3, col);
            combineUp(0, col);
            collapseUp2(3, col);
            collapseUp2(3, col);
        }
        if(!Arrays.deepEquals(board, previousBoard)){
            random2Placement();
        }
    }
    public void selectedDown(){
        int[][] previousBoard = deepCopy(board);
        combinationDown(0, 0, 1);
        moveDown(0,0);
        if(!Arrays.deepEquals(board, previousBoard)){
            random2Placement();
        }
    }
    public void selectedLeft(){
        int[][] previousBoard = deepCopy(board);
        for (int row = 0; row < 4; row++){
            collapseLeft(row, 3);
            collapseLeft(row, 3);
            combineLeft(row, 0);
            collapseLeft(row, 3);
            collapseLeft(row, 3);
        }
        if(!Arrays.deepEquals(board, previousBoard)){
            random2Placement();
        }
    }
    public void selectedRight(){
        int[][] previousBoard = deepCopy(board);
        for (int row = 0; row < 4; row++){
            collapseRight(row, 0);
            collapseRight(row, 0);
            combineRight(row, 3);
            collapseRight(row, 0);
            collapseRight(row, 0);

        }
        if(!Arrays.deepEquals(board, previousBoard)){
            random2Placement();
        }
    }
    private void combineUp(int row, int col){
        if(row != 3){
            if(board[row][col] != 0 && board[row][col] == board[row+1][col]){
                board[row][col] *= 2;
                board[row+1][col] = 0;
            }
            combineUp(row+1, col);
        }
    }
    private void collapseUp1(int row, int col){
        if(row != 3){
            if(board[row][col] != 0 && board[row-1][col] == 0){
                board[row-1][col] = board[row][col];
                board[row][col] = 0;
            }
            collapseUp1(row+1, col);
        }
    }
    private void collapseUp2(int row, int col){
        if(row != 0){
            if(board[row][col] != 0 && board[row-1][col] == 0){
                board[row-1][col] = board[row][col];
                board[row][col] = 0;
            }
            collapseUp2(row-1, col);
        }
    }

    private void combineDown(int row, int col){
        if(row != 0){
            if(board[row][col] != 0 && board[row][col] == board[row-1][col]){
                board[row][col] *= 2;
                board[row-1][col] = 0;
            }
            combineDown(row-1, col);
        }
    }
    private void collapseDown(int row, int col){
        if(row != 3){
            if(board[row][col] != 0 && board[row+1][col] == 0){
                board[row+1][col] = board[row][col];
                board[row][col] = 0;
            }
            collapseDown(row+1, col);
        }
    }
    private void combineLeft(int row, int col){
        if(col != 3){
            if(board[row][col] != 0 && board[row][col] == board[row][col+1]){
                board[row][col] *= 2;
                board[row][col+1] = 0;
            }
            combineLeft(row, col+1);
        }
    }
    private void collapseLeft(int row, int col){
        if(col != 0){
            if(board[row][col] != 0 && board[row][col-1] == 0){
                board[row][col-1] = board[row][col];
                board[row][col] = 0;
            }
            collapseLeft(row, col-1);
        }
    }
    private void combineRight(int row, int col){
        if(col != 0){
            if(board[row][col] != 0 && board[row][col] == board[row][col-1]){
                board[row][col] *= 2;
                board[row][col-1] = 0;
            }
            combineRight(row, col-1);
        }
    }
    private void collapseRight(int row, int col){
        if(col != 3){
            if(board[row][col] != 0 && board[row][col+1] == 0){
                board[row][col+1] = board[row][col];
                board[row][col] = 0;
            }
            collapseRight(row, col+1);
        }
    }
    private boolean noMoveAvailableLoop(){
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 4; col++) {
                if (board[row][col] == board[row+1][col]) {
                    return false;
                }
            }
        }
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == board[row][col+1]) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean noMoveAvailableRecursive(){
        return false;
    }
    private boolean boardFull(){
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (board[row][col] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    private void random2Placement(){
        while(!boardFull()) {
            Random random = new Random();
            int row = random.nextInt(4);
            int col = random.nextInt(4);
            if(board[row][col] == 0){
                board[row][col] = 2;
                break;
            }
        }
    }
    private int[][] deepCopy(int[][] board){
        int[][] result = new int[board.length][];
        for (int i = 0; i < board.length; i++){
            result[i] = Arrays.copyOf(board[i], board[i].length);
        }
        return result;
    }

    private void combinationDown(int row, int col, int index){
        if (row == dimension-1 && col != dimension-1) {
            combinationDown(0, col + 1, 1);
        }
        else if (board[row][col] == 0){
            combinationDown(row+1, col, 1);
        }
        else if (((row + index) < dimension -1) && board[row+index][col] == 0){
            combinationDown(row, col, index+1);
        }
        else if (board[row][col] == board[row+index][col]){
            board[row][col] = 0;
            board[row+index][col] *= 2;
            combinationDown(row+1, col, 1);
        }

    }
    private void moveDown(int row, int col){
        if (row == dimension-1 && col != dimension - 1){
            moveDown(0, col + 1);
        }

        else if(board[row][col] != 0 && board[row+1][col] == 0){
            board[row+1][col] = board[row][col];
            board[row][col] = 0;
            moveDown(row+1, col);
        }
        else if(row < dimension-1){
            moveDown(row+1, col);
        }
    }
}
