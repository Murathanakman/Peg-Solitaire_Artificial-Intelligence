import java.util.*;

public class IDS {

  private int depthLimit = 0;
  private long timeLimit;
  private long elapsedTime;
  private int memorySize;
  private ArrayList<Node> path = new ArrayList<Node>();
  private ArrayList<Node> frontier = new ArrayList<Node>();
  private ArrayList<Node> explored = new ArrayList<Node>(); // Expanded yap
  private ArrayList<Node> subOptimal = new ArrayList<Node>();

  public IDS(long timeLimit) {
    this.timeLimit = timeLimit;
  }

  long startTime = System.currentTimeMillis();
  public ArrayList<Node> Search(Node initNode) {
    boolean found = false;
    boolean subFound = false;
    while (!found && depthLimit <= 40) {
      elapsedTime = System.currentTimeMillis() - startTime;
      if (elapsedTime > timeLimit * 1000) {  //Time limit
        break;
      }
      frontier.clear(); // Clear the frontier arraylist.
      explored.clear(); // Clear the explored arraylist.
      memorySize = 1; // Memory size is 1 because of the initial node. reset memory size

      frontier.add(initNode);

      while (frontier.size() > 0 && !found) {
        Node current = frontier.get(frontier.size() - 1);
        explored.add(current);
        frontier.remove(frontier.size() - 1);
        current.ExpandNode();

        for (int i = current.children.size() - 1; i >= 0; i--) {
          if (!Contains(frontier, current.children.get(i)) && !Contains(explored, current.children.get(i))) {
            if (current.depth + 1 <= depthLimit) {
              frontier.add(current.children.get(i));
              memorySize++;
            }
          }
        }

        if (current.children.size() == 0 && current.GoalTest() == false) {
          subFound = true;
          subOptimal.add(current);
        }

        else if (current.children.size() > 0) {
          Node currentChild = current.children.get(current.children.size() - 1);
          if (currentChild.GoalTest()) {
            found = true;
            PathTrace(path, currentChild);
          }
        }
      }
      depthLimit++;
    }

    // If goal state is not found, find the best sub-optimal path
    if (found == false && subFound == true) {
      // Add a bestNode (single one) to the So array which includes keeping a single best node.
      PathTrace(path, FindBestSubOptimal(subOptimal));
      System.out.println("ii. Sub-optimum Solution Found with " + subOptimalPegNumber() + " remaining pegs");
      Collections.reverse(path);
      return path;
    } 
    else if (found == true){
      // Reverse the path
      Collections.reverse(path);
      return path;
    }
    else{
      return null;
    }
  }

  // Remove
  public static boolean Contains(ArrayList<Node> frontier, Node node) {
    for (int i = 0; i < frontier.size(); i++) {
      if (frontier.get(i).IsSamePuzzle(node.puzzle)) {
        return true;
      }

    }
    return false;
  }
  
  public int getDepthLimit() {
    return depthLimit;
  }

  public long getTimeLimit() {
    return timeLimit;
  }

  public long getElapsedTime() {
    return elapsedTime;
  }

  public int getMemorySize() {
    return memorySize;
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

  public ArrayList<Node> getSubOptimal() {
    return subOptimal;
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

}
