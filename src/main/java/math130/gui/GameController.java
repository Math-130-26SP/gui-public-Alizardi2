package math130.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * GameController controls all game logic and connects the JavaFX UI
 * to the backend Tic Tac Toe game system.
 *
 * Responsibilities:
 * - itll handle button clicks from the board
 * - the switches between players
 * - runs AI moves
 * - checks for win and tie conditions
 * - Controls menu navigation which is really just the home and the game modes
 */
public class GameController {

    // The Game State

    private GameBoard board = new GameBoard();

    private Player player1;
    private Player player2;
    private Player currentPlayer;

    private boolean gameOver = true;

    // The UI elements

    @FXML private Button b00, b01, b02;
    @FXML private Button b10, b11, b12;
    @FXML private Button b20, b21, b22;

    @FXML private Button pvpButton;
    @FXML private Button easyButton;
    @FXML private Button hardButton;
    @FXML private Button homeButton;

    @FXML private Label statusLabel;

    // The instalation

    /**
     * will automatically runs when the FXML loads.
     * Starts the application in the home screen.
     */
    @FXML
    public void initialize() {
        goHome();
    }

    // This the homescreen

    /**
     * Resets everything and returns user to main menu.
     */
    @FXML
    public void goHome() {
        gameOver = true;

        board.resetBoard();
        clearBoardUI();

        statusLabel.setText("Choose a Game Mode");

        showMenu(true);
        homeButton.setVisible(false);
    }

    /**
     * Shows or hides the game mode menu buttons.
     */
    private void showMenu(boolean show) {
        pvpButton.setVisible(show);
        easyButton.setVisible(show);
        hardButton.setVisible(show);
    }

    // The Game Modes

    /**
     * Starts Player vs Player mode.
     */
    @FXML
    public void startPvP() {
        setupGame(new HumanPlayer('X'), new HumanPlayer('O'));
        statusLabel.setText("PvP Mode");
    }

    /**
     * Starts Player vs Easy AI mode.
     */
    @FXML
    public void startEasyAI() {
        setupGame(new HumanPlayer('X'), new EasyAIPlayer('O'));
        statusLabel.setText("Easy AI Mode");
    }

    /**
     * Starts Player vs Hard AI mode.
     */
    @FXML
    public void startHardAI() {
        setupGame(new HumanPlayer('X'), new HardAIPlayer('O'));
        statusLabel.setText("Hard AI Mode");
    }

    /**
     * Shared setup method for all game modes.
     */
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

    // The Player Moves

    /**
     * Handles a move when a board button is clicked.
     */
    private void handleMove(int r, int c, Button button) {

        if (gameOver) return;

        // Try to place move on board
        if (!board.placeMove(r, c, currentPlayer.getSymbol())) return;

        // Update UI
        button.setText(String.valueOf(currentPlayer.getSymbol()));

        // Check if game ended
        if (checkGameEnd()) return;

        switchTurn();

        // If its AIs turn then trigger the AI move
        if (currentPlayer instanceof EasyAIPlayer ||
                currentPlayer instanceof HardAIPlayer) {
            aiMove();
        }
    }

    // Board button handlers
    @FXML public void handle00() { handleMove(0,0,b00); }
    @FXML public void handle01() { handleMove(0,1,b01); }
    @FXML public void handle02() { handleMove(0,2,b02); }

    @FXML public void handle10() { handleMove(1,0,b10); }
    @FXML public void handle11() { handleMove(1,1,b11); }
    @FXML public void handle12() { handleMove(1,2,b12); }

    @FXML public void handle20() { handleMove(2,0,b20); }
    @FXML public void handle21() { handleMove(2,1,b21); }
    @FXML public void handle22() { handleMove(2,2,b22); }

    // All the Ai's logic
    /**
     * Executes AI move and updates the board.
     */
    private void aiMove() {

        int[] move = currentPlayer.makeMove(board);
        if (move == null) return;

        board.placeMove(move[0], move[1], currentPlayer.getSymbol());

        getButton(move[0], move[1])
                .setText(String.valueOf(currentPlayer.getSymbol()));

        if (checkGameEnd()) return;

        switchTurn();
    }

    // The Game Logic

    /**
     * Checks for win or tie conditions.
     */
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

    /**
     * Switches turn between player 1 and player 2.
     */
    private void switchTurn() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    // All the UI handelers

    /**
     * Clears all buttons on the board.
     */
    private void clearBoardUI() {
        b00.setText(""); b01.setText(""); b02.setText("");
        b10.setText(""); b11.setText(""); b12.setText("");
        b20.setText(""); b21.setText(""); b22.setText("");
    }

    /**
     * Gets button reference based on row and column.
     */
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