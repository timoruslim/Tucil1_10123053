import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

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
    }

    public void displayBlock() {
        for (ArrayList<Character> row : this) {
            for (Character letter : row) {
                System.out.print(letter + " ");
            }
            System.out.println();
        }
    }
}

public class App {

    static void printList(String[] list) {
        for (String n : list) {
            System.out.println(n);
        }
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
        String[] dimensions = fileScanner.nextLine().split(" "); 
        String type = fileScanner.nextLine();

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
        
    }
}
