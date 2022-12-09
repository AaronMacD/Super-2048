package Project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUI2048 extends JPanel {

    private int dimension;

    private static int gameNumber = 1;

    private static int winNumber = 0;
    private Board iBoard;

    private JButton[][] jLabelsBoard;
    private static GameController game = new GameController();

    private JPanel menu = new JPanel();
    private JPanel gameBoard = new JPanel();
    private JPanel controller = new JPanel();

    private JButton upButton;
    private JButton downButton;
    private JButton leftButton;
    private JButton rightButton;
    private JButton invisButton;
    private JButton quitButton;
    private JButton resetButton;

    private static JMenuBar menus = new JMenuBar();

    private JLabel winText = new JLabel("Wins: " + winNumber);
    private JLabel gameText = new JLabel("Game Number: " + gameNumber);

    private ButtonListener buttonListener = new ButtonListener();

    public GUI2048(int dim) {
        super();

        this.dimension = dim;

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

        controller.setLayout(new BorderLayout());

        upButton = new JButton("^");
        upButton.addActionListener(buttonListener);
        upButton.setFocusable(false);
        controller.add(upButton, BorderLayout.NORTH);

        downButton = new JButton("v");
        downButton.addActionListener(buttonListener);
        downButton.setFocusable(false);
        controller.add(downButton, BorderLayout.SOUTH);

        leftButton = new JButton("<");
        leftButton.addActionListener(buttonListener);
        leftButton.setFocusable(false);
        controller.add(leftButton, BorderLayout.WEST);

        rightButton = new JButton(">");
        rightButton.addActionListener(buttonListener);
        rightButton.setFocusable(false);
        controller.add(rightButton, BorderLayout.EAST);

        gameBoard.setLayout(new GridLayout(dimension, dimension));
        jLabelsBoard = new JButton[dimension][dimension];
        instantiateLabels();
        updateBoard();

        menu.setFocusable(false);
        controller.setFocusable(false);
        gameBoard.setFocusable(false);


        JMenu fileMenu = new JMenu("File");
        //JMenuBar menus = new JMenuBar();
        fileMenu.add(resetButton);
        fileMenu.add(quitButton);

        menus.add(fileMenu);


        //this.add (menus, BorderLayout.NORTH);

        this.add(gameText);
        this.add(winText);
        this.add(gameBoard);
        this.add(controller);


    }

    private void instantiateLabels() {
        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                jLabelsBoard[row][col] = new JButton();
                jLabelsBoard[row][col].setFocusable(false);
                jLabelsBoard[row][col].setPreferredSize(new Dimension(100, 100));
                gameBoard.add(jLabelsBoard[row][col]);
            }
        }
    }

    private void updateBoard() {
        iBoard = game.getBoard();
        winText.setText("Wins: " + winNumber);
        gameText.setText("Games: " + gameNumber);

        for (int row = 0; row < this.dimension; row++) {
            for (int col = 0; col < this.dimension; col++) {
                if (iBoard.getTile(row, col) == null) {
                    jLabelsBoard[row][col].setBackground(Color.white);
                }
                if (iBoard.getTile(row, col) != null) {
                    jLabelsBoard[row][col].setText(iBoard.getTile(row, col).toString());

                    if (iBoard.getTile(row, col).toString().equals("2")) {
                        jLabelsBoard[row][col].setBackground(new Color(255, 234, 229));
                    }
                    else if (iBoard.getTile(row, col).toString().equals("4")) {
                        jLabelsBoard[row][col].setBackground(new Color(255, 213, 204));
                    }
                    else if (iBoard.getTile(row, col).toString().equals("8")) {
                        jLabelsBoard[row][col].setBackground(new Color(255, 192, 179));
                    }
                    else if (iBoard.getTile(row, col).toString().equals("16")) {
                        jLabelsBoard[row][col].setBackground(new Color(255, 171, 153));
                    }
                    else if (iBoard.getTile(row, col).toString().equals("32")) {
                        jLabelsBoard[row][col].setBackground(new Color(255, 150, 128));
                    }
                    else if (iBoard.getTile(row, col).toString().equals("64")) {
                        jLabelsBoard[row][col].setBackground(new Color(255, 129, 102));
                    }
                    else if (iBoard.getTile(row, col).toString().equals("128")) {
                        jLabelsBoard[row][col].setBackground(new Color(255, 108, 77));
                    }
                    else if (iBoard.getTile(row, col).toString().equals("256")) {
                        jLabelsBoard[row][col].setBackground(new Color(255, 87, 51));
                    }
                    else if (iBoard.getTile(row, col).toString().equals("512")) {
                        jLabelsBoard[row][col].setBackground(new Color(255, 66, 25));
                    }
                    else if (iBoard.getTile(row, col).toString().equals("1024")) {
                        jLabelsBoard[row][col].setBackground(new Color(255, 40, 0));
                    }
                    else if (iBoard.getTile(row, col).toString().equals("2048")) {
                        jLabelsBoard[row][col].setBackground(new Color(255, 14, 0));
                    }
                    else
                    {
                        jLabelsBoard[row][col].setBackground(new Color(0, 0, 0));
                    }

                } else {
                    jLabelsBoard[row][col].setText("");
                }
            }
        }
    }

    private void checkStatus() {
        game.updateStatus();
        if (game.getStatus() == GameStatus.LOST) {
            JOptionPane.showMessageDialog(null, "Game Over\nThe game will reset");
            gameNumber++;
            game.reset();
            game.setScore(0);
        } else if (game.getStatus() == GameStatus.WON) {
            winNumber++;
            gameNumber++;
            JOptionPane.showMessageDialog(null, "Congratulations, you won!\nThe game will reset");
            game.reset();
            game.setScore(0);
        } else {
            game.newTile();
        }

    }

    /**
     * Private Class for tracking keyboard input
     */
    private class KeyboardListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyChar() == 'w' || e.getKeyChar() == 'W') {
                game.recurseUp(0, 0);
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyChar() == 's' || e.getKeyChar() == 'S') {
                game.recurseDown(dimension - 1, 0);
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {
                game.recurseLeft(0, 0);
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
                game.recurseRight(0, dimension - 1);
            }
            checkStatus();
            updateBoard();
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    /**
     * Private Class for tracking button presses
     */
    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (upButton == e.getSource()) {
                game.recurseUp(0, 0);
            }
            if (downButton == e.getSource()) {

                try {
                    game.recurseDown(dimension - 1, 0);
                } catch (Exception excp) {
                    JOptionPane.showMessageDialog(null, excp.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (leftButton == e.getSource()) {
                game.recurseLeft(0, 0);
            }
            if (rightButton == e.getSource()) {
                game.recurseRight(0, dimension - 1);
            }
            checkStatus();

            if (resetButton == e.getSource()) {
                gameNumber++;
                game.reset();
            }
            updateBoard();

            if (quitButton == e.getSource()) {
                System.exit(0);
            }
        }

    }

    public static void main(String[] args) {
        JFrame gui = new JFrame("2048");

        int dimension = 4;
        boolean quit = false;
        boolean validDimEntry = false;
        while (!validDimEntry && !quit) {
            String dimensionSelection = JOptionPane.showInputDialog(null,
                    "Enter in the size of the board (4-10): ");

            if (dimensionSelection == null) {
                gui.dispose();
                quit = true;
                break;
            }

            try {
                dimension = Integer.parseInt(dimensionSelection);
                if (dimension > 3 && dimension < 11) {
                    validDimEntry = true;
                } else {
                    validDimEntry = false;
                }
            } catch (Exception e) {
                validDimEntry = false;
            }

            if (validDimEntry == false) {
                JOptionPane.showMessageDialog(null, "Invalid Entry! Must be between 2 and 15.", "Invalid Entry", JOptionPane.ERROR_MESSAGE);
            }
        }
        boolean validWinEntry = false;
        int newWin = 2048;
        while (!validWinEntry && !quit) {
            String winSelection = JOptionPane.showInputDialog(null,
                    "Enter the minimum win condition ");

            if (winSelection == null) {
                gui.dispose();
                quit = true;
                break;
            }

            try {
                newWin = Integer.parseInt(winSelection);
                Tile checkTile = new Tile(newWin);
                validWinEntry = true;
                //} else {
                //    validWinEntry = false;
                //} if (validDimEntry == false) {
                //                JOptionPane.showMessageDialog(null, "Invalid Entry! Must be between 2 and 15.", "Invalid Entry", JOptionPane.ERROR_MESSAGE);
                //            }
            } catch (Exception e) {
                validWinEntry = false;
            }

            if (validWinEntry == false) {
                JOptionPane.showMessageDialog(null, "Invalid Entry! Must be a power of two!", "Invalid Entry", JOptionPane.ERROR_MESSAGE);
            }

            newWin = Integer.parseInt(winSelection);
        }
        game = new GameController(dimension, newWin);


        if (!quit) {
            GUI2048 panel = new GUI2048(dimension);
            gui.add(panel);
            gui.setJMenuBar(menus);
            gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gui.pack();
            gui.setVisible(true);
        }

    }
}