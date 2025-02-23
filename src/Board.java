import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class Board extends ArrayList<ArrayList<Character>> {

    // Board properties
    int n, m, p, cells;
    String type;

    // Algorithm stuff
    int cases = 0;
    HashSet<String> failedStates = new HashSet<>();

    // Constructor
    public Board(int n, int m, int p, String type, Scanner fileScanner) { 
        super(); 
        this.n = n;
        this.m = m;
        this.p = p;
        this.type = type;
        cells = n * m;

        switch(type) {
            case "DEFAULT":
                for (int i = 0; i < n; i++) {
                    ArrayList<Character> row = new ArrayList<>();
                    for (int j = 0; j < m; j++) {
                        row.add('·');
                    }
                    this.add(row);
                }
                break;
            case "CUSTOM":
                cells = 0;
                for (int i = 0; i < n; i++) {
                    char[] input = fileScanner.nextLine().toCharArray(); 
                    ArrayList<Character> row = new ArrayList<>();
                    for (int j = 0; j < m; j++) {
                        if (input[j] == 'X') {
                            row.add('·');
                            cells++;
                        } else {
                            row.add(' ');
                        }
                    }
                    this.add(row);
                }
                break;
            case "PYRAMID":
                System.out.println("Too difficult...");
            
        }
        
    }

    public void displayBoard() {

        // Possible 26 Colors
        String RESET = "\u001B[0m";
        String[] rainbow = {
            "\u001B[38;2;255;0;0m", 
            "\u001B[38;2;255;59;0m",
            "\u001B[38;2;255;118;0m",
            "\u001B[38;2;255;177;0m",
            "\u001B[38;2;255;235;0m",
            "\u001B[38;2;216;255;0m",
            "\u001B[38;2;157;255;0m",
            "\u001B[38;2;98;255;0m",
            "\u001B[38;2;39;255;0m",
            "\u001B[38;2;0;255;20m",
            "\u001B[38;2;0;255;78m",
            "\u001B[38;2;0;255;137m",
            "\u001B[38;2;0;255;196m",
            "\u001B[38;2;0;255;255m",
            "\u001B[38;2;0;196;255m",
            "\u001B[38;2;0;137;255m",
            "\u001B[38;2;0;78;255m",
            "\u001B[38;2;0;20;255m",
            "\u001B[38;2;39;0;255m",
            "\u001B[38;2;98;0;255m",
            "\u001B[38;2;157;0;255m",
            "\u001B[38;2;216;0;255m",
            "\u001B[38;2;255;0;235m",
            "\u001B[38;2;255;0;177m",
            "\u001B[38;2;255;0;118m",
            "\u001B[38;2;255;0;59m" 
        };
        
        // Printing the Board
        Map<Character, Integer> letterColors = new HashMap<>();
        for (ArrayList<Character> row : this) {
            for (char letter : row) {
                if (letter != '·' && letter != ' ') {
                    // map letter to color
                    if (!letterColors.containsKey(letter)) {
                        letterColors.put(letter, (int) letterColors.size() * 25/p);
                    }
                    System.out.print(rainbow[letterColors.get(letter)] + letter + RESET + " "); 
                } else {
                    System.out.print(letter + " ");
                }
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
            for (Block block : blocks) num += block.num;
            if (num != cells) return false;
        }

        // Failed if case has been tried
        if (failedStates.contains(this.toString())) return false;

        // Solved if every piece is placed
        if (blocks.isEmpty()) return true;
        
        // Try every position 
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                // Try every piece
                for (int k = 0; k < blocks.size(); k++) {
                    Block block = blocks.get(k);

                    // Try Every permutation
                    for (Block attemptedBlock : block.permutations) {
                        if (canPlaceBlock(attemptedBlock, i, j)) {

                            // Recursion
                            cases++;

                            Block successfulBlock = blocks.remove(k);
                            placeBlock(attemptedBlock, i, j);

                            if (solveBoard(blocks)) return true;

                            removeBlock(attemptedBlock, i, j);  
                            blocks.add(k, successfulBlock);

                        }
                    }

                }

            }
        }

        return false;

    }

}