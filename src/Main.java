import java.io.FileNotFoundException;

public class Main {

  public static void main(String[] args) throws FileNotFoundException {
    String filePath;
    int runTime;
    float desDirProb;
    float actRew;

    // check command line arguments
    if (args.length == 4) {
      filePath = args[0];
      runTime = Integer.parseInt(args[1]);
      desDirProb = Float.parseFloat(args[2]);
      actRew = Float.parseFloat(args[3]);
    } else {
      return;
    }

    // print command line arguments
    System.out.println("\nFile Path: " + filePath);
    System.out.println("Time to Run: " + String.valueOf(runTime) + " seconds");
    System.out.println("Direction Probability: " + String.valueOf(desDirProb));
    System.out.println("Reward for Action: " + String.valueOf(actRew) + "\n");

    // parse board
    Parser parser = new Parser(filePath);
    Board board = parser.parseBoard();

    // run algorithm
    RL algo = new RL(board, runTime, desDirProb, actRew);
    algo.run();
  }
}
