import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Parser {

  String filePath;
  int boardRows;
  int boardCols;
  Random rand;

  public Parser(String filePath) {
    this.filePath = filePath;
    this.boardRows = 0;
    this.boardCols = 0;
    this.rand = new Random();
  }

  // parses board file into two dimensional array
  public Board parseBoard() throws FileNotFoundException {
    this.getBoardSize();
    Board board = new Board(this.boardRows, this.boardCols);
    File file = new File(this.filePath);
    Scanner scanner = new Scanner(file);
    int i = 0;
    while (scanner.hasNextLine()) {
      String data = scanner.nextLine();
      String[] spaces = data.split("\t");
      for (int j = 0; j < spaces.length; j++) {
        Coord coord = new Coord(i, j, Float.parseFloat(spaces[j]));
        if (coord.getVal() != 0) {
          coord.setGoal(true);
        } else {
          coord.setGoal(false);
        }
        board.board[i][j] = coord;
      }
      i++;
    }
    int randRow = this.rand.nextInt(this.boardRows);
    int randCol = this.rand.nextInt(this.boardCols);
    Coord start = board.board[randRow][randCol];
    board.pos = start;
    scanner.close();
    return board;
  }

  // gets size of board from file
  public void getBoardSize() throws FileNotFoundException {
    File file = new File(this.filePath);
    Scanner scanner = new Scanner(file);
    while (scanner.hasNextLine()) {
      String data = scanner.nextLine();
      String[] spaces = data.split("\t");
      this.boardCols = spaces.length;
      this.boardRows++;
    }
    scanner.close();
  }
}
