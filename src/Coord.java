public class Coord {

  int row;
  int col;
  float val;
  int dir;
  boolean goal;

  public Coord(int row, int col, float val) {
    this.row = row;
    this.col = col;
    this.val = val;
    this.dir = 0;
    this.goal = false;
  }

  // gets value of coord
  public float getVal() {
    return this.val;
  }

  // sets coord as goal or not
  public void setGoal(boolean goal) {
    this.goal = goal;
  }
}
