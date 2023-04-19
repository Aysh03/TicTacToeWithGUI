package tictactoegame;

import javax.swing.*;
import java.awt.*;

public class TicTacToeGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    private JButton[][] board;
    private char currentPlayer;
    private JPanel gamePanel;

    public TicTacToeGUI() {
        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(3, 3));

        board = new JButton[3][3];
        currentPlayer = 'X';

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new JButton("");
                board[i][j].setBackground(Color.WHITE);


                board[i][j].addActionListener(e -> {
                    JButton button = (JButton) e.getSource();
                    int row = -1;
                    int col = -1;
                    for (int k = 0; k < board.length; k++) {
                        for (int l = 0; l < board[0].length; l++) {
                            if (button == board[k][l]) {
                                row = k;
                                col = l;
                                break;
                            }
                        }
                    }
                    if (board[row][col].getText().equals("")) {
                        board[row][col].setText("" + currentPlayer);
                        board[row][col].setBackground(Color.ORANGE);
                        char winner = checkForWin();
                        if (winner != '\0') {
                            JOptionPane.showMessageDialog(gamePanel, "Player " + winner + " has won!");
                            currentPlayer = 'X';
                            resetBoard();
                        } else {
                            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                        }
                    }
                });

                gamePanel.add(board[i][j]);
            }
        }

        add(gamePanel);
        setVisible(true);
    }

    public char checkForWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (!board[i][0].getText().equals("") && board[i][0].getText().equals(board[i][1].getText()) && board[i][1].getText().equals(board[i][2].getText())) {
                return board[i][0].getText().charAt(0);
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (!board[0][i].getText().equals("") && board[0][i].getText().equals(board[1][i].getText()) && board[1][i].getText().equals(board[2][i].getText())) {
                return board[0][i].getText().charAt(0);
            }
        }

        // Check diagonals
        if (!board[0][0].getText().equals("") && board[0][0].getText().equals(board[1][1].getText()) && board[1][1].getText().equals(board[2][2].getText())) {
            return board[0][0].getText().charAt(0);
        }
        if (!board[0][2].getText().equals("") && board[0][2].getText().equals(board[1][1].getText()) && board[1][1].getText().equals(board[2][0].getText())) {
            return board[0][2].getText().charAt(0);
        }
        // Check for tie
        boolean tie = true;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].getText().equals("")) {
                    tie = false;
                    break;
                }
            }
        }
        if (tie) {
            System.out.println("It's a tie!");
            return 'T';
        }

        return '\0';
    }


    public void resetBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j].setText("");
                board[i][j].setBackground(Color.WHITE);
            }
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToeGUI::new);
    }

}