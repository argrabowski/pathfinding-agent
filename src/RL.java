import java.util.ArrayList;
import java.util.Random;

public class RL {

  Board board;
  int runTime;
  float desDirProb;
  float actRew;
  Random rand;

  static final float stepSize = (float) 0.1;
  static final float discount = (float) 0.9;
  static final float epsilon = (float) 0.1;

  public RL(Board board, int runTime, float desDirProb, float actRew) {
    this.board = board;
    this.runTime = runTime;
    this.desDirProb = desDirProb;
    this.actRew = actRew;
    this.rand = new Random();
  }

  // run algorithm
  public void run() {
    long curr = System.currentTimeMillis();
    long end = curr + this.runTime * 1000;
    System.out.println("Initial State:");
    this.printState();
    while (System.currentTimeMillis() < end) {
      this.move();
    }
    System.out.println("\nFinal State:");
    this.printState();
  }

  public void printState() {
    this.board.printVals();
    this.board.printDirs();
  }

  // move agent
  public void move() {
    // check if current position is goal
    while (this.board.pos.goal) {
      this.board.pos = this.getRandCoord();
    }
    Coord newPos;
    // get list of adjacent coords to current
    ArrayList<Coord> adjCoords = this.board.getAdjCoords();
    float epsilonRand = this.rand.nextFloat();
    // if exploring, else exploiting
    if (epsilonRand < epsilon) {
      // set new position to random adjacent coord
      int randNum = rand.nextInt(adjCoords.size());
      newPos = adjCoords.get(randNum);
      // set previous value
      this.board.pos.val = this.getNewPosVal(newPos);
    } else {
      newPos = this.getMaxAdjCoord(adjCoords);
      // set previous value and direction before deflection
      this.board.pos.val = this.getNewPosVal(newPos);
      this.board.pos.dir = this.getDirection(newPos, this.board.pos);
      // set deflected coord, based on desDirProb
      newPos = this.getDefCoord(newPos, adjCoords);
    }
    // set old position to new position, repeat
    this.board.pos = newPos;
  }

  // update utility of current position
  public float getNewPosVal(Coord newPos) {
    float oldUtility = this.board.pos.val;
    float diffInVal = newPos.val - this.board.pos.val;
    float newUtility = oldUtility + stepSize * (actRew + discount * diffInVal);
    return newUtility;
  }

  // get random coord on board
  public Coord getRandCoord() {
    int randRow = rand.nextInt(this.board.rows);
    int randCol = rand.nextInt(this.board.cols);
    return this.board.board[randRow][randCol];
  }

  // get maximum adjacent coord to current coord
  public Coord getMaxAdjCoord(ArrayList<Coord> adjCoords) {
    Coord maxAdjCoord = adjCoords.get(0);
    for (int i = 0; i < adjCoords.size(); i++) {
      if (adjCoords.get(i).val > maxAdjCoord.val) {
        maxAdjCoord = adjCoords.get(i);
      }
    }
    return maxAdjCoord;
  }

  // get direction from to and from coords
  public int getDirection(Coord toCord, Coord fromCord) {
    int dir = 0;
    if (toCord.row < fromCord.row) {
      dir = 0;
    } else if (toCord.col > fromCord.col) {
      dir = 1;
    } else if (toCord.row > fromCord.row) {
      dir = 2;
    } else {
      dir = 3;
    }
    return dir;
  }

  // get deflected coord from desired coord
  public Coord getDefCoord(Coord initCoord, ArrayList<Coord> adjCoords) {
    Coord defCoord = initCoord;
    double randNum = this.rand.nextDouble();
    double middle = desDirProb + (1 - desDirProb) / 2;
    if (randNum > desDirProb && randNum < middle) {
      // System.out.println("Deflected Left");
      defCoord = this.getDefLeft(initCoord, adjCoords);
    } else if (randNum > middle && randNum < 1.0) {
      // System.out.println("Deflected Right");
      defCoord = this.getDefRight(initCoord, adjCoords);
    } else {
      // System.out.println("Not Deflected");
    }
    return defCoord;
  }

  // get deflected left coord
  public Coord getDefLeft(Coord initCoord, ArrayList<Coord> adjCoords) {
    Coord defCoord = initCoord;
    int initRow = initCoord.row;
    int initCol = initCoord.col;
    if (initCoord.dir == 0 && initCol - 1 >= 0) {
      defCoord = this.board.board[initRow][initCol - 1];
    } else if (initCoord.dir == 1 && initRow - 1 >= 0) {
      defCoord = this.board.board[initRow - 1][initCol];
    } else if (initCoord.dir == 2 && initCol + 1 < board.cols) {
      defCoord = this.board.board[initRow][initCol + 1];
    } else if (initCoord.dir == 3 && initRow + 1 < board.rows) {
      defCoord = this.board.board[initRow + 1][initCol];
    }
    return defCoord;
  }

  // get deflected right coord
  public Coord getDefRight(Coord initCoord, ArrayList<Coord> adjCoords) {
    Coord defCoord = initCoord;
    int initRow = initCoord.row;
    int initCol = initCoord.col;
    if (initCoord.dir == 0 && initCol + 1 < board.cols) {
      defCoord = this.board.board[initRow][initCol + 1];
    } else if (initCoord.dir == 1 && initRow + 1 < board.rows) {
      defCoord = this.board.board[initRow + 1][initCol];
    } else if (initCoord.dir == 2 && initCol - 1 >= 0) {
      defCoord = this.board.board[initRow][initCol - 1];
    } else if (initCoord.dir == 3 && initRow - 1 >= 0) {
      defCoord = this.board.board[initRow - 1][initCol];
    }
    return defCoord;
  }
}
