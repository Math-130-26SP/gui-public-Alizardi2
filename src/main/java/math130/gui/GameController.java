package math130.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GameController {

    // BOARD
    private GameBoard board = new GameBoard();

    // PLAYERS
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    private boolean gameOver = false;

    // UI ELEMENTS (must match fx:id in FXML)
    @FXML private Button b00;
    @FXML private Button b01;
    @FXML private Button b02;

    @FXML private Button b10;
    @FXML private Button b11;
    @FXML private Button b12;

    @FXML private Button b20;
    @FXML private Button b21;
    @FXML private Button b22;

    @FXML private Label statusLabel;

    // CALLED AUTOMATICALLY WHEN SCREEN LOADS
    @FXML
    public void initialize() {
        startPvP(); // default mode for now
    }

    // -----------------------
    // GAME SETUP
    // -----------------------

    public void startPvP() {
        player1 = new HumanPlayer('X');
        player2 = new HumanPlayer('O');
        currentPlayer = player1;

        board.resetBoard();
        gameOver = false;
        statusLabel.setText("Player X's turn");

        clearBoardUI();
    }

    public void startEasyAI() {
        player1 = new HumanPlayer('X');
        player2 = new EasyAIPlayer('O');
        currentPlayer = player1;

        board.resetBoard();
        gameOver = false;
        statusLabel.setText("Player X's turn (vs Easy AI)");

        clearBoardUI();
    }

    public void startHardAI() {
        player1 = new HumanPlayer('X');
        player2 = new HardAIPlayer('O');
        currentPlayer = player1;

        board.resetBoard();
        gameOver = false;
        statusLabel.setText("Player X's turn (vs Hard AI)");

        clearBoardUI();
    }

    // -----------------------
    // BUTTON CLICK HANDLERS
    // -----------------------

    @FXML public void handle00() { handleMove(0, 0, b00); }
    @FXML public void handle01() { handleMove(0, 1, b01); }
    @FXML public void handle02() { handleMove(0, 2, b02); }

    @FXML public void handle10() { handleMove(1, 0, b10); }
    @FXML public void handle11() { handleMove(1, 1, b11); }
    @FXML public void handle12() { handleMove(1, 2, b12); }

    @FXML public void handle20() { handleMove(2, 0, b20); }
    @FXML public void handle21() { handleMove(2, 1, b21); }
    @FXML public void handle22() { handleMove(2, 2, b22); }

    // -----------------------
    // CORE MOVE LOGIC
    // -----------------------

    private void handleMove(int r, int c, Button button) {

        if (gameOver) return;

        // HUMAN MOVE
        if (board.placeMove(r, c, currentPlayer.getSymbol())) {

            button.setText(String.valueOf(currentPlayer.getSymbol()));

            if (checkGameEnd()) return;

            switchTurn();

            // IF PLAYER 2 IS AI AND IT IS THEIR TURN
            if ((player2 instanceof EasyAIPlayer || player2 instanceof HardAIPlayer)
                    && currentPlayer == player2) {
                aiMove();
            }
        }
    }

    // -----------------------
    // AI MOVE
    // -----------------------

    private void aiMove() {

        int[] move = currentPlayer.makeMove(board);
        if (move == null) return;

        int r = move[0];
        int c = move[1];

        board.placeMove(r, c, currentPlayer.getSymbol());

        getButton(r, c).setText(String.valueOf(currentPlayer.getSymbol()));

        if (checkGameEnd()) return;

        switchTurn();
    }

    // -----------------------
    // GAME STATE
    // -----------------------

    private boolean checkGameEnd() {

        char winner = board.checkWinner();

        if (winner != ' ') {
            statusLabel.setText("Player " + winner + " wins!");
            gameOver = true;
            return true;
        }

        if (board.isFull()) {
            statusLabel.setText("It's a tie!");
            gameOver = true;
            return true;
        }

        return false;
    }

    private void switchTurn() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
        statusLabel.setText("Player " + currentPlayer.getSymbol() + "'s turn");
    }

    // -----------------------
    // UI HELPERS
    // -----------------------

    private void clearBoardUI() {
        b00.setText(""); b01.setText(""); b02.setText("");
        b10.setText(""); b11.setText(""); b12.setText("");
        b20.setText(""); b21.setText(""); b22.setText("");
    }

    private Button getButton(int r, int c) {
        if (r == 0 && c == 0) return b00;
        if (r == 0 && c == 1) return b01;
        if (r == 0 && c == 2) return b02;

        if (r == 1 && c == 0) return b10;
        if (r == 1 && c == 1) return b11;
        if (r == 1 && c == 2) return b12;

        if (r == 2 && c == 0) return b20;
        if (r == 2 && c == 1) return b21;

        return b22;
    }
}