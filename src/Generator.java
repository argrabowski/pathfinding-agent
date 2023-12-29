import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Generator {

  public Generator() throws IOException {
    int lengthOfArray = 8;
    int widthOfArray = 8;
    int numOfPosEnds = 3;
    int numOfNegEnds = 1;
    String[][] array = new String[0][];
    try {
      array =
        generate2DArray(
          lengthOfArray,
          widthOfArray,
          numOfPosEnds,
          numOfNegEnds
        );
    } catch (IOException e) {
      e.printStackTrace();
    }

    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array.length; j++) {
        builder.append(array[i][j] + "\t");
        if (j < array.length - 1) builder.append("");
      }
      builder.append("\n");

      BufferedWriter writer = null;
      try {
        writer = new BufferedWriter(new FileWriter("../board.txt"));
        writer.write(builder.toString());
        writer.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public String[][] generate2DArray(
    int x,
    int y,
    int numOfPosEnds,
    int numOfNegEnds
  )
    throws IOException {
    String[][] array = new String[x][y];

    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array.length; j++) {
        array[i][j] = (Integer.toString(0));
      }
    }

    int size = numOfPosEnds + numOfNegEnds + 1;
    ArrayList<Integer> xCoords = generateUniqueInts(size, x);
    ArrayList<Integer> yCoords = generateUniqueInts(size, x);

    for (int i = 0; i < numOfPosEnds + numOfNegEnds + 1; i++) {
      if (i == 0) {
        array[xCoords.get(i)][yCoords.get(i)] = "0";
      } else if (i > 0 && i < numOfPosEnds) {
        int posEnd = ThreadLocalRandom.current().nextInt(1, 5);
        array[xCoords.get(i)][yCoords.get(i)] = Integer.toString(posEnd);
      } else {
        int negEnd = ThreadLocalRandom.current().nextInt(-5, -1);
        array[xCoords.get(i)][yCoords.get(i)] = Integer.toString(negEnd);
      }
    }

    return array;
  }

  public ArrayList<Integer> generateUniqueInts(int numInts, int size) {
    ArrayList<Integer> allInts = new ArrayList<Integer>();
    ArrayList<Integer> ints = new ArrayList<Integer>();
    for (int i = 0; i < size; i++) {
      allInts.add(i);
    }
    for (int i = 0; i < numInts; i++) {
      int index = ThreadLocalRandom.current().nextInt(0, allInts.size());
      ints.add(allInts.get(index));
      allInts.remove(index);
    }
    return ints;
  }
}
