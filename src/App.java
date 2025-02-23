import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

public class App {

    // array of strings into an array of ints
    static int[] toInt(String[] list) {
        int[] intList = new int[list.length];
        for (int i = 0; i < list.length; i++) {
            intList[i] = Integer.parseInt(list[i]); 
        }
        return intList;
    }

    // get letter representing block
    static char findId(char[] row) {
        for (char letter : row) {
            if (letter != ' ') {
                return letter;
            }
        }
        return ' ';
    }

    // file name without extension
    static String getName(File file) {
        String name = file.getName();
        int pos = name.lastIndexOf(".");
        if (pos > 0) {
            name = name.substring(0, pos); // cut extension
        }
        return name;
    }

    public static void main(String[] args) throws Exception {

        // Get File
        System.out.println();
        Scanner terminalScanner = new Scanner(System.in);
        // System.out.print("Enter file path: ");
        // String path = terminalScanner.nextLine( ); 
        String path = "../test/Problem_10.txt";

        // Read file 
        File file = new File(path);
        Scanner fileScanner = new Scanner(file);

        // Create Board
        int[] dimensions = toInt(fileScanner.nextLine().split(" ")); 
        String type = fileScanner.nextLine();
        Board board = new Board(dimensions[0], dimensions[1], dimensions[2], type, fileScanner);

        // Get the Pieces 
        ArrayList<Block> blocks = new ArrayList<>();
        Block block = null;
        
        while (fileScanner.hasNextLine()) {
            char[] row = fileScanner.nextLine().toCharArray(); 
            char letter = findId(row);

            if (block == null || block.id != letter) {
                if (block != null) {
                    block.permutations = block.permuteBlock();
                    blocks.add(block);
                }
                block = new Block(letter);
            }
            
            block.addRow(row);
        }
        
        // Last Row
        if (block != null) {
            block.permutations = block.permuteBlock();
            blocks.add(block);
        }

        fileScanner.close();

        // Solve Puzzle
        long start = System.nanoTime(); 
        if (board.solveBoard(blocks)) {
            board.displayBoard();
        } else {
            System.out.println("Tidak ada solusi.");
        }
        long end = System.nanoTime();

        // Print info
        System.out.println("\nWaktu pencarian: " + (end - start) / 1000000 + " ms");
        System.out.println("\nBanyak kasus yang ditinjau: " + board.cases);
        
        // Write Output into File
        System.out.print("\nApakah anda ingin menyimpan solusi? (ya/tidak) ");
        String answer = terminalScanner.nextLine();
        if (answer.equals("ya")) {
            File solution = new File("../test/" + getName(file) + "_Solution.txt");
            PrintWriter solutionWriter = new PrintWriter(new FileWriter(solution));
            for (ArrayList<Character> r : board) {
                for (char c : r) {
                    solutionWriter.print(c + " ");
                }
                solutionWriter.println();
            }
            solutionWriter.println("\nWaktu pencarian: " + (end - start) / 1000000 + " ms");
            solutionWriter.print("\nBanyak kasus yang ditinjau: " + board.cases);
            solutionWriter.close();
        }

        // End Program
        System.out.println();
        terminalScanner.close();
        
    }
}
