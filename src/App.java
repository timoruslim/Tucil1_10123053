import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;

public class App {

    static void print(Object thing) {
        System.out.println(thing);
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
