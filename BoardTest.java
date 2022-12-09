import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    @Test (expected = IllegalArgumentException.class)
     public void setDimension() {
        Board e =new Board(1);
    }
    @Test
   public void setDimension2() {
        Board a =new Board(4);
    }

    @Test (expected = IllegalArgumentException.class)
   public void getDimension() {
        Board d =new Board(22);

    }
    @Test
    public void getDimension2() {
        Board c = new Board(8);
    }

    @Test
   public void hasEmpty() {
        Board b = new Board(4);
        assertNull(b.getTile(0,0));


    }


    @Test (expected = IllegalArgumentException.class)
   public void getTile() {
       Board b = new Board(5);
       assertNull(b.getTile(6,4));


    }


    @Test (expected = IllegalArgumentException.class)
    public void setTile(){
        Board p = new Board(4);
        assertNull(p.getTile(10,10));
    }

    @Test (expected =  IllegalArgumentException.class)
    public void getValue() {
       Board b = new Board(4);
        assertNull(b.getValue(5,7));
    }

    @Test
    public void printBoard() {
        Board b = new Board(4);
        assertNull(b.getTile(0,0));
    }

}
// project members:Aaron MacDougall,Sawyer Rogers,Andre Luna