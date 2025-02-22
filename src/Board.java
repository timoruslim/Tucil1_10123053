import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class Board extends ArrayList<ArrayList<Character>> {

    int n, m, p;
    String type;
    int cases = 0;

    public Board(int n, int m, int p, String type) { 
        super(); 
        this.n = n;
        this.m = m;
        this.p = p;
        this.type = type;

        for (int i = 0; i < n; i++) {
            ArrayList<Character> row = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                row.add('·');
            }
            this.add(row);
        }
    }

    public void displayBoard() {

        // Possible 26 Colors
        String RESET = "\u001B[0m";
        String[] rainbowColors = {
            "\u001B[38;5;196m", // Red
            "\u001B[38;5;202m", // Reddish-Orange
            "\u001B[38;5;208m", // Orange
            "\u001B[38;5;214m", // Yellow-Orange
            "\u001B[38;5;220m", // Yellow
            "\u001B[38;5;226m", // Yellow-Green
            "\u001B[38;5;190m", // Greenish-Yellow
            "\u001B[38;5;154m", // Lime Green
            "\u001B[38;5;118m", // Green
            "\u001B[38;5;46m",  // Bright Green
            "\u001B[38;5;47m",  // Cyan-Green
            "\u001B[38;5;51m",  // Cyan
            "\u001B[38;5;39m",  // Light Blue-Cyan
            "\u001B[38;5;33m",  // Sky Blue
            "\u001B[38;5;27m",  // Blue
            "\u001B[38;5;21m",  // Deep Blue
            "\u001B[38;5;57m",  // Indigo
            "\u001B[38;5;93m",  // Purple-Blue
            "\u001B[38;5;129m", // Purple
            "\u001B[38;5;165m", // Magenta-Purple
            "\u001B[38;5;201m", // Magenta
            "\u001B[38;5;200m", // Pink-Magenta
            "\u001B[38;5;206m", // Pink
            "\u001B[38;5;212m", // Light Pink
            "\u001B[38;5;218m", // Soft Pink
            "\u001B[38;5;224m"  // Very Light Pink
        };
        
        // Printing the Board
        Map<Character, Integer> letterColors = new HashMap<>();
        for (ArrayList<Character> row : this) {
            for (char letter : row) {
                if (letter != '·') {
                    // map letter to color
                    if (!letterColors.containsKey(letter)) {
                        letterColors.put(letter, (int) letterColors.size() * 26/p);
                    }
                    System.out.print(rainbowColors[letterColors.get(letter)] + letter + RESET + " "); 
                } else {
                    System.out.print(letter + " ");
                }
            }
            System.out.println();
        }
        System.out.println();

    }

    public boolean canPlaceBlock(Block block, int x, int y) {
        int row = block.size();
        int col = block.get(0).size();

        if (x + row > n || y + col > m) {
            return false;
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (block.get(i).get(j) != '·' && this.get(i + x).get(j + y) != '·') {
                    return false;
                } 
            }
        }
        return true;
    }
    
    public Board placeBlock(Block block, int x, int y) {
        Board newBoard = this;
        int row = block.size();
        int col = block.get(0).size();
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (block.get(i).get(j) != '·') {
                    newBoard.get(i + x).set(j + y, block.id);
                } 
            }
        }
        return newBoard;
    }

    public Board removeBlock(Block block, int x, int y) {
        Board newBoard = this;
        int row = block.size();
        int col = block.get(0).size();
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (block.get(i).get(j) == block.id) {
                    newBoard.get(i + x).set(j + y, '·');
                } 
            }
        }
        return newBoard;
    }

    public boolean isFull() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (this.get(i).get(j) == '·') {
                    return false;
                } 
            }
        } 
        return true;
    }

    public boolean solveBoard(ArrayList<Block> blocks) {
        
        // Failed if more pieces than spaces
        if (cases == 0) {
            int num = 0;
            for (Block block : blocks) {
                num += block.num;
            }
            if (num > n * m) return false;
        }
        
        // Solved if every piece is placed
        if (blocks.isEmpty()) return true;
        
        // Try every position 
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (this.get(i).get(j) != '·') continue;

                // Try every piece
                for (int k = 0; k < blocks.size(); k++) {
                    Block block = blocks.remove(0);

                    // Try Every permutation
                    LinkedHashSet<Block> blockPermutations = block.permuteBlock();
                    for (Block attemptedBlock : blockPermutations) {
                        if (canPlaceBlock(attemptedBlock, i, j)) {

                            // Recursion
                            cases++;
                            placeBlock(attemptedBlock, i, j);
                            if (solveBoard(blocks)) {
                                return true;
                            } 
                            removeBlock(attemptedBlock, i, j);

                        }
                    }
                    blocks.add(block);

                }

            }
        }

        return false;

    }

}