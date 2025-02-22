import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

public class App {

    static void print(Object thing) {
        System.out.println(thing);
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

    static String getName(File file) {
        String name = file.getName();
        int pos = name.lastIndexOf(".");
        if (pos > 0) {
            name = name.substring(0, pos);
        }
        return name;
    }
    
    public static void main(String[] args) throws Exception {

        // Get File
        Scanner terminalScanner = new Scanner(System.in);
        /* System.out.print("Enter file path: ");
        String path = terminalScanner.nextLine( ); */
        String path = "../test/Problem_1.txt";

        // Read file 
        File file = new File(path);
        Scanner fileScanner = new Scanner(file);

        // Create Board
        int[] dimensions = toInt(fileScanner.nextLine().split(" ")); 
        String type = fileScanner.nextLine();
        Board board = new Board(dimensions[0], dimensions[1], dimensions[2], type);

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
        if (board.solveBoard(blocks)) {
            board.displayBoard();
        } else {
            print("Tidak ada solusi.");
        }
        long end = System.nanoTime();

        // Print info
        print("Waktu pencarian: " + (end - start) / 1000000 + " ms");
        print("Banyak kasus yang ditinjau: " + board.cases);
        
        // Write Output into File
        System.out.print("Apakah anda ingin menyimpan solusi? (ya/tidak) ");
        String answer = terminalScanner.nextLine();
        if (answer.equals("ya")) {
            String solutionPath = "../test/" + file.getName() + "_Solution.txt";
            File solution = new File(solutionPath);
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
        terminalScanner.close();
        
    }
}
