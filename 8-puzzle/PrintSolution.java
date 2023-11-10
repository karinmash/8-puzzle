import java.util.Arrays;
import java.util.Stack;

public class PrintSolution {
    // this class provides an object that is used to trace back the path from the goal
    // it then prints the path
    Stack<BoardNode> path;

    public PrintSolution(BoardNode initialNode, BoardNode goalNode) {  //the arguments are goalNode, info and intialNode so a path can be found.
        path = this.getPath(initialNode, goalNode);
    }


    private Stack<BoardNode> getPath(BoardNode initialNode, BoardNode goalNode) {  //given a goalNode and initialNode this method uses node's parents to trace it's way back up
        BoardNode tempNode = goalNode;
        Stack<BoardNode> path = new Stack<>();

        while (!(tempNode.equals(initialNode))) {
            path.push(tempNode);
            tempNode = tempNode.getParent();

        }
        path.add(initialNode);
        return path;  // a list of the path is returned in reverse order
    }


    public void printSolution() {   //this method enables us to print the path in correct order from start node to goal node with sufficient details.
        System.out.println("The Solution:");
        while (!path.isEmpty()) {
            BoardNode currentNodeInPath = path.pop();
            System.out.println();
            System.out.println("Moved from: " + currentNodeInPath.getDirection());
            System.out.println("Cost: " + currentNodeInPath.getCost());
            System.out.println();
            System.out.println("Current Node:");
            System.out.println(Arrays.deepToString(currentNodeInPath.getMatrix()).replace("], ",
                    "]\n").replace("[[", "[").replace("]]", "]"));
            System.out.println();
        }
    }
}
