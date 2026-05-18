package math130.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GameController {

    private GameBoard board = new GameBoard();

    private Player player1;
    private Player player2;
    private Player currentPlayer;

    private boolean gameOver = true;

    // UI
    @FXML private Button b00, b01, b02;
    @FXML private Button b10, b11, b12;
    @FXML private Button b20, b21, b22;

    @FXML private Button pvpButton;
    @FXML private Button easyButton;
    @FXML private Button hardButton;
    @FXML private Button homeButton;

    @FXML private Label statusLabel;

    @FXML
    public void initialize() {
        goHome();
    }

    // ---------------- HOME ----------------

    @FXML
    public void goHome() {
        gameOver = true;
        board.resetBoard();
        clearBoardUI();

        statusLabel.setText("Choose a Game Mode");

        showMenu(true);
        homeButton.setVisible(false);
    }

    private void showMenu(boolean show) {
        pvpButton.setVisible(show);
        easyButton.setVisible(show);
        hardButton.setVisible(show);
    }

    // ---------------- START MODES ----------------

    @FXML
    public void startPvP() {
        setupGame(new HumanPlayer('X'), new HumanPlayer('O'));
        statusLabel.setText("PvP Mode");
    }

    @FXML
    public void startEasyAI() {
        setupGame(new HumanPlayer('X'), new EasyAIPlayer('O'));
        statusLabel.setText("Easy AI Mode");
    }

    @FXML
    public void startHardAI() {
        setupGame(new HumanPlayer('X'), new HardAIPlayer('O'));
        statusLabel.setText("Hard AI Mode");
    }

    private void setupGame(Player p1, Player p2) {
        player1 = p1;
        player2 = p2;
        currentPlayer = player1;

        board.resetBoard();
        clearBoardUI();

        gameOver = false;

        showMenu(false);
        homeButton.setVisible(false);
    }

    // ---------------- MOVES ----------------

    private void handleMove(int r, int c, Button button) {

        if (gameOver) return;

        if (!board.placeMove(r, c, currentPlayer.getSymbol())) return;

        button.setText(String.valueOf(currentPlayer.getSymbol()));

        if (checkGameEnd()) return;

        switchTurn();

        if (currentPlayer != player1 && (player2 instanceof EasyAIPlayer || player2 instanceof HardAIPlayer)) {
            aiMove();
        }
    }

    @FXML public void handle00() { handleMove(0,0,b00); }
    @FXML public void handle01() { handleMove(0,1,b01); }
    @FXML public void handle02() { handleMove(0,2,b02); }

    @FXML public void handle10() { handleMove(1,0,b10); }
    @FXML public void handle11() { handleMove(1,1,b11); }
    @FXML public void handle12() { handleMove(1,2,b12); }

    @FXML public void handle20() { handleMove(2,0,b20); }
    @FXML public void handle21() { handleMove(2,1,b21); }
    @FXML public void handle22() { handleMove(2,2,b22); }

    // ---------------- AI ----------------

    private void aiMove() {

        int[] move = currentPlayer.makeMove(board);
        if (move == null) return;

        board.placeMove(move[0], move[1], currentPlayer.getSymbol());

        getButton(move[0], move[1]).setText(String.valueOf(currentPlayer.getSymbol()));

        if (checkGameEnd()) return;

        switchTurn();
    }

    // ---------------- GAME LOGIC ----------------

    private boolean checkGameEnd() {

        char winner = board.checkWinner();

        if (winner != ' ') {
            statusLabel.setText("Player " + winner + " wins!");
            gameOver = true;
            homeButton.setVisible(true);
            return true;
        }

        if (board.isFull()) {
            statusLabel.setText("Tie Game!");
            gameOver = true;
            homeButton.setVisible(true);
            return true;
        }

        return false;
    }

    private void switchTurn() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    // ---------------- UI ----------------

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