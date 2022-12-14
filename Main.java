import java.util.*;

// Create main class
public class Main {
  public static void main(String[] args) {

    // Create Peg-Solitaire game board in 1D array
    int[] puzzle = {
        9, 9, 1, 1, 1, 9, 9,
        9, 9, 1, 1, 1, 9, 9,
        1, 1, 1, 1, 1, 1, 1,
        1, 1, 1, 0, 1, 1, 1,
        1, 1, 1, 1, 1, 1, 1,
        9, 9, 1, 1, 1, 9, 9,
        9, 9, 1, 1, 1, 9, 9
    };

    /* 
    Node initNode = new Node(puzzle);
    BFS bfs = new BFS();

    ArrayList<Node> solution = bfs.Search(initNode);
    */

    Node initNode = new Node(puzzle);
    
     //Time spent on the dfs
    long dfsStartTime = System.nanoTime();
    DFS dfs = new DFS();
    ArrayList<Node> path = dfs.Search(initNode);  
    long dfsEndTime = System.nanoTime();
    long dfsTimeSpent = dfsEndTime - dfsStartTime;
    
    if (path.size() > 0) {
      System.out.println("iii. The board states from the initial state to the final state: ");
      for (int i = 0; i < path.size(); i++) {
        path.get(i).PrintPuzzle();
      }
    } else {
      //??? Bu kalkabilir
      System.out.println("No solution found!");
    }
    //Nanoseconds to seconds
    System.out.println("iv. Time spent on the dfs: " + dfsTimeSpent + " nanoseconds");
  }
}
