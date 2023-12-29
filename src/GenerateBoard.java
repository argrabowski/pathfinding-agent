import java.io.IOException;

public class GenerateBoard {

  public static void main(String[] args) {
    //generates new board
    // you can change the size and number of exits in the Generator class
    try {
      new Generator();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
