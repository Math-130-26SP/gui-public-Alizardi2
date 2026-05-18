package math130.gui;

public class GameBoard {

    private char[][] board;

    public GameBoard() {
        board = new char[3][3];
        resetBoard();
    }

    public void resetBoard() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c] = ' ';
            }
        }
    }

    public boolean placeMove(int r, int c, char symbol) {
        if (board[r][c] == ' ') {
            board[r][c] = symbol;
            return true;
        }
        return false;
    }

    public char[][] getBoard() {
        return board;
    }

    public boolean isFull() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c] == ' ') return false;
            }
        }
        return true;
    }

    public char checkWinner() {
        // rows
        for (int r = 0; r < 3; r++) {
            if (board[r][0] != ' ' &&
                    board[r][0] == board[r][1] &&
                    board[r][1] == board[r][2]) {
                return board[r][0];
            }
        }

        // columns
        for (int c = 0; c < 3; c++) {
            if (board[0][c] != ' ' &&
                    board[0][c] == board[1][c] &&
                    board[1][c] == board[2][c]) {
                return board[0][c];
            }
        }

        // diagonals
        if (board[0][0] != ' ' &&
                board[0][0] == board[1][1] &&
                board[1][1] == board[2][2]) {
            return board[0][0];
        }

        if (board[0][2] != ' ' &&
                board[0][2] == board[1][1] &&
                board[1][1] == board[2][0]) {
            return board[0][2];
        }

        return ' '; // no winner
    }
}