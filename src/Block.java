import java.util.ArrayList;
import java.util.HashSet;

public class Block extends ArrayList<ArrayList<Character>> {
    
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

  // get all permutations (by rotation and reflection)
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

  // add dots in empty spaces
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
  
  // get width before it is padded
  public int width() {
      int maxWidth = 0;
      for (ArrayList<Character> row : this) {
          maxWidth = Math.max(maxWidth, row.size());          
      }
      return maxWidth;
  }

}