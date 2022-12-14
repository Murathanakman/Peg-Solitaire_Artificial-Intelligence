import java.util.*;
public class DFS {
  public DFS() {

  }

  public void PathTrace(ArrayList<Node> path, Node node) {
    Node current = node;
    path.add(current);
    while (current.parent != null) {
      current = current.parent;
      path.add(current);
    }
  }

  public ArrayList<Node> Search(Node initNode) {
    ArrayList<Node> path = new ArrayList<Node>();
    ArrayList<Node> frontier = new ArrayList<Node>();
    ArrayList<Node> explored = new ArrayList<Node>(); // Expanded yap
    // Inıtıal node added to frontier
    int memorySize = 1;

    frontier.add(initNode);
    boolean found = false;
   
    while(frontier.size() > 0 && !found) {
       
        Node currentNode = frontier.get(frontier.size() - 1);
        explored.add(currentNode);
        frontier.remove(frontier.size() - 1);

        currentNode.ExpandNode();
      
        /*Print frontier queue (Sonradan silinecek)
        currentNode.PrintPuzzle();
        */
        for (int i = 0; i < currentNode.children.size(); i++) {
            Node currentChild = currentNode.children.get(i);
            if (currentChild.GoalTest()) {
            /* Print goal state if exists (Sonradan silinecek)
            currentChild.PrintPuzzle();
             */
            found = true;
            // Backtrack to find the path
            System.out.println("v. The number of nodes expanded during the search: " + explored.size());
            PathTrace(path, currentChild);
            System.out.println("vi. Max number of nodes stored in the memory during the search: " + memorySize);
            }

            //Contains programın duplicate data yapmasının önüne geçmek için var
            if (!Contains(frontier, currentChild) && !Contains(explored, currentChild)) {
            frontier.add(currentChild);
            memorySize++;
            }
        }
       
    }
    // Reverse the path
    Collections.reverse(path);
    
  return path;
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

}
