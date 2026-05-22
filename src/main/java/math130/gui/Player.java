package math130.gui;

/**
 * This Abstract player class used for both the human and the AI players.
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
     * AI will actually override this and Human will returns null since its handled by UI.
     */
    public abstract int[] makeMove(GameBoard board);
}