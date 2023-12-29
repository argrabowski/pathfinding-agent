# RL-PathfindingAgent

## How to Run

**You must use java 8 to run this project. Also, you must install the JDK, which contains both java and javac.**

### Main

To compile, enter the ./src directory and run:

```
javac Main.java
```

To execute, enter the ./src directory and run:

```
java Main [filePath] [runTime] [desDirProb] [actRew]
```

- `filePath` is the name of the file to read in representing the map
- `runTime` is how long to learn (in seconds)
- `desDirProb` is the probability of moving in the desired direction upon taking an action
- `actRew` is the constant reward for each action

#### Output

Output is expressed in directions and board values, where each value is the expected reward from
that position, and directions are expressed as `^`, `>`, `v`, and `<`. On the board of directions,
the goal states are expressed as `G`.

### Board Generator

To compile, enter the ./src directory and run:

```
javac GenerateBoard.java
```

To execute, enter the ./src directory and run:

```
java GenerateBoard
```
