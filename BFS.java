import java.util.*;

public class BFS {

  private long timeLimit;
  private long elapsedTime;
  private int memorySize = 1;
  private ArrayList<Node> path = new ArrayList<Node>();
  private ArrayList<Node> frontier = new ArrayList<Node>();
  private ArrayList<Node> explored = new ArrayList<Node>();
  private ArrayList<Node> subOptimal = new ArrayList<Node>();

  public BFS(long timeLimit) {
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
      Node currentNode = frontier.get(0);
      explored.add(currentNode);
      frontier.remove(0);

      currentNode.ExpandNode();
      //currentNode.PrintPuzzle();

      if (currentNode.children.size() == 0 && currentNode.GoalTest() == false) {
        subFound = true;
        subOptimal.add(currentNode);
      }

      for (int i = 0; i < currentNode.children.size(); i++) {
        Node currentChild = currentNode.children.get(i);
        if (currentChild.GoalTest()) {
          found = true;
          // Backtrack to find the path
          PathTrace(path, currentChild);

        }

        if (!Contains(frontier, currentChild) && !Contains(explored, currentChild)) {
          frontier.add(currentChild);
          memorySize++;
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
      System.out.println(found);
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

  //Remove
  public static boolean Contains(ArrayList<Node> frontier, Node node) {
    for (int i = 0; i < frontier.size(); i++) {
      if (frontier.get(i).IsSamePuzzle(node.puzzle)) {
        return true;
      }
    }
    return false;

  }

  public void PathTrace(ArrayList<Node> path, Node node) {
    System.out.println("Path trace:");
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

  public int getMemorySize() {
    return memorySize;
  }

  public ArrayList<Node> getPath(){
    return path;
  }

  public ArrayList<Node> getFrontier(){
    return frontier;
  }
  
  public ArrayList<Node> getExplored(){
    return explored;
  }

  public ArrayList<Node> getSubOptimal(){
    return subOptimal;
  }
  
  public long getTimeLimit(){
    return timeLimit;
  }

}
