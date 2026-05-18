package math130.gui;

/**
 * Abstract player class used for both human and AI players.
 */
public abstract class Player {

    protected char symbol;

    public Player(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    /**
     * Returns the move the player wants to make.
     * AI overrides this. Human returns null (handled by UI).
     */
    public abstract int[] makeMove(GameBoard board);
}