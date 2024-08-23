
// Imports
import java.util.HashSet;
import java.util.PriorityQueue;

public class Searcher {
    // Initialize open and closed list
    private PriorityQueue<Cube> openList;
    private HashSet<Cube> closedList;

    // Create current node
    private Cube currentCube;

    // Constructor
    Searcher() {
        this.openList = new PriorityQueue<>();
        this.closedList = new HashSet<>();
        this.currentCube = null;
    }

    Cube aStar(Cube initialCube) {
        // Add initial node on the open list
        this.openList.add(initialCube);

        while (this.openList.size() > 0) {
            // Sort the open list based on the heuretistic and get the first node of the open list
            currentCube = this.openList.poll();

            // If it is the goal, print the moves and return it
            if (currentCube.isFinal()) {
                System.out.println("Moves made to solve: " + currentCube.getMoves());
                return currentCube;
            }

            // Else, add it on the closed list
            this.closedList.add(currentCube);

            // Generate its children
            for (Cube child : currentCube.getChildren()) {
                // If it is on the closed list, continue
                if (closedList.contains(child)) {
                    break;
                }

                // Else, if it is the goal, print the moves and return it
                if (child.isFinal()) {
                    System.out.println("Moves made to solve: " + child.getMoves());
                    return child;
                }

                // Else, add it on the open list
                openList.add(child);
            }

        }
        return null;
    }
}