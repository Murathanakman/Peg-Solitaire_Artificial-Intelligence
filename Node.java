import java.util.ArrayList;

public class Node {

  public ArrayList<Node> children = new ArrayList<Node>();
  public Node parent;
  public int[] puzzle = new int[49];

  public Node(int[] puzzle) {
    SetPuzzle(puzzle);
  }

  public void SetPuzzle(int[] puzzle) {
    for (int i = 0; i < puzzle.length; i++) {
      this.puzzle[i] = puzzle[i];
    }
  }

  public void BFS_ExpandNode() {
    for (int i = 0; i < puzzle.length; i++) {
      if (puzzle[i] == 1) {
        MoveDown(puzzle, i);
        MoveRight(puzzle, i);
        MoveLeft(puzzle, i);
        MoveUp(puzzle, i);
      }
    }
  }

  public void DFS_ExpandNode() {
    for (int i = 0; i < puzzle.length; i++) {
      if (puzzle[i] == 1) {
        MoveUp(puzzle, i);
        MoveLeft(puzzle, i);
        MoveRight(puzzle, i);
        MoveDown(puzzle, i);
      }
    }
  }

  // Goal test function PEG-SOLITAIRE //DÜZELT
  public boolean GoalTest() {
    int count = 0;
    for (int i = 0; i < puzzle.length; i++) {
      if (puzzle[i] == 1) {
        count++;
      }
    }
    if (count == 1) {
      return true;
    } else {
      return false;
    }
  }

  // Move to the Right function PEG-SOLITAIRE
  public void MoveRight(int[] puzzle, int index) {
    try {
      if (puzzle[index + 1] == 1 && puzzle[index + 2] == 0) {

        int[] newPuzzle = new int[49];
        copyPuzzle(puzzle, newPuzzle);
  
        newPuzzle[index] = 0;
        newPuzzle[index + 1] = 0;
        newPuzzle[index + 2] = 1;
  
        // Create a new node
        Node child = new Node(newPuzzle);
        children.add(child);
  
        // Set the parent of the new node
        child.parent = this;
  
      }
   } catch(ArrayIndexOutOfBoundsException e) {
      return;
   }
  }

  // Move to the Left function PEG-SOLITAIRE
  public void MoveLeft(int[] puzzle, int index) {
  try{
    if (puzzle[index - 1] == 1 && puzzle[index - 2] == 0) {

      int[] newPuzzle = new int[49];
      copyPuzzle(puzzle, newPuzzle);

      newPuzzle[index] = 0;
      newPuzzle[index - 1] = 0;
      newPuzzle[index - 2] = 1;

      // Create a new node
      Node child = new Node(newPuzzle);
      children.add(child);

      // Set the parent of the new node
      child.parent = this;

    }
  } catch(ArrayIndexOutOfBoundsException e) {
    return;
  }
  }

  // Move Up function
  public void MoveUp(int[] puzzle, int index) {
  try{
    if (puzzle[index - 7] == 1 && puzzle[index - 14] == 0) {

      int[] newPuzzle = new int[49];
      copyPuzzle(puzzle, newPuzzle);

      newPuzzle[index] = 0;
      newPuzzle[index - 7] = 0;
      newPuzzle[index - 14] = 1;

      // Create a new node
      Node child = new Node(newPuzzle);
      children.add(child);

      // Set the parent of the new node
      child.parent = this;
    }
  } catch(ArrayIndexOutOfBoundsException e) {
    return;
  }
  }

  // Move Down function PEG-SOLITAIRE
  public void MoveDown(int[] puzzle, int index) {
  try{
    if (puzzle[index + 7] == 1 && puzzle[index + 14] == 0) {

      int[] newPuzzle = new int[49];
      copyPuzzle(puzzle, newPuzzle);

      newPuzzle[index] = 0;
      newPuzzle[index + 7] = 0;
      newPuzzle[index + 14] = 1;

      // Create a new node
      Node child = new Node(newPuzzle);
      children.add(child);

      // Set the parent of the new node
      child.parent = this;
    }
  } catch(ArrayIndexOutOfBoundsException e) {
    return;
  }
  }

  public void PrintPuzzle() {
    for (int i = 0; i < puzzle.length; i++) {
      System.out.print(puzzle[i] + " ");
      if ((i + 1) % 7 == 0) {
        System.out.println();
      }
    }
    System.out.println();
  }

  public boolean IsSamePuzzle(int[] puzzle) {
    for (int i = 0; i < puzzle.length; i++) {
      if (this.puzzle[i] != puzzle[i]) {
        return false;
      }
    }
    return true;
  }

  // Copy puzzle function
  public void copyPuzzle(int[] puzzle, int[] newPuzzle) {
    for (int i = 0; i < puzzle.length; i++) {
      newPuzzle[i] = puzzle[i];
    }
  }

}
