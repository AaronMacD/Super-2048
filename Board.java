package Project3;

import weiss.util.LinkedList;

import java.security.InvalidParameterException;

public class Board {

    private weiss.util.LinkedList<weiss.util.LinkedList<Tile>> board = new weiss.util.LinkedList<weiss.util.LinkedList<Tile>>();
    private int dimension;

    public Board() {
        setupLinkedLists(4);
    }
    public Board(int dim){
        setupLinkedLists(dim);
    }

    public void setDimension(int dim){
        if (dim < 11){
            this.dimension = dim;
        }
        else{
            throw new IllegalArgumentException("Invalid Dimension");
        }
    }
    public int getDimension(){
        return this.dimension;
    }

    public boolean hasEmpty(){
        for(int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                if (board.get(i).get(j) == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public Tile getTile(int row, int col){
        checkRowCol(row, col);
        return board.get(row).get(col);
    }
    public void setTile(int row, int col, Tile t){
        checkRowCol(row, col);
        board.get(row).set(col, t);
    }
    public int getValue(int row, int col){
        checkRowCol(row, col);
        return board.get(row).get(col).getValue();
    }


    private void setupLinkedLists(int dim){
        for(int i = 0; i < dim; i++){
            board.add(new LinkedList<Tile>());
            for(int j = 0; j < dim; j++){
                board.get(i).add(null);
            }
        }
    }

    private void checkRowCol(int row, int col){
        if(row > this.dimension || row < 0 || col > this.dimension || col < 0){
            throw new IllegalArgumentException("Invalid row or column entered.");
        }
    }
}
