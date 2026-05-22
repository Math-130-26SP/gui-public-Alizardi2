package math130.gui;

/**
 * Human player class.
 * Does not calculate moves the UI will handle it instead.
 */
public class HumanPlayer extends Player {

    public HumanPlayer(char symbol) {
        super(symbol);
    }

    @Override
    public int[] makeMove(GameBoard board) {
        return null; // handled by button clicks in GUI
    }
}