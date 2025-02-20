import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class App {

    static void print(Object thing) {
        System.out.println(thing);
    }

    static void print(Object[] list) {
        for (Object thing : list) {
            System.out.print(thing + " ");
        }
        System.out.println();
    }

    static void print(ArrayList<Block> list) {
        for (Block b : list) {
            b.displayBlock();
            System.out.println();
        }
    }

    static void print(HashSet<Block> list) {
        for (Block b : list) {
            b.displayBlock();
            System.out.println();
        }
    }

    static void print() {
        System.out.println();
    }   

    // array of strings into an array of ints
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
        String path = "../test/Problem_2.txt";
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
        char[] row = fileScanner.nextLine().toCharArray();
        Block block = new Block(row[0]);
        block.addRow(row);
        while (fileScanner.hasNextLine()) {
            char[] newRow = fileScanner.nextLine().toCharArray();  
            if (row[0] != newRow[0]) {
                blocks.add(block);
                block = new Block(newRow[0]);
                block.displayBlock();
                row = newRow;
            }
            block.addRow(newRow);
        }
        blocks.add(block);
        fileScanner.close();

        // Solve Puzzle
        if (board.solveBoard(blocks)) {
            board.displayBoard();
            print("can");
        } else {
            print("cannot");
        }

    }
}
