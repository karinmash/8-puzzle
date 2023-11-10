import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Program {
    final static Scanner scanner = new Scanner(System.in);
    final static int TREE = 0;
    final static int GRAPH = 1;

    public static void main(String[] args) {
        BoardNode initialNode = createInitialNodeFromUser();
        Astar astar = new Astar(initialNode, chooseHeuristic());
        int treeOrGraph = treeOrGraph();
        if (treeOrGraph == TREE) {
            astar.aStarTreeSearch();
        } else if (treeOrGraph == GRAPH) {
            astar.aStarGraphSearch();
        } else {
            System.out.println("Invalid input!");
            System.exit(0);
        }

        PrintSolution printSolution = new PrintSolution(astar.getInitialNode(), astar.getLastNode());
        printSolution.printSolution();
    }

    private static int chooseHeuristic() {
        System.out.println("Please choose the heuristic you want to use.");
        System.out.println("Enter 0 for UCS");
        System.out.println("Enter 1 for COUNT_MISPLACED");
        System.out.println("Enter 2 for MANHATTAN");

        return scanner.nextInt();
    }

    private static int treeOrGraph() {
        System.out.println("Please choose the type of search you want to use.");
        System.out.println("Please note that UCS search only includes TREE search");
        System.out.println("Enter 0 for TREE.");
        System.out.println("Enter 1 for GRAPH.");
        return scanner.nextInt();
    }

    private static BoardNode createInitialNodeFromUser() {
        System.out.println("If you want a random initial node please enter the number 1, and otherwise enter 0:");
        if(scanner.nextInt() == 1)
            return createRandomInitialNode();

        int[][] matrix = new int[3][3];
        System.out.println("Please enter the numbers 0,1,2,3,4,5,6,7,8 in the order you want:");
        System.out.println("for example:\n1 4 2\n3 5 8\n0 6 7");
        for (int i = 0; i < 9; i++) {
            matrix[i / 3][i % 3] = scanner.nextInt();
        }
        return new BoardNode(matrix);
    }

    private static BoardNode createRandomInitialNode() {
        ArrayList<Integer> numbers = new ArrayList<>(9);
        numbers.add(0);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        numbers.add(6);
        numbers.add(7);
        numbers.add(8);
        Collections.shuffle(numbers);

        int[][] matrix = new int[3][3];
        for (int i = 0; i < 9; i++) {
            matrix[i / 3][i % 3] = numbers.get(i);
        }

        return new BoardNode(matrix);
    }


}
