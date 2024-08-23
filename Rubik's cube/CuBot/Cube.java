
//Imports
import java.util.ArrayList;
import java.util.List;

public class Cube implements Comparable<Cube> {
    // Default parameters
    private int cubeSize = 3; // Every side is cubeSize x cubeSize

    // Cube
    private int sides; // Number of sides requested (K)
    private String[][][] block;

    private String moves; // Moves made to solve the cube

    // Heuristic's parameters
    private Cube father;
    private int rootCost; // g(n)
    private int heuristicCost; // h(n)

    // Constructor
    public Cube(int sides, int occasion) {
        this.sides = sides;

        this.moves = "";

        // Default costs
        this.rootCost = 0;
        this.heuristicCost = 0;

        // Colors
        List<String> colors = new ArrayList<>();
        // White
        for (int i = 0; i < cubeSize * cubeSize; i++) {
            colors.add("W");
        }
        // Green
        for (int i = 0; i < cubeSize * cubeSize; i++) {
            colors.add("G");
        }
        // Red
        for (int i = 0; i < cubeSize * cubeSize; i++) {
            colors.add("R");
        }
        // Blue
        for (int i = 0; i < cubeSize * cubeSize; i++) {
            colors.add("B");
        }
        // Orange
        for (int i = 0; i < cubeSize * cubeSize; i++) {
            colors.add("O");
        }
        // Yellow
        for (int i = 0; i < cubeSize * cubeSize; i++) {
            colors.add("Y");
        }

        // Create cube
        this.block = new String[6][cubeSize][cubeSize];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < cubeSize; j++) {
                for (int z = 0; z < cubeSize; z++) {
                    this.block[i][j][z] = colors.remove(0);
                }
            }
        }

        String moves = ""; // Moves made to randomize the cube

        if (occasion == 0) {
            // Randomized cube
            int n = 1 + (int) (Math.random() * (6)); // Number of moves

            int move; // Move

            for (int i = 0; i < n; i++) {
                move = (int) (Math.random() * (8));

                switch (move) {
                    case 0:
                        this.moveU(true);
                        moves = moves + "U ";
                        break;
                    case 1:
                        this.moveE(true);
                        moves = moves + "E ";
                        break;
                    case 2:
                        this.moveD(true);
                        moves = moves + "D ";
                        break;
                    case 3:
                        this.moveF(true);
                        moves = moves + "F ";
                        break;
                    case 4:
                        this.moveS(true);
                        moves = moves + "S ";
                        break;
                    case 5:
                        this.moveB(true);
                        moves = moves + "B ";
                        break;
                    case 6:
                        this.moveR(true);
                        moves = moves + "R ";
                        break;
                    case 7:
                        this.moveM(true);
                        moves = moves + "M ";
                        break;
                    case 8:
                        this.moveL(true);
                        moves = moves + "L ";
                        break;
                }
            }
        } else if (occasion == 1) {
            // Testing cube 1
            this.moveM(true);
            this.moveU(true);
            this.moveS(false);
            this.moveD(true);

            moves = "M U S' D";
        } else if (occasion == 2) {
            // Testing cube 2
            this.moveU(false);
            this.moveE(true);
            this.moveS(false);

            moves = "U' E S'";
        } else if (occasion == 3) {
            // Testing cube 3
            this.moveF(true);
            this.moveE(true);
            this.moveU(true);
            this.moveM(false);
            this.moveR(true);

            moves = "F E U M' R";
        } else if (occasion == 4) {
            // Testing cube 4
            this.moveF(false);
            this.moveD(true);
            this.moveS(true);
            this.moveF(true);

            moves = "F' D S F";
        } else if (occasion == 5) {
            // Testing cube 5
            this.moveU(false);
            this.moveE(true);
            this.moveS(false);
            this.moveF(false);
            this.moveD(true);

            moves = "U' E S' F' D";
        } else {
            // Testing cube 6
            this.moveU(false);
            this.moveE(true);
            this.moveS(false);
            this.moveF(false);
            this.moveD(true);
            this.moveF(true);

            moves = "U' E S' F' D F";
        }

        // Print the randomizing moves
        System.out.println("Moves made to randomize: " + moves);
    }

    // Copy contrsuctor
    public Cube(Cube copy) {
        this.setSides(copy.getSides());
        this.setBlock(copy.getBlock());
        this.setRootCost(copy.getRootCost() + 1);
        this.setFather(copy);
    }

    // Getters
    public int getSides() {
        return this.sides;
    }

    public String[][][] getBlock() {
        return this.block;
    }

    public int getRootCost() {
        return this.rootCost;
    }

    public int getHeuristicCost() {
        return this.heuristicCost;
    }

    public Cube getFather() {
        return this.father;
    }

    public String getMoves() {
        return this.moves;
    }

    public ArrayList<Cube> getChildren() {
        ArrayList<Cube> children = new ArrayList<>(18);
        Cube child;

        // Move U
        child = new Cube(this);
        child.moveU(true);
        child.countHeuristicCost();
        children.add(child);

        // Move U'
        child = new Cube(this);
        child.moveU(false);
        child.countHeuristicCost();
        children.add(child);

        // Move E
        child = new Cube(this);
        child.moveE(true);
        child.countHeuristicCost();
        children.add(child);

        // Move E'
        child = new Cube(this);
        child.moveE(false);
        child.countHeuristicCost();
        children.add(child);

        // Move D
        child = new Cube(this);
        child.moveD(true);
        child.countHeuristicCost();
        children.add(child);

        // Move D'
        child = new Cube(this);
        child.moveD(false);
        child.countHeuristicCost();
        children.add(child);

        // Move F
        child = new Cube(this);
        child.moveF(true);
        child.countHeuristicCost();
        children.add(child);

        // Move F'
        child = new Cube(this);
        child.moveF(false);
        child.countHeuristicCost();
        children.add(child);

        // Move S
        child = new Cube(this);
        child.moveS(true);
        child.countHeuristicCost();
        children.add(child);

        // Move S'
        child = new Cube(this);
        child.moveS(false);
        child.countHeuristicCost();
        children.add(child);

        // Move B
        child = new Cube(this);
        child.moveB(true);
        child.countHeuristicCost();
        children.add(child);

        // Move B'
        child = new Cube(this);
        child.moveB(false);
        child.countHeuristicCost();
        children.add(child);

        // Move R
        child = new Cube(this);
        child.moveR(true);
        child.countHeuristicCost();
        children.add(child);

        // Move R'
        child = new Cube(this);
        child.moveR(false);
        child.countHeuristicCost();
        children.add(child);

        // Move M
        child = new Cube(this);
        child.moveM(true);
        child.countHeuristicCost();
        children.add(child);

        // Move M'
        child = new Cube(this);
        child.moveM(false);
        child.countHeuristicCost();
        children.add(child);

        // Move L
        child = new Cube(this);
        child.moveL(true);
        child.countHeuristicCost();
        children.add(child);

        // Move L'
        child = new Cube(this);
        child.moveL(false);
        child.countHeuristicCost();
        children.add(child);

        return children;
    }

    // Setters
    public void setSides(int sides) {
        this.sides = sides;
    }

    public void setBlock(String[][][] block) {
        // Create new cube
        this.block = new String[6][cubeSize][cubeSize];

        // Hard copy the given cube
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < cubeSize; j++) {
                for (int z = 0; z < cubeSize; z++) {
                    this.block[i][j][z] = block[i][j][z];
                }
            }
        }
    }

    public void setFather(Cube father) {
        this.father = father;
    }

    public void setRootCost(int rootCost) {
        this.rootCost = rootCost;
    }

    public void addMove(String move) {
        if (this.getFather() != null) {
            this.moves = this.getFather().getMoves() + " " + move;
        }
    }

    // Print cube
    public void print() {
        for (int i = 0; i < 6; i++) {
            System.out.printf("%s %d \n", "Side", (i + 1));
            System.out.printf("%s \n", "-------");

            for (int j = 0; j < cubeSize; j++) {
                for (int z = 0; z < cubeSize; z++) {
                    System.out.printf("%s", "|");
                    System.out.printf("%s", block[i][j][z]);
                }

                System.out.printf("%s \n", "|");
            }

            System.out.printf("%s \n", "-------");
        }
    }

    // Check if cube is in final state
    public boolean isFinal() {
        int counter = 6;

        // For every side of the cube
        for (int i = 0; i < 6; i++) {
            String tester = this.block[i][0][0];
            outLoop: for (int j = 0; j < cubeSize; j++) {
                for (int z = 0; z < cubeSize; z++) {
                    // If a piece has different color from the side's 1st piece
                    if (this.block[i][j][z] != tester) {
                        // This side is not in final state
                        counter = counter - 1;
                        break outLoop;
                    }
                }
            }
        }

        // Compare cube's sides in final state with the requested number of sides
        if (counter >= this.sides)
            return true;
        return false;
    }

    // Compare cubes based on the A* algorithm and the heuretistic
    @Override
    public int compareTo(Cube o) {
        if ((this.getHeuristicCost() + this.getRootCost()) < (o.getHeuristicCost() + o.getRootCost())) {
            return -1;
        } else if ((this.getHeuristicCost() + this.getRootCost()) > (o.getHeuristicCost() + o.getRootCost())) {
            return 1;
        }
        return 0;
    }

    // Moves
    private void moveU(boolean clockwise) {
        if (clockwise) {
            this.addMove("U");

            // Rotate the row
            String[] tempArray = new String[cubeSize];

            for (int z = 0; z < cubeSize; z++) {
                tempArray[z] = this.block[0][0][z];
            }

            for (int i = 0; i < cubeSize; i++) {
                for (int z = 0; z < cubeSize; z++) {
                    this.block[i][0][z] = this.block[i + 1][0][z];
                }
            }

            for (int z = 0; z < cubeSize; z++) {
                this.block[3][0][z] = tempArray[z];
            }

            // Rotate the dependent side
            for (int i = 0; i < cubeSize / 2; i++) {
                for (int j = i; j < cubeSize - i - 1; j++) {
                    String temp = this.block[4][i][j];
                    this.block[4][i][j] = this.block[4][j][cubeSize - i - 1];
                    this.block[4][j][cubeSize - i - 1] = this.block[4][cubeSize - i - 1][cubeSize - j - 1];
                    this.block[4][cubeSize - i - 1][cubeSize - j - 1] = this.block[4][cubeSize - j - 1][i];
                    this.block[4][cubeSize - j - 1][i] = temp;
                }
            }
        } else {
            this.addMove("U'");

            // Rotate the row
            String[] tempArray = new String[cubeSize];

            for (int z = 0; z < cubeSize; z++) {
                tempArray[z] = this.block[3][0][z];
            }

            for (int i = cubeSize; i > 0; i--) {
                for (int z = 0; z < cubeSize; z++) {
                    this.block[i][0][z] = this.block[i - 1][0][z];
                }
            }

            for (int z = 0; z < cubeSize; z++) {
                this.block[0][0][z] = tempArray[z];
            }

            // Rotate the dependent side
            for (int i = 0; i < cubeSize / 2; i++) {
                for (int j = i; j < cubeSize - i - 1; j++) {
                    String temp = this.block[4][i][j];
                    this.block[4][i][j] = this.block[4][cubeSize - j - 1][i];
                    this.block[4][cubeSize - j - 1][i] = this.block[4][cubeSize - i - 1][cubeSize - j - 1];
                    this.block[4][cubeSize - i - 1][cubeSize - j - 1] = this.block[4][j][cubeSize - i - 1];
                    this.block[4][j][cubeSize - i - 1] = temp;
                }
            }
        }
    }

    private void moveE(boolean clockwise) {
        if (clockwise) {
            this.addMove("E");

            // Rotate the row
            String[] tempArray = new String[cubeSize];

            for (int z = 0; z < cubeSize; z++) {
                tempArray[z] = this.block[3][1][z];
            }

            for (int i = cubeSize; i > 0; i--) {
                for (int z = 0; z < cubeSize; z++) {
                    this.block[i][1][z] = this.block[i - 1][1][z];
                }
            }

            for (int z = 0; z < cubeSize; z++) {
                this.block[0][1][z] = tempArray[z];
            }
        } else {
            this.addMove("E'");

            // Rotate the row
            String[] tempArray = new String[cubeSize];

            for (int z = 0; z < cubeSize; z++) {
                tempArray[z] = this.block[0][1][z];
            }

            for (int i = 0; i < cubeSize; i++) {
                for (int z = 0; z < cubeSize; z++) {
                    this.block[i][1][z] = this.block[i + 1][1][z];
                }
            }

            for (int z = 0; z < cubeSize; z++) {
                this.block[3][1][z] = tempArray[z];
            }
        }
    }

    private void moveD(boolean clockwise) {
        if (clockwise) {
            this.addMove("D");

            // Rotate the row
            String[] tempArray = new String[cubeSize];

            for (int z = 0; z < cubeSize; z++) {
                tempArray[z] = this.block[3][2][z];
            }

            for (int i = cubeSize; i > 0; i--) {
                for (int z = 0; z < cubeSize; z++) {
                    this.block[i][2][z] = this.block[i - 1][2][z];
                }
            }

            for (int z = 0; z < cubeSize; z++) {
                this.block[0][2][z] = tempArray[z];
            }

            // Rotate the dependent side
            for (int i = 0; i < cubeSize / 2; i++) {
                for (int j = i; j < cubeSize - i - 1; j++) {
                    String temp = this.block[5][i][j];
                    this.block[5][i][j] = this.block[5][j][cubeSize - i - 1];
                    this.block[5][j][cubeSize - i - 1] = this.block[5][cubeSize - i - 1][cubeSize - j - 1];
                    this.block[5][cubeSize - i - 1][cubeSize - j - 1] = this.block[5][cubeSize - j - 1][i];
                    this.block[5][cubeSize - j - 1][i] = temp;
                }
            }
        } else {
            this.addMove("D'");

            // Rotate the row
            String[] tempArray = new String[cubeSize];

            for (int z = 0; z < cubeSize; z++) {
                tempArray[z] = this.block[0][2][z];
            }

            for (int i = 0; i < cubeSize; i++) {
                for (int z = 0; z < cubeSize; z++) {
                    this.block[i][2][z] = this.block[i + 1][2][z];
                }
            }

            for (int z = 0; z < cubeSize; z++) {
                this.block[3][2][z] = tempArray[z];
            }

            // Rotate the dependent side
            for (int i = 0; i < cubeSize / 2; i++) {
                for (int j = i; j < cubeSize - i - 1; j++) {
                    String temp = this.block[5][i][j];
                    this.block[5][i][j] = this.block[5][cubeSize - j - 1][i];
                    this.block[5][cubeSize - j - 1][i] = this.block[5][cubeSize - i - 1][cubeSize - j - 1];
                    this.block[5][cubeSize - i - 1][cubeSize - j - 1] = this.block[5][j][cubeSize - i - 1];
                    this.block[5][j][cubeSize - i - 1] = temp;
                }
            }
        }
    }

    private void moveR(boolean clockwise) {
        if (clockwise) {
            this.addMove("R");

            // Rotate the column
            String[] tempArray = new String[cubeSize];

            for (int i = 0; i < cubeSize; i++) {
                tempArray[i] = this.block[2][2 - i][0];
            }

            this.block[2][0][0] = this.block[4][2][2];
            this.block[2][1][0] = this.block[4][1][2];
            this.block[2][2][0] = this.block[4][0][2];

            int[] nums = { 4, 0, 5 };
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < cubeSize; j++) {
                    this.block[nums[i]][j][2] = this.block[nums[i + 1]][j][2];
                }
            }

            for (int i = 0; i < cubeSize; i++) {
                this.block[5][i][2] = tempArray[i];
            }

            // Rotate the dependent side
            for (int i = 0; i < cubeSize / 2; i++) {
                for (int j = i; j < cubeSize - i - 1; j++) {
                    String temp = this.block[1][i][j];
                    this.block[1][i][j] = this.block[1][cubeSize - j - 1][i];
                    this.block[1][cubeSize - j - 1][i] = this.block[1][cubeSize - i - 1][cubeSize - j - 1];
                    this.block[1][cubeSize - i - 1][cubeSize - j - 1] = this.block[1][j][cubeSize - i - 1];
                    this.block[1][j][cubeSize - i - 1] = temp;
                }
            }
        } else {
            this.addMove("R'");
            this.moveR(true);
            this.moveR(true);
            this.moveR(true);
        }
    }

    private void moveM(boolean clockwise) {
        if (clockwise) {
            this.addMove("M");

            // Rotate the column
            String[] tempArray = new String[cubeSize];

            for (int i = 0; i < cubeSize; i++) {
                tempArray[i] = this.block[2][2 - i][1];
            }

            this.block[2][0][1] = this.block[5][2][1];
            this.block[2][1][1] = this.block[5][1][1];
            this.block[2][2][1] = this.block[5][0][1];

            int[] nums = { 5, 0, 4 };
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < cubeSize; j++) {
                    this.block[nums[i]][j][1] = this.block[nums[i + 1]][j][1];
                }
            }

            for (int i = 0; i < cubeSize; i++) {
                this.block[4][i][1] = tempArray[i];
            }
        } else {
            this.addMove("M'");
            this.moveM(true);
            this.moveM(true);
            this.moveM(true);
        }
    }

    private void moveL(boolean clockwise) {
        if (clockwise) {
            this.addMove("L");

            // Rotate the column
            String[] tempArray = new String[cubeSize];

            for (int i = 0; i < cubeSize; i++) {
                tempArray[i] = this.block[2][2 - i][2];
            }

            this.block[2][0][2] = this.block[5][2][0];
            this.block[2][1][2] = this.block[5][1][0];
            this.block[2][2][2] = this.block[5][0][0];

            int[] nums = { 5, 0, 4 };
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < cubeSize; j++) {
                    this.block[nums[i]][j][0] = this.block[nums[i + 1]][j][0];
                }
            }

            for (int i = 0; i < cubeSize; i++) {
                this.block[4][i][0] = tempArray[i];
            }

            // Rotate the dependent side
            for (int i = 0; i < cubeSize / 2; i++) {
                for (int j = i; j < cubeSize - i - 1; j++) {
                    String temp = this.block[3][i][j];
                    this.block[3][i][j] = this.block[3][cubeSize - j - 1][i];
                    this.block[3][cubeSize - j - 1][i] = this.block[3][cubeSize - i - 1][cubeSize - j - 1];
                    this.block[3][cubeSize - i - 1][cubeSize - j - 1] = this.block[3][j][cubeSize - i - 1];
                    this.block[3][j][cubeSize - i - 1] = temp;
                }
            }

        } else {
            this.addMove("L'");
            this.moveL(true);
            this.moveL(true);
            this.moveL(true);
        }
    }

    private void moveF(boolean clockwise) {
        if (clockwise) {
            this.addMove("F");

            // Rotate the side
            for (int i = 0; i < cubeSize / 2; i++) {
                for (int j = i; j < cubeSize - i - 1; j++) {
                    String temp = this.block[0][i][j];
                    this.block[0][i][j] = this.block[0][cubeSize - j - 1][i];
                    this.block[0][cubeSize - j - 1][i] = this.block[0][cubeSize - i - 1][cubeSize - j - 1];
                    this.block[0][cubeSize - i - 1][cubeSize - j - 1] = this.block[0][j][cubeSize - i - 1];
                    this.block[0][j][cubeSize - i - 1] = temp;
                }
            }

            // Rotate the depended column
            String[] tempArray = new String[cubeSize];

            for (int i = 0; i < cubeSize; i++) {
                tempArray[i] = this.block[1][2 - i][0];
            }

            this.block[1][0][0] = this.block[4][2][0];
            this.block[1][1][0] = this.block[4][2][1];
            this.block[1][2][0] = this.block[4][2][2];

            this.block[4][2][0] = this.block[3][2][2];
            this.block[4][2][1] = this.block[3][1][2];
            this.block[4][2][2] = this.block[3][0][2];

            this.block[3][2][2] = this.block[5][0][2];
            this.block[3][1][2] = this.block[5][0][1];
            this.block[3][0][2] = this.block[5][0][0];

            for (int i = 0; i < cubeSize; i++) {
                this.block[5][0][i] = tempArray[i];
            }

        } else {
            this.addMove("F'");
            this.moveF(true);
            this.moveF(true);
            this.moveF(true);
        }
    }

    private void moveS(boolean clockwise) {
        if (clockwise) {
            this.addMove("S");

            // Rotate the column
            String[] tempArray = new String[cubeSize];

            for (int i = 0; i < cubeSize; i++) {
                tempArray[i] = this.block[1][2 - i][1];
            }

            this.block[1][0][1] = this.block[4][1][0];
            this.block[1][1][1] = this.block[4][1][1];
            this.block[1][2][1] = this.block[4][1][2];

            this.block[4][1][0] = this.block[3][2][1];
            this.block[4][1][1] = this.block[3][1][1];
            this.block[4][1][2] = this.block[3][0][1];

            this.block[3][2][1] = this.block[5][1][2];
            this.block[3][1][1] = this.block[5][1][1];
            this.block[3][0][1] = this.block[5][1][0];

            for (int i = 0; i < cubeSize; i++) {
                this.block[5][1][i] = tempArray[i];
            }

        } else {
            this.addMove("S'");
            this.moveS(true);
            this.moveS(true);
            this.moveS(true);
        }
    }

    private void moveB(boolean clockwise) {
        if (clockwise) {
            this.addMove("B");

            // Rotate the side
            for (int i = 0; i < cubeSize / 2; i++) {
                for (int j = i; j < cubeSize - i - 1; j++) {
                    String temp = this.block[2][i][j];
                    this.block[2][i][j] = this.block[2][cubeSize - j - 1][i];
                    this.block[2][cubeSize - j - 1][i] = this.block[2][cubeSize - i - 1][cubeSize - j - 1];
                    this.block[2][cubeSize - i - 1][cubeSize - j - 1] = this.block[2][j][cubeSize - i - 1];
                    this.block[2][j][cubeSize - i - 1] = temp;
                }
            }

            // Rotate the column
            String[] tempArray = new String[cubeSize];

            for (int i = 0; i < cubeSize; i++) {
                tempArray[i] = this.block[1][i][2];
            }

            this.block[1][0][2] = this.block[5][2][2];
            this.block[1][1][2] = this.block[5][2][1];
            this.block[1][2][2] = this.block[5][2][0];

            this.block[5][2][2] = this.block[3][2][0];
            this.block[5][2][1] = this.block[3][1][0];
            this.block[5][2][0] = this.block[3][0][0];

            this.block[3][2][0] = this.block[4][0][0];
            this.block[3][1][0] = this.block[4][0][1];
            this.block[3][0][0] = this.block[4][0][2];

            for (int i = 0; i < cubeSize; i++) {
                this.block[4][0][i] = tempArray[i];
            }

        } else {
            this.addMove("B'");
            this.moveB(true);
            this.moveB(true);
            this.moveB(true);
        }
    }

    // Calculate h(n)
    void countHeuristicCost() {
        ArrayList<Cost> Cost = heuristic();
        boolean found;

        // Finds the side and the colour of the minimum cost
        int minSide = -1;
        int minCost = 10;
        String minColour = " ";

        for (int i = 0; i < this.sides; i++) {
            for (int z = 0; z < Cost.size(); z++) {
                if (i == 0) {
                    if (Cost.get(z).getCounter() < minCost) {
                        minCost = Cost.get(z).getCounter();
                        minSide = Cost.get(z).getSide();
                        minColour = Cost.get(z).getColour();
                    }
                } // Prevents a variable from taking a value for second time
                else {
                    found = true;
                    if (minSide == Cost.get(z).getSide())
                        found = false;
                    else if (minColour == Cost.get(z).getColour())
                        found = false;

                    if (found == true && Cost.get(z).getCounter() < minCost) {
                        minCost = Cost.get(z).getCounter();
                        minSide = Cost.get(z).getSide();
                        minColour = Cost.get(z).getColour();
                    }
                }
            }
        }
        this.heuristicCost += minCost;
    }

    private ArrayList<Cost> heuristic() {
        ArrayList<Cost> Cost = new ArrayList<>();

        for (int z = 0; z <= 5; z++) {
            // Colour counters
            int red_counter = 0;
            int white_counter = 0;
            int black_counter = 0;
            int yellow_counter = 0;
            int green_counter = 0;
            int orange_counter = 0;

            for (int i = 0; i <= cubeSize - 1; i++) {
                for (int j = 0; j <= cubeSize - 1; j++) {
                    if (block[z][i][j] == "R")
                        red_counter += 1;
                    else if (block[z][i][j] == "W")
                        white_counter += 1;
                    else if (block[z][i][j] == "B")
                        black_counter += 1;
                    else if (block[z][i][j] == "Y")
                        yellow_counter += 1;
                    else if (block[z][i][j] == "G")
                        green_counter += 1;
                    else if (block[z][i][j] == "O")
                        orange_counter += 1;
                    else {
                        System.out.println("There is something wrong...There are more colours than we need!");
                        break;
                    }
                }
            }

            Cost cost1 = new Cost(z, "R", cubeSize * cubeSize - red_counter);
            Cost cost2 = new Cost(z, "B", cubeSize * cubeSize - black_counter);
            Cost cost3 = new Cost(z, "W", cubeSize * cubeSize - white_counter);
            Cost cost4 = new Cost(z, "Y", cubeSize * cubeSize - yellow_counter);
            Cost cost5 = new Cost(z, "O", cubeSize * cubeSize - orange_counter);
            Cost cost6 = new Cost(z, "G", cubeSize * cubeSize - green_counter);
            Cost.add(cost1);
            Cost.add(cost2);
            Cost.add(cost3);
            Cost.add(cost4);
            Cost.add(cost5);
            Cost.add(cost6);
        }
        return Cost;
    }
}