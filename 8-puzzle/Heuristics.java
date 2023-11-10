
public class Heuristics {
    private int heuristic; // 0 or 1 or 2

    public final static int UCS = 0;
    public final static int COUNT_MISPLACED = 1;
    public final static int MANHATTAN = 2;

    //heuristic class that creates both heuristics
    public Heuristics(int heuristic) {
        this.heuristic = heuristic;
    }

    // choose between the heuristics
    public int heuristic(BoardNode node) {
        if (heuristic == 0)
            return 0; // for the case we want UCS
        if (heuristic == 1)
            return countMisplaced(node);
        else
            return manhattan(node);
    }

    // First Heuristic which tells us how many tiles are in an incorrect position
    private int countMisplaced(BoardNode node) {
        int[][] goalState = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
        int result = 0;
        int[][] state = node.getMatrix();
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state.length; j++) {
                if (goalState[i][j] != state[i][j]) {
                    result++;
                }
            }
        }
        return result;
    }

    private int manhattan(BoardNode node) {   //second heuristic which uses a goal state to help determined how far argument node tiles are from desired position
        int[][] goalState = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
        int result = 0;
        int[][] matrix = node.getMatrix();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int element = matrix[i][j];
                Cell loc1 = BoardNode.searchElement(matrix, element);
                Cell loc2 = BoardNode.searchElement(goalState, element);
                int rowDistance = Math.abs(loc1.row - loc2.row);
                int colDistance = Math.abs(loc1.col - loc2.col);

                result += rowDistance + colDistance;
            }
        }

        return result;
    }

}
