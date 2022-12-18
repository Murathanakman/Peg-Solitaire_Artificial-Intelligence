import java.util.*;
public class Test {
    public static void main(String[] args){
        // Create Peg-Solitaire game board in 1D array
        int[] puzzle = {
            9, 9, 1, 1, 1, 9, 9,
            9, 9, 1, 1, 1, 9, 9,
            1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 0, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1,
            9, 9, 1, 1, 1, 9, 9,
            9, 9, 1, 1, 1, 9, 9
        };

        // Your program should input a search method and a time limit value,
        // Get array input from user
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the search method: ");
        String searchMethod = input.nextLine();


        // Get time limit value from user in minutes
        System.out.print("Please enter the time limit value (sec): ");
        long timeLimit = input.nextInt();
    
        // Create initial node
        Node initNode = new Node(puzzle, 0);

        if(searchMethod.equals("bfs") || searchMethod.equals("breadth first search") || searchMethod.equals("breadth-first search")){
            System.out.println("i. Search method is breadth first search and the time limit is " + timeLimit + " seconds.");
            BFS bfs = new BFS(timeLimit);
            try{
            ArrayList<Node> path = bfs.Search(initNode);  
            if (path.size() > 0) {
               System.out.println("iii. The board states from the initial state to the final state: ");
               for (int i = 0; i < path.size(); i++) {
                 path.get(i).PrintPuzzle();
               }
            }
            }
            catch(NullPointerException e){
                System.out.println("ii. No solution found Time Limit.");
            }
              
            catch(OutOfMemoryError e){
              System.out.println("ii. No solution found Out of Memory");
            }
            System.out.println("iv. Time spent on the bfs: " + bfs.getElapsedTime() + " ms");
            System.out.println("v. The number of nodes expanded during the search: " + bfs.getExplored().size());
            System.out.println("vi. Max number of nodes stored in the memory during the search: " + bfs.getMemorySize());
        }
        else if(searchMethod.equals("dfs") || searchMethod.equals("depth first search") || searchMethod.equals("depth-first search")){
            System.out.println("i. Search method is depth first search and the time limit is " + timeLimit + " seconds.");
            DFS dfs = new DFS(timeLimit);
            try{
            ArrayList<Node> path = dfs.Search(initNode);  
            if (path.size() > 0) {
               System.out.println("iii. The board states from the initial state to the final state: ");
               for (int i = 0; i < path.size(); i++) {
                 path.get(i).PrintPuzzle();
               }
            }
            }
            catch(NullPointerException e){
                System.out.println("ii. No solution found Time Limit.");
            }
              
            catch(OutOfMemoryError e){
              System.out.println("ii. No solution found Out of Memory");
            }
            System.out.println("iv. Time spent on the dfs: " + dfs.getElapsedTime() + " ms");
            System.out.println("v. The number of nodes expanded during the search: " + dfs.getExplored().size());
            System.out.println("vi. Max number of nodes stored in the memory during the search: " + dfs.getMemorySize());
        }
        else if(searchMethod.equals("ids") || searchMethod.equals("iterative deepening search")){
            System.out.println("i. Search method is iterative deepening search and the time limit is " + timeLimit + " seconds.");
            IDS ids = new IDS(timeLimit);
            try{
            ArrayList<Node> path = ids.Search(initNode);  
            if (path.size() > 0) {
               System.out.println("iii. The board states from the initial state to the final state: ");
               for (int i = 0; i < path.size(); i++) {
                 path.get(i).PrintPuzzle();
               }
            }
            }
            catch(NullPointerException e){
                System.out.println("ii. No solution found Time Limit.");
            }
              
            catch(OutOfMemoryError e){
              System.out.println("ii. No solution found Out of Memory");
            }
            System.out.println("iv. Time spent on the ids: " + ids.getElapsedTime() + " ms");
            System.out.println("v. The number of nodes expanded during the search: " + ids.getExplored().size());
            System.out.println("vi. Max number of nodes stored in the memory during the search: " + ids.getMemorySize());
        }
        else if(searchMethod.equals("dfsrs") || searchMethod.equals("depth first search with random selection") || searchMethod.equals("depth-first search with random selection")){
            System.out.println("i. Search method is depth first search with random selection and the time limit is " + timeLimit + " seconds.");
            DFSRS dfsrs = new DFSRS(timeLimit);
            try{
            ArrayList<Node> path = dfsrs.Search(initNode);  
            if (path.size() > 0) {
               System.out.println("iii. The board states from the initial state to the final state: ");
               for (int i = 0; i < path.size(); i++) {
                 path.get(i).PrintPuzzle();
               }
            }
            }
            catch(NullPointerException e){
                System.out.println("ii. No solution found Time Limit.");
            }
              
            catch(OutOfMemoryError e){
              System.out.println("ii. No solution found Out of Memory");
            }
            System.out.println("iv. Time spent on the dfsrs: " + dfsrs.getElapsedTime() + " ms");
            System.out.println("v. The number of nodes expanded during the search: " + dfsrs.getExplored().size());
            System.out.println("vi. Max number of nodes stored in the memory during the search: " + dfsrs.getMemorySize());
        }
        else if(searchMethod.equals("heuristic") || searchMethod.equals("depth-First search with a node selection heuristic") || searchMethod.equals("depth-first search with a node selection heuristic")){
            System.out.println("i. Search method is depth first search with a node selection heuristic and the time limit is " + timeLimit + " seconds.");
            Heuristic heuristic = new Heuristic(timeLimit);
            try{
            ArrayList<Node> path = heuristic.Search(initNode);  
            if (path.size() > 0) {
               System.out.println("iii. The board states from the initial state to the final state: ");
               for (int i = 0; i < path.size(); i++) {
                 path.get(i).PrintPuzzle();
               }
            }
            }
            catch(NullPointerException e){
                System.out.println("ii. No solution found Time Limit.");
            }
              
            catch(OutOfMemoryError e){
              System.out.println("ii. No solution found Out of Memory");
            }
            System.out.println("iv. Time spent on the dfs: " + heuristic.getElapsedTime() + " ms");
            System.out.println("v. The number of nodes expanded during the search: " + heuristic.getExplored().size());
            System.out.println("vi. Max number of nodes stored in the memory during the search: " + heuristic.getMemorySize());
        }
    }
}
