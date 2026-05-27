package math130.gui;

import java.util.ArrayList;
import java.util.Random;

/**
 * Easy AI that chooses a random available spot on the board
 * This is the lowest difficulty mode
 */
public class EasyAIPlayer extends Player {

    private Random random = new Random();
    /**
     * This is the constructor and it takes in the symbol and gives it to the parent player abtract class
     */
    public EasyAIPlayer(char symbol) {
        super(symbol);
    }

    /**
     * Chooses a random empty spot on the board.
     * @param board current game board
     * @return row/col position for AI move
     */
    @Override
    public int[] makeMove(GameBoard board) {

        ArrayList<int[]> emptySpots = new ArrayList<>();
        char[][] b = board.getBoard();

        // This will find all empty spaces
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (b[r][c] == ' ') {
                    emptySpots.add(new int[]{r, c});
                }
            }
        }

        // This will pick a random empty spot
        return emptySpots.get(random.nextInt(emptySpots.size()));
    }
}