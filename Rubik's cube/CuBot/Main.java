import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        // Read the version of the cube
        int occasion;
        while (true) {
            System.out.println("Choose the version of the cube you want to create:");
            System.out.println("Press 0 to create a randomized cube.");
            System.out.println("Press 1 to test the program for 1 completed side.");
            System.out.println("Press 2 to test the program for 2 completed sides.");
            System.out.println("Press 3 to test the program for 3 completed sides.");
            System.out.println("Press 4 to test the program for 4 completed sides.");
            System.out.println("Press 5 to test the program for 5 completed sides.");
            System.out.println("Press 6 to test the program for all sides completed.");
            occasion = s.nextInt();

            if ((occasion < 0) || (occasion > 6)) {
                System.out.println("Wrong input!");
            } else {
                System.out.println('\n');
                break;
            }
        }

        int sides;
        // If user wants to create a randomized cube
        if (occasion == 0) {
            // Read the number of wanted sides (K)
            while (true) {
                System.out.println("How many sides do you want to be completed in the cube?");
                sides = s.nextInt();

                if ((sides < 0) || (sides > 6)) {
                    System.out.println("A cube has 6 sides! Choose between 0 and 6.");
                } else {
                    System.out.println('\n');
                    break;
                }
            }
        } else {
            sides = occasion;
        }

        s.close();

        // Create and print cube
        Cube cube = new Cube(sides, occasion);
        cube.print();

        // Create searcher
        Searcher searcher = new Searcher();

        // Run the searcher with A* algorithm on the cube
        cube = searcher.aStar(cube);

        // Print the solution
        cube.print();
    }
}
