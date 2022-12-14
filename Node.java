import java.util.*;

public class Node {

  public ArrayList<Node> children = new ArrayList<Node>();
  public Node parent;
  public int[] puzzle = new int[49];
  public int heuristic_counter;
  public int depth;

  public Node(int[] puzzle, int depth) {
    SetPuzzle(puzzle);
    this.depth = depth;
  }

  //get Puzzle
  public int[] GetPuzzle() {
    return puzzle;
  }

  public void SetPuzzle(int[] puzzle) {
    for (int i = 0; i < puzzle.length; i++) {
      this.puzzle[i] = puzzle[i];
    }
  }

  // düzelt
  public void ExpandNode() {
    for (int i = 0; i < puzzle.length; i++) {
      MoveDown(puzzle, i);
      MoveRight(puzzle, i);
      MoveLeft(puzzle, i);
      MoveUp(puzzle, i);
      }
  }

  // Heuristic funtion
  // Node is jumpable or not
  public int JumpCounter(int[] puzzle) {
    int heuristic_counter = 0;
    try {
      for(int index = 0; index < puzzle.length; index++) {
        // Right
        if (puzzle[index] == 1 && puzzle[index + 1] == 1 && puzzle[index + 2] == 0 && index != 19 && index != 20 && index != 26 && index != 27) {
          heuristic_counter += 1;
        }
        // Left
        else if (puzzle[index] == 1 && puzzle[index - 1] == 1 && puzzle[index - 2] == 0 && index != 14 && index != 15 && index != 21 && index != 22) {
          heuristic_counter += 1;
        }
        // Up
        else if (puzzle[index] == 1 && puzzle[index + 7] == 1 && puzzle[index + 14] == 0 && index != 2 && index != 3 && index != 4 && index != 9 && index != 10 && index != 11) {
          heuristic_counter += 1;
        }
        //Down
        else if (puzzle[index] == 1 && puzzle[index - 7] == 1 && puzzle[index - 14] == 0 && index != 37 && index != 38 && index != 39 && index != 44 && index != 45 && index != 46) {
          heuristic_counter += 1;
        }
      }
    return heuristic_counter;
    } catch(ArrayIndexOutOfBoundsException e) {
      return -1;
    }
  }

  // Sort children list using by
  public void SortChildren() {
    for (int i = 0; i < children.size(); i++) {
      for (int j = 0; j < children.size(); j++) {
        if (children.get(i).JumpCounter(children.get(i).puzzle) < children.get(j).JumpCounter(children.get(j).puzzle)) {
          Node temp = children.get(i);
          children.get(i).puzzle = children.get(j).puzzle;
          children.get(j).puzzle = temp.puzzle;
        }
      }
    }
  }

  // Goal test function PEG-SOLITAIRE
  public boolean GoalTest() {
    int count = 0;
    for (int i = 0; i < puzzle.length; i++) {
      if (puzzle[i] == 1) {
        count++;
      }
    }
    //Optimum solution
    if (count == 1 && puzzle[24] == 1) {
      return true;
    }
    else {
      return false;
    }
  }

  // Move to the Right function PEG-SOLITAIRE
  public void MoveRight(int[] puzzle, int index) {
    try {
      if (puzzle[index] == 1 && puzzle[index + 1] == 1 && puzzle[index + 2] == 0) {
        // Control some indexes to prevent error caused by one dimensional array
        if(index != 19 && index != 20 && index != 26 && index != 27){
          int[] newPuzzle = new int[49];
          copyPuzzle(puzzle, newPuzzle);
    
          newPuzzle[index] = 0;
          newPuzzle[index + 1] = 0;
          newPuzzle[index + 2] = 1;
    
          // Create a new node
          Node child = new Node(newPuzzle, this.depth + 1);
          children.add(child);
    
          // Set the parent of the new node
          child.parent = this;
        }
      }
   } catch(ArrayIndexOutOfBoundsException e) {
      return;
   }
  }

  // Move to the Left function PEG-SOLITAIRE
  public void MoveLeft(int[] puzzle, int index) {
  try{
    if (puzzle[index] == 1 && puzzle[index - 1] == 1 && puzzle[index - 2] == 0) {
      // Control some indexes to prevent error caused by one dimensional array
      if(index != 14 && index != 15 && index != 21 && index != 22){
        int[] newPuzzle = new int[49];
        copyPuzzle(puzzle, newPuzzle);

        newPuzzle[index] = 0;
        newPuzzle[index - 1] = 0;
        newPuzzle[index - 2] = 1;

        // Create a new node
        Node child = new Node(newPuzzle, this.depth + 1);
        children.add(child);

        // Set the parent of the new node
        child.parent = this;
      }
    }
  } catch(ArrayIndexOutOfBoundsException e) {
    return;
  }
  }

  // Move Up function
  public void MoveUp(int[] puzzle, int index) {
  try{
    if (puzzle[index] == 1  && puzzle[index - 7] == 1 && puzzle[index - 14] == 0) {
      // Control some indexes to prevent error caused by one dimensional array
      if(index != 2 && index != 3 && index != 4 && index != 9 && index != 10 && index != 11){
        int[] newPuzzle = new int[49];
        copyPuzzle(puzzle, newPuzzle);

        newPuzzle[index] = 0;
        newPuzzle[index - 7] = 0;
        newPuzzle[index - 14] = 1;

        // Create a new node
        Node child = new Node(newPuzzle, this.depth + 1);
        children.add(child);

        // Set the parent of the new node
        child.parent = this;
      }
    }
  } catch(ArrayIndexOutOfBoundsException e) {
    return;
  }
  }

  // Move Down function PEG-SOLITAIRE
  public void MoveDown(int[] puzzle, int index) {
  try{
    if (puzzle[index] == 1 && puzzle[index + 7] == 1 && puzzle[index + 14] == 0) {
      if(index != 37 && index != 38 && index != 39 && index != 44 && index != 45 && index != 46){
        int[] newPuzzle = new int[49];
        copyPuzzle(puzzle, newPuzzle);

        newPuzzle[index] = 0;
        newPuzzle[index + 7] = 0;
        newPuzzle[index + 14] = 1;

        // Create a new node
        Node child = new Node(newPuzzle, this.depth + 1);
        children.add(child);

        // Set the parent of the new node
        child.parent = this;
      }
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
