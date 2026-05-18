package math130.gui;

import java.util.ArrayList;
import java.util.Random;

public class EasyAIPlayer extends Player {

    private Random random = new Random();

    public EasyAIPlayer(char symbol) {
        super(symbol);
    }

    @Override
    public int[] makeMove(GameBoard board) {

        ArrayList<int[]> emptySpots = new ArrayList<>();
        char[][] b = board.getBoard();

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (b[r][c] == ' ') {
                    emptySpots.add(new int[]{r, c});
                }
            }
        }

        return emptySpots.get(random.nextInt(emptySpots.size()));
    }
}