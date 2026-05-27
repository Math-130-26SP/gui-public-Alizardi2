package math130.gui;

/**
 * This is hard AI and it will
 * 1. try to win if it cant
 * 2. it will try to block opponent and if it cant
 * 3. it just picks first available spot
 */
public class HardAIPlayer extends Player {
    /**
     * This is the constructor for the Hard Ai and it takes in a symbol and gives it to the super
     */
    public HardAIPlayer(char symbol) {
        super(symbol);
    }

    @Override
    public int[] makeMove(GameBoard board) {

        char[][] b = board.getBoard();

        // Try to win first
        int[] winMove = findBestMove(b, symbol);
        if (winMove != null){
            return winMove;
        }

        // Block opponent
        char opponent;

        if (symbol == 'X') {
            opponent = 'O';
        } else {
            opponent = 'X';
        }
        int[] blockMove = findBestMove(b, opponent);
        if (blockMove != null) {
            return blockMove;
        }

        // Otherwise take first empty spot
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (b[r][c] == ' ') {
                    return new int[]{r, c};
                }
            }
        }

        return null;
    }

    // Finds a move that completes or blocks a line
    private int[] findBestMove(char[][] b, char target) {
        // Check every row

        for (int r = 0; r < 3; r++) {
            if (count(b[r][0], b[r][1], b[r][2], target) == 2) {
                return emptySpot(b, r, 0, r, 1, r, 2);
            }
        }
        // Check every column

        for (int c = 0; c < 3; c++) {
            if (count(b[0][c], b[1][c], b[2][c], target) == 2) {
                return emptySpot(b, 0, c, 1, c, 2, c);
            }
        }
        // Check top-left to bottom-right diagonal

        if (count(b[0][0], b[1][1], b[2][2], target) == 2) {
            return emptySpot(b, 0, 0, 1, 1, 2, 2);
        }
        // Check top-right to bottom-left diagonal

        if (count(b[0][2], b[1][1], b[2][0], target) == 2) {
            return emptySpot(b, 0, 2, 1, 1, 2, 0);
    }

        return null;
    }

    private int count(char a, char b, char c, char target) {
        int count = 0;
        if (a == target){
            count++;
        }
        if (b == target){
            count++;
        }
        if (c == target){
            count++;
        }
        return count;
    }

    private int[] emptySpot(char[][] b, int r1,int c1, int r2,int c2, int r3,int c3) {

        if (b[r1][c1] == ' '){
            return new int[]{r1,c1};
        }
        if (b[r2][c2] == ' ') {
            return new int[]{r2,c2};
        }
        if (b[r3][c3] == ' '){
            return new int[]{r3,c3};
        }
        return null;
    }
}