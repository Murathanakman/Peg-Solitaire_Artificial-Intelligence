import java.util.ArrayList;
import java.util.List;

public class Bfs {

  public Bfs() {

  }

  public ArrayList<Node> Search(Node initNode) {
    List<Node> path = new ArrayList<Node>();
    List<Node> frontier = new ArrayList<Node>();
    List<Node> explored = new ArrayList<Node>();

    frontier.add(initNode);
    boolean found = false;

    while (frontier.size() > 0 && !found) {
      Node currentNode = frontier.get(0);
      explored.add(currentNode);
      frontier.remove(0);

      currentNode.ExpandNode();
      currentNode.PrintPuzzle();

      for (int i = 0; i < currentNode.children.size(); i++) {
        Node currentChild = currentNode.children.get(i);
        if (currentChild.GoalTest()) {
          System.out.println("Found solution!");
          found = true;
          // Backtrack to find the path
          PathTrace(path, currentChild);

        }

        if (!Contains(frontier, currentChild) && !Contains(explored, currentChild)) {
          frontier.add(currentChild);
        }
      }
    }
	return null;

  }

  public static boolean Contains(List<Node> frontier, Node node) {
    for (int i = 0; i < frontier.size(); i++) {
      if (frontier.get(i).IsSamePuzzle(node.puzzle)) {
        return true;
      }
      
    }
    return false;

  }

  public void PathTrace(List<Node> path, Node node) {
    System.out.println("Path trace:");
    Node current = node;
    path.add(current);
    while (current.parent != null) {
      current = current.parent;
      path.add(current);
    }
  }
}
