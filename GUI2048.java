package Project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUI2048 extends JPanel {
    private JButton[][] jLabelsBoard;
    private GameController game = new GameController();

    private JPanel menu = new JPanel();
    private JPanel gameBoard = new JPanel();

    private JButton quitButton;
    private JButton resetButton;

    private ButtonListener buttonListener = new ButtonListener();

    public GUI2048() {
        super();
        this.setFocusable(true);
        addKeyListener(new KeyboardListener());

        quitButton = new JButton("Quit");
        quitButton.addActionListener(buttonListener);
        quitButton.setFocusable(false);
        menu.add(quitButton);

        resetButton = new JButton("Reset");
        resetButton.addActionListener(buttonListener);
        resetButton.setFocusable(false);
        menu.add(resetButton);

        gameBoard.setLayout(new GridLayout(4,4));
        jLabelsBoard = new JButton[4][4];
        instantiateLabels();
        updateBoard();

        menu.setFocusable(false);
        gameBoard.setFocusable(false);
        this.add(menu);
        this.add(gameBoard);
    }
    private void instantiateLabels(){
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                jLabelsBoard[row][col] = new JButton();
                jLabelsBoard[row][col].setFocusable(false);
                jLabelsBoard[row][col].setPreferredSize(new Dimension(100, 100));
                gameBoard.add(jLabelsBoard[row][col]);
            }
        }
    }
    private void updateBoard(){
        iBoard = game.getBoard ();
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if(iBoard[row][col] != 0) {
                    jLabelsBoard[row][col].setText(String.valueOf(iBoard[row][col]));
                }
                else{
                    jLabelsBoard[row][col].setText("");
                }
            }
        }
    }

    /**
     * Private Class for tracking keyboard input
     */
    private class KeyboardListener implements KeyListener{
        @Override
        public void keyTyped(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyChar() == 'w' || e.getKeyChar() == 'W' ){
                game.selectedUp();
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyChar() == 's' || e.getKeyChar() == 'S' ){
                game.selectedDown();
            }
            if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyChar() == 'a' || e.getKeyChar() == 'A' ){
                game.selectedLeft();
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyChar() == 'd' || e.getKeyChar() == 'D' ){
                game.selectedRight();
            }
            updateBoard();
            if (game.gameOver()) {
                JOptionPane.showMessageDialog(null, "Game Over\nThe game will reset");
                game.reset();
                game.setScore(0);
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {}
        @Override
        public void keyReleased(KeyEvent e) {}
    }

    /**
     * Private Class for tracking button presses
     */
    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(quitButton == e.getSource()){
                System.exit(0);
            }
            if(resetButton == e.getSource()){
                game.reset();
            }

            updateBoard();
        }

    }
    public static void main(String[] args) {
        JFrame gui = new JFrame("2048");
        GUI2048 panel = new GUI2048();
        gui.add(panel);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.pack();
        gui.setVisible(true);
    }
}
