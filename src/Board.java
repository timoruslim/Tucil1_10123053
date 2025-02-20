import java.util.ArrayList;

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
              System.out.print(dot);
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
      for (int i = x; i < row; i++) {
          for (int j = y; j < col; j++) {
              if (block.get(i - x).get(j - y) != '·' && this.get(i).get(j) != '·') {
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
      char letter = block.get(0).get(0);
      
      for (int i = x; i < row; i++) {
          for (int j = y; j < col; j++) {
              if (block.get(i - x).get(j - y) != '.') {
                  newBoard.get(i).set(j, letter);
              } else {

              }
          }
      }
      return newBoard;
  }

  public boolean solveBoard() {
      return true;
  }

}