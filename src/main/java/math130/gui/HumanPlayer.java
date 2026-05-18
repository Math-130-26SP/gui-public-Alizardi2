package math130.gui;

public class HumanPlayer extends Player {

    public HumanPlayer(char symbol) {
        super(symbol);
    }

    @Override
    public int[] makeMove(GameBoard board) {
        return null; // GUI will handle moves directly
    }
}