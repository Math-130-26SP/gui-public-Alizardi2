package math130.gui;

/**
 * Represents the Tic Tac Toe board logic.
 * this andles moves, win checking and the board state.
 */
public class GameBoard {

    private char[][] board;

    public GameBoard() {
        board = new char[3][3];
        resetBoard();
    }

    /**
     * Clears the board for a new game.
     */
    public void resetBoard() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c] = ' ';
            }
        }
    }

    /**
     * Places a move if the spot is empty.
     */
    public boolean placeMove(int r, int c, char symbol) {

        // Safety check (prevents crashes from invalid input)
        if (r < 0 || r > 2 || c < 0 || c > 2) {
            throw new IllegalArgumentException("Invalid move position");
        }

        if (board[r][c] == ' ') {
            board[r][c] = symbol;
            return true;
        }
        return false;
    }

    /**
     * Returns the board array.
     */
    public char[][] getBoard() {
        return board;
    }

    /**
     * Checks if the board is full (tie condition).
     */
    public boolean isFull() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c] == ' '){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks all win conditions (rows, columns, diagonals).
     * @return winner symbol or ' ' if no winner
     */
    public char checkWinner() {

        // Check rows
        for (int r = 0; r < 3; r++) {
            if (board[r][0] != ' ' && board[r][0] == board[r][1] && board[r][1] == board[r][2]) {
                return board[r][0];
            }
        }

        // Check columns
        for (int c = 0; c < 3; c++) {
            if (board[0][c] != ' ' && board[0][c] == board[1][c] && board[1][c] == board[2][c]) {
                return board[0][c];
            }
        }

        // Check diagonals
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][0];
        }

        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return board[0][2];
        }

        return ' '; // no winner
    }
}