import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Board extends ArrayList<ArrayList<Character>> {

    int n, m;

    public Board(int n, int m) { 
        super(); 
        this.n = n;
        this.m = m;

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

    public boolean solveBoard(ArrayList<Block> blocks) {
        
        this.displayBoard();
        System.out.println();
        if (blocks.isEmpty()) return true;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < blocks.size(); k++) {
                    Block block = blocks.remove(0);
                    LinkedHashSet<Block> blockPermutations = block.permuteBlock();
                    for (Block attemptedBlock : blockPermutations) {
                        if (canPlaceBlock(attemptedBlock, i, j)) {
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