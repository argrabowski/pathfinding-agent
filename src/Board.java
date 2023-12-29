import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Board {

  int rows;
  int cols;
  Coord[][] board;
  Coord pos;

  public Board(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
    this.board = new Coord[rows][cols];
  }

  // gets list of adgacent coords to current coord
  public ArrayList<Coord> getAdjCoords() {
    ArrayList<Coord> adjCoords = new ArrayList<Coord>();
    int posRow = this.pos.row;
    int posCol = this.pos.col;
    if (posRow - 1 >= 0) {
      adjCoords.add(this.board[posRow - 1][posCol]);
    }
    if (posCol + 1 < this.cols) {
      adjCoords.add(this.board[posRow][posCol + 1]);
    }
    if (posRow + 1 < this.rows) {
      adjCoords.add(this.board[posRow + 1][posCol]);
    }
    if (posCol - 1 >= 0) {
      adjCoords.add(this.board[posRow][posCol - 1]);
    }
    return adjCoords;
  }

  // prints list of adgacent coords to current coord
  public void printAdjCoords(ArrayList<Coord> coords) {
    for (int i = 0; i < coords.size(); i++) {
      System.out.println(
        "(" + coords.get(i).row + ", " + coords.get(i).col + ")"
      );
    }
  }

  // prints values of each coord on board
  public void printVals() {
    System.out.println("Board Values:");
    DecimalFormat df = new DecimalFormat("#.##");
    df.setRoundingMode(RoundingMode.CEILING);
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        System.out.print(df.format(this.board[i][j].val) + "\t");
      }
      System.out.println();
    }
  }

  // prints directions of each coord on board
  public void printDirs() {
    String dirStr = "";
    System.out.println("Board Directions:");
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (this.board[i][j].goal) {
          dirStr = "G";
        } else {
          int dir = this.board[i][j].dir;
          switch (dir) {
            case 0:
              dirStr = "^";
              break;
            case 1:
              dirStr = ">";
              break;
            case 2:
              dirStr = "v";
              break;
            case 3:
              dirStr = "<";
              break;
          }
        }
        System.out.print(String.valueOf(dirStr + "\t"));
      }
      System.out.println();
    }
  }
}
