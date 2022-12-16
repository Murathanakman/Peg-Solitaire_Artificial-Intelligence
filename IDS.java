import java.util.*;

public class IDS {
  private ArrayList<Node> path = new ArrayList<Node>();
  private ArrayList<Node> frontier = new ArrayList<Node>();
  private ArrayList<Node> explored = new ArrayList<Node>(); // Expanded yap
  private ArrayList<Node> subOptimal = new ArrayList<Node>();
  public ArrayList<Node> So = new ArrayList<>();
  // Inıtıal node added to frontier
  private int memorySize = 1;
  private int sum = 0;

  public IDS() {

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

  public ArrayList<Node> Search(Node initNode) {
    int depth = 0;
    boolean found = false;

    while (!found) {
      ArrayList<Node> result = DepthLimitedSearch(initNode, depth);

      if (result != null) {
        return result;
      }
      depth++;
    }
    return null;
  }

  private ArrayList<Node> DepthLimitedSearch(Node node, int depthLimit) {
    boolean found = false;
    frontier.add(node);
    while (frontier.size() > 0) {
      Node currNode = frontier.remove(frontier.size() - 1);
      explored.add(currNode);

      if (currNode.GoalTest()) {
        PathTrace(path, currNode);
        found = true;
        return path;
      }

      if (depthLimit > 0) {
        currNode.ExpandNode();
        sum++;
        System.out.println(sum);
        currNode.PrintPuzzle();

        for (Node child : currNode.children) {

          if (!Contains(frontier, child) && !Contains(explored, child)) {
            frontier.add(child);
            memorySize++;
          }
        }
        if (currNode.children.size() == 0) {
          subOptimal.add(currNode);
        }

      }

    }

    if (found == false) {
      PathTrace(path, FindBestSubOptimal(subOptimal));
      Collections.reverse(path);
      return path;
    }
    return null;
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

}
