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

    // Your program should input a search method and a time limit value,
    // Get array input from user
    Scanner input = new Scanner(System.in);
    System.out.print("Please enter the search method: ");
    String searchMethod = input.nextLine();
    //Accept all capital and small letters
    searchMethod = searchMethod.toLowerCase();

    // Get time limit value from user in minutes
    System.out.print("Please enter the time limit value (minute): ");
    long timeLimit = input.nextInt();
   
    // Create initial node
    Node initNode = new Node(puzzle);

    if(searchMethod.equals("bfs") || searchMethod.equals("breadth first search") || searchMethod.equals("breadth-first search")){
     try{
      System.out.println("i. Search method is breadth first search and the time limit is " + timeLimit + " minutes.");
      long startTime = System.nanoTime();
      BFS bfs = new BFS();
      ArrayList<Node> path = bfs.Search(initNode);
      long endTime = System.nanoTime();
      long timeSpent = endTime - startTime;
      timeSpent = timeSpent / 1000000;
      // Check if the elapsed time has exceeded the time limit

      //min to ns = multiply by 60000000000L
      if (timeSpent > timeLimit * 60000000000L) {
        System.out.println("ii. No solution found – Time Limit");
        return;
      }

      if (path.size() > 0) {
        System.out.println("iii. The board states from the initial state to the final state: ");
        for (int i = 0; i < path.size(); i++) {
          path.get(i).PrintPuzzle();
        }
      }
      if(!(timeSpent > timeLimit * 60000000000L)){
        //Nanoseconds to ms
        System.out.println("iv. Time spent on the dfs: " + timeSpent + " ms");
      }
     } catch (OutOfMemoryError e) {
        System.out.println("ii. No solution found – Out of Memory");
       }
    }

    else if(searchMethod.equals("dfs") || searchMethod.equals("depth first search") || searchMethod.equals("depth-first search")){
      try{
       System.out.println("i. Search method is depth first search and the time limit is " + timeLimit + " minutes.");
       long startTime = System.nanoTime();
       DFS dfs = new DFS();
       ArrayList<Node> path = dfs.Search(initNode);
       long endTime = System.nanoTime();
       long timeSpent = endTime - startTime;
       timeSpent = timeSpent / 1000000;

       // Check if the elapsed time has exceeded the time limit
       //min to ns = multiply by 60000000000L
       if (timeSpent > timeLimit * 60000000000L) {
         System.out.println("ii. No solution found – Time Limit");
         return;
       }
 
       if (path.size() > 0) {
         System.out.println("iii. The board states from the initial state to the final state: ");
         for (int i = 0; i < path.size(); i++) {
           path.get(i).PrintPuzzle();
         }
       }
       if(!(timeSpent > timeLimit * 60000000000L)){
         //Nanoseconds to ms
         System.out.println("iv. Time spent on the dfs: " + timeSpent + " ms");
       }

       System.out.println("v. The number of nodes expanded during the search: " + dfs.getExplored().size());
       System.out.println("vi. Max number of nodes stored in the memory during the search: " + dfs.getMemorySize());
      } catch (OutOfMemoryError e) {
         System.out.println("ii. No solution found – Out of Memory");
        }
    } 
    else{
      System.out.println("Please enter a valid search method.");
    }
  }
}
