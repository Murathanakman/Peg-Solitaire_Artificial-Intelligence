import java.util.*;
public class DFSRS {
  private ArrayList<Node> path = new ArrayList<Node>();
  private ArrayList<Node> frontier = new ArrayList<Node>();
  private ArrayList<Node> explored = new ArrayList<Node>(); // Expanded yap
  // Inıtıal node added to frontier
  private int memorySize = 1;
  // Create randomized dfs
    public DFSRS() {
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
    public void PathTrace(ArrayList<Node> path, Node node) {
      Node current = node;
      path.add(current);
      while (current.parent != null) {
        current = current.parent;
        path.add(current);
      }
    }
    public ArrayList<Node> Search(Node initNode) {
        frontier.add(initNode);
        boolean found = false;
        Node currentNode = initNode;
        while(frontier.size() > 0 && !found) {
            // Remove the selected node from the frontier
            // düzelt
            explored.add(currentNode);
            currentNode.ExpandNode();

            // Generate a random index
            if(currentNode.children.size() > 0){
              int randomIndex = new Random().nextInt(currentNode.children.size() - 1);
              currentNode = currentNode.children.get(randomIndex);
            }
            currentNode.PrintPuzzle();
            
            for (int i = 0; i < currentNode.children.size(); i++) {
                Node currentChild = currentNode.children.get(i);
                if (currentChild.GoalTest()) {
                /* Print goal state if exists (Sonradan silinecek)
                currentChild.PrintPuzzle();
                */
                found = true;
                // Backtrack to find the path
    
                PathTrace(path, currentChild);
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

