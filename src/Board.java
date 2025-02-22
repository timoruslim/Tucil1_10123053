import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Board extends ArrayList<ArrayList<Character>> {

    int n, m;
    String type;

    public Board(int n, int m, String type) { 
        super(); 
        this.n = n;
        this.m = m;
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
        for (ArrayList<Character> row : this) {
            for (char dot : row) {
                System.out.print(dot + " ");
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

    public Solution solveBoard(ArrayList<Block> blocks, int iterations) {
        
        Solution result = new Solution(iterations);

        // Failed if more pieces than spaces
        if (iterations == 0) {
            int num = 0;
            for (Block block : blocks) {
                num += block.num;
            }
            if (num > n*m) {
                result.solveable = false;
                return result;
            }
        }
        
        // Solved if every piece is placed
        if (blocks.isEmpty()) {
            result.solveable = true;
            return result;
        }
        
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
                            placeBlock(attemptedBlock, i, j);
                            Solution recursion = solveBoard(blocks, iterations);
                            if (recursion.solveable) {
                                result.solution = this;
                                result.solveable = true;
                                result.iterations += recursion.iterations;
                                return result;
                            } 
                            removeBlock(attemptedBlock, i, j);

                        }
                        iterations++;
                    }
                    blocks.add(block);
                }
            }
        }

        result.solveable = false;
        return result;

    }

}