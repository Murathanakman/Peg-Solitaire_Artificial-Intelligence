import java.util.*;

public class DFS {
  private long timeLimit;
  private long elapsedTime;
  private int memorySize = 1;
  private ArrayList<Node> path = new ArrayList<Node>();
  private ArrayList<Node> frontier = new ArrayList<Node>();
  private ArrayList<Node> explored = new ArrayList<Node>(); // Expanded yap
  private ArrayList<Node> subOptimal = new ArrayList<Node>();

  public DFS(long timeLimit) {
    this.timeLimit = timeLimit;
  }
  long startTime = System.currentTimeMillis();
  public ArrayList<Node> Search(Node initNode) {
    frontier.add(initNode);
    boolean found = false;
    boolean subFound = false;

    while (frontier.size() > 0 && !found) {
        elapsedTime = System.currentTimeMillis() - startTime;
        if (elapsedTime > timeLimit * 1000) {  //Time limit
          break;
        }
        Node currentNode = frontier.get(frontier.size() - 1);
        explored.add(currentNode);
        frontier.remove(frontier.size() - 1);

        currentNode.ExpandNode();

        for (int i = currentNode.children.size()-1; i >= 0; i--) {
          if (!Contains(frontier, currentNode.children.get(i)) && !Contains(explored, currentNode.children.get(i))) {
            frontier.add(currentNode.children.get(i));
            memorySize++;
          }
        }
        //currentNode.PrintPuzzle();
        
        if (currentNode.children.size() == 0 && currentNode.GoalTest() == false) {
          subFound = true;
          subOptimal.add(currentNode);
        }

        else if(currentNode.children.size() > 0){
          Node currentChild = currentNode.children.get(currentNode.children.size() - 1);
          if (currentChild.GoalTest()) {
            /*
            Print goal state if exists (Sonradan silinecek)
            */
            found = true;
            // Backtrack to find the path

            PathTrace(path, currentChild);
          }
        }
    }
    // If goal state is not found, find the best sub-optimal path
    if (found == false && subFound == true) {
      // Add a bestNode (single one) to the So array which includes keeping a single best node.
      PathTrace(path, FindBestSubOptimal(subOptimal));
      System.out.println("ii. Sub-optimum Solution Found with " + subOptimalPegNumber() + " remaining pegs");
      Collections.reverse(path);
      elapsedTime = System.currentTimeMillis() - startTime;
      return path;
    } 
    else if (found == true){
      System.out.println("ii. Optimum solution found");
      // Reverse the path
      Collections.reverse(path);
      elapsedTime = System.currentTimeMillis() - startTime;
      return path;
    }
    else{
      elapsedTime = System.currentTimeMillis() - startTime;
      return null;
    }
  }

  public static boolean Contains(ArrayList<Node> frontier, Node node) {
    for (int i = 0; i < frontier.size(); i++) {
      if (frontier.get(i).IsSamePuzzle(node.puzzle)) {
        return true;
      }

    }
    return false;

  }

  public void PathTrace(ArrayList<Node> path, Node node) {
    Node current = node;
    path.add(current);
    while (current.parent != null) {
      current = current.parent;
      path.add(current);
    }
  }

  public Node FindBestSubOptimal(ArrayList<Node> path) {
    int count = 0;
    int worst = 100; // Assume (does not imporant)
    Node bestNode = null;
    for (int i = 0; i < path.size(); i++) { // Traveling the path arraylist that given as parameter.
      Node current = path.get(i); // Get the current node from the path arraylist -- current node will change
                                  // respectively to the loop.
      for (int j = 0; j < current.puzzle.length; j++) { // Traveling the puzzle array of the current node.
        if (current.puzzle[j] == 1) { // If the current element of the puzzle array is 1, then increase the count.
          count++;
        }
      }
      if (count < worst) {
        worst = count; // If number of 1 of current node less than worst, assign new worst current
                       // node's 1.
        bestNode = current; // Then assign bestNode is current node.
      }
    }
    return bestNode; // Return the bestNode.
  }

  public int subOptimalPegNumber(){
    int count = 0;
    for(int i = 0; i < FindBestSubOptimal(path).puzzle.length; i++){
      if(FindBestSubOptimal(path).puzzle[i] == 1){
        count ++;
      }
    }
    return count;
  }

  public long getElapsedTime(){
    return elapsedTime;
  }

  public ArrayList<Node> getPath() {
    return path;
  }

  public ArrayList<Node> getFrontier() {
    return frontier;
  }

  public ArrayList<Node> getExplored() {
    return explored;
  }

  public int getMemorySize() {
    return memorySize;
  }

  public ArrayList<Node> getSubOptimal() {
    return subOptimal;
  }

  public long getTimeLimit(){
    return timeLimit;
  }

}
