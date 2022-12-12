import java.util.ArrayList;

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

    
    Node initNode = new Node(puzzle);
    Bfs bfs = new Bfs();

    ArrayList<Node> solution = bfs.Search(initNode);

    if (solution.size() > 0) {
      for (int i = 0; i < solution.size(); i++) {
        solution.get(i).PrintPuzzle();
      }
    } else {
      System.out.println("No solution found!");
    }

  }
}
