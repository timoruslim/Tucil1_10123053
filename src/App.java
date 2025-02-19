import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;

class Block extends ArrayList<ArrayList<Character>> {
    
    public Block() { 
        super(); 
    }

    public void addRow(char[] row) {
        ArrayList<Character> rowList = new ArrayList<>();
        for (char letter : row) {
            rowList.add(letter);
        }
        this.add(rowList);
        this.padBlock();
    }

    public void displayBlock() {
        for (ArrayList<Character> row : this) {
            for (Character letter : row) {
                System.out.print(letter + " ");
            }
            System.out.println();
        }
    }
    
    public Block rotateBlock() { // rotate 90 degrees clockwise
        Block rotatedBlock = new Block();
        int rows = width();
        int cols = this.size();

        for (int i = 0; i < rows; i++) {
            rotatedBlock.add(new ArrayList<>());
        }
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                char letter = this.get(i).get(j);
                rotatedBlock.get(j).add(0, letter);  
            }
        }
        
        return rotatedBlock;
    }

    public Block reflectBlock() { // reflect about y-axis
        Block reflectedBlock = new Block();
        int rows = this.size();

        for (int i = 0; i < rows; i++) {
            reflectedBlock.add(new ArrayList<>());
            for (char letter : this.get(i)) {
                reflectedBlock.get(i).add(0, letter);  
            }
        }
        
        return reflectedBlock;
    }

    public HashSet<Block> permuteBlock() {
        HashSet<Block> permutations = new HashSet<>();

        Block block = this;
        for (int i = 0; i < 4; i++) {
            block = block.rotateBlock();
            permutations.add(block);
            permutations.add(block.reflectBlock());
        }
        
        return permutations;
    }

    public Block padBlock() {
        Block paddedBlock = new Block();
        int maxWidth = width();
        for (ArrayList<Character> row : this) {
            while (row.size() < maxWidth) {
                row.add('·');
            }
        }
        return paddedBlock;
    }
    
    public int width() {
        int maxWidth = 0;
        for (ArrayList<Character> row : this) {
            maxWidth = Math.max(maxWidth, row.size());          
        }
        return maxWidth;
    }

    public String toString() {
        StringBuilder string = new StringBuilder();
        for (ArrayList<Character> row : this) {
            for (Character letter : row) {
                string.append(letter);
            }
            string.append(' ');
        }
        return string.toString();
    }
}

class Board extends ArrayList<ArrayList<Character>> {

    public Board(int n, int m) { 
        super(); 

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
                System.out.print(dot);
            }
            System.out.println();
        }
    }

}

public class App {

    static void print(String string) {
        System.out.println(string);
    }

    static void print(int integer) {
        System.out.println(integer);
    }

    static void print() {
        System.out.println();
    }

    static void printList(String[] list) {
        for (String n : list) {
            System.out.print(n + " ");
        }
        System.out.println();
    }

    static void printList(ArrayList<Block> list) {
        for (Block b : list) {
            b.displayBlock();
            System.out.println();
        }
    }

    static int[] toInt(String[] list) {
        int[] intList = new int[list.length];
        for (int i = 0; i < list.length; i++) {
            intList[i] = Integer.parseInt(list[i]); 
        }
        return intList;
    }

    public static void main(String[] args) throws Exception {

        // Get File
        Scanner terminalScanner = new Scanner(System.in);
        // System.out.print("Enter file path: ");
        // String path = terminalScanner.nextLine( );
        String path = "../test/Problem_1.txt";
        File file = new File(path);
        terminalScanner.close();

        // Read file 
        Scanner fileScanner = new Scanner(file);
        int[] dimensions = toInt(fileScanner.nextLine().split(" ")); 
        String type = fileScanner.nextLine();

        // Create Board
        Board board = new Board(dimensions[0], dimensions[1]);
        if (type == "CUSTOM") {
            // custom
        } else if (type == "PYRAMID") {
            // pyramid
        }

        // Get the Pieces 
        ArrayList<Block> blocks = new ArrayList<>();
        Block block = new Block();
        char[] row = fileScanner.nextLine().toCharArray();
        block.addRow(row);
        while (fileScanner.hasNextLine()) {
            char[] newRow = fileScanner.nextLine().toCharArray();   
            if (row[0] != newRow[0]) {
                blocks.add(block);
                block = new Block();
                row = newRow;
            }
            block.addRow(newRow);
        }
        blocks.add(block);
        fileScanner.close();
        
        // Permute all Pieces
        ArrayList<Block> possibleBlocks = new ArrayList<>();
        for (Block b : blocks) {
            HashSet<Block> perms = b.permuteBlock();
            for (Block perm : perms) {
                possibleBlocks.add(perm);
            }
        }
        
        
        
    }
}
