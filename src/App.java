import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class App {

    static void print(Object thing) {
        System.out.println(thing);
        System.out.println();
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

    static void print(LinkedHashSet<Block> list) {
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

    static char findId(char[] row) {
        for (char letter : row) {
            if (letter != ' ') {
                return letter;
            }
        }
        return ' ';
    }

    public static void main(String[] args) throws Exception {

        // Get File
        Scanner terminalScanner = new Scanner(System.in);
        // System.out.print("Enter file path: ");
        // String path = terminalScanner.nextLine( );
        String path = "../test/Problem_1.txt";
        terminalScanner.close();

        // Read file 
        File file = new File(path);
        Scanner fileScanner = new Scanner(file);

        // Create Board
        int[] dimensions = toInt(fileScanner.nextLine().split(" ")); 
        String type = fileScanner.nextLine();
        Board board = new Board(dimensions[0], dimensions[1], type);

        // Get the Pieces 
        ArrayList<Block> blocks = new ArrayList<>();
        char[] row = fileScanner.nextLine().toCharArray();
        Block block = new Block(findId(row));
        block.addRow(row);
        while (fileScanner.hasNextLine()) {
            char[] newRow = fileScanner.nextLine().toCharArray();  
            char letter = findId(newRow);

            if (block.id != letter) {
                block.permutations = block.permuteBlock();
                blocks.add(block);
                block = new Block(letter);
                row = newRow;
            }
            
            block.addRow(newRow);
        }
        blocks.add(block);
        fileScanner.close();

        // Solve Puzzle
        long start = System.nanoTime(); 
        Solution result = board.solveBoard(blocks, 0);
        if (result.solveable) {
            board.displayBoard();
        } else {
            print("Tidak ada solusi.");
        }
        long end = System.nanoTime();
        print("Waktu pencarian: " + (end - start) / 1000000 + " ms");
        print("Banyak kasus yang ditinjau: " + result.iterations);
    }
}
