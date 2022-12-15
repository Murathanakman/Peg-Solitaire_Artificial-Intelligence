import java.util.*;
public class DFS {
  private ArrayList<Node> path = new ArrayList<Node>();
  private ArrayList<Node> frontier = new ArrayList<Node>();
  private ArrayList<Node> explored = new ArrayList<Node>(); // Expanded yap
  private ArrayList<Node> subOptimal = new ArrayList<Node>();
  // Inıtıal node added to frontier
  private int memorySize = 1;
  public DFS() {

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

  public ArrayList<Node> FindBestSubOptimal(ArrayList<Node> path){
    int count = 0;
    int best = 0;
    for (int i = 0; i < path.size(); i++) {
      Node current = subOptimal.get(i);
      for(int j = 0; j < current.puzzle.length; j++){
        if (current.puzzle[j] == 1){
          count++;
        }
      }
      if(count > best){
        best = count;
      }
    }
    return path;
  }

  public ArrayList<Node> Search(Node initNode) {
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
          if(currentNode.children.size() == 0){
              subOptimal.add(currentNode);
          }
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
