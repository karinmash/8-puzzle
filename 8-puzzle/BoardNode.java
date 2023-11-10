
import java.util.ArrayList;
import java.util.List;

public class BoardNode {

    public enum DIRECTIONS {
        LEFT, RIGHT, UP, DOWN
    }

    private int[][] matrix = new int[3][3];
    private List<BoardNode> children;
    private BoardNode parent;
    private int depth;
    private int cost;
    private DIRECTIONS direction; // The direction we came from

    public DIRECTIONS getDirection() {
        return direction;
    }

    public BoardNode(int[][] matrix) {
        this.matrix = matrix; // the state
        this.depth = 1; // the depth
        this.children = new ArrayList<BoardNode>(); // the children of the node

        this.parent = null;
        this.cost = 0;
    }

    public void createChildren() {
        Cell blankCell = searchElement(matrix, 0);
        if (blankCell == null) {
            System.out.println("!!!");
        }

        Cell up = new Cell(blankCell.row - 1, blankCell.col);
        Cell down = new Cell(blankCell.row + 1, blankCell.col);
        Cell left = new Cell(blankCell.row, blankCell.col - 1);
        Cell right = new Cell(blankCell.row, blankCell.col + 1);

        // copy the board:
        int[][] upMatrix = duplicate(matrix);
        int[][] downMatrix = duplicate(matrix);
        int[][] leftMatrix = duplicate(matrix);
        int[][] rightMatrix = duplicate(matrix);

        BoardNode currentChild;
        // go all the possible steps (without going to where we started):
        if (replace(upMatrix, blankCell, up) && direction != DIRECTIONS.UP) {
            currentChild = new BoardNode(upMatrix);
            currentChild.direction = DIRECTIONS.DOWN;
            addChild(currentChild);
        }

        if (replace(downMatrix, blankCell, down) && direction != DIRECTIONS.DOWN) {
            currentChild = new BoardNode(downMatrix);
            currentChild.direction = DIRECTIONS.UP;
            addChild(currentChild);
        }

        if (replace(leftMatrix, blankCell, left) && direction != DIRECTIONS.LEFT) {
            currentChild = new BoardNode(leftMatrix);
            currentChild.direction = DIRECTIONS.RIGHT;
            addChild(currentChild);
        }

        if (replace(rightMatrix, blankCell, right) && direction != DIRECTIONS.RIGHT) {
            currentChild = new BoardNode(rightMatrix);
            currentChild.direction = DIRECTIONS.LEFT;
            addChild(currentChild);
        }
    }

    private static int[][] duplicate(int[][] matrix) {
        int[][] temp = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                temp[i][j] = matrix[i][j];
            }
        }
        return temp;
    }


    private static boolean replace(int[][] matrix, Cell cell1, Cell cell2) {
        if (cell1.row < 0 || cell1.row >= matrix.length || cell1.col < 0 || cell1.col >= matrix.length ||
                cell2.row < 0 || cell2.row >= matrix.length || cell2.col < 0 || cell2.col >= matrix.length)
            return false;
        if (cell1.equals(cell2))
            return false;
        // the cells are valid
        int temp = matrix[cell1.row][cell1.col];
        matrix[cell1.row][cell1.col] = matrix[cell2.row][cell2.col];
        matrix[cell2.row][cell2.col] = temp;

        return true;
    }

    public void addChild(BoardNode child) { //adding a Child to the node
        child.setParent(this);
        child.setDepth(this.getDepth() + 1);
        child.setCost(this.getCost() + 1);
        this.children.add(child);
    }

    public void setParent(BoardNode parent) { //setting the Parent of the node
        this.parent = parent;
    }

    public void setDepth(int depth) {  //setting the Depth of the node
        this.depth = depth;
    }

    public int getDepth() {  //getting the Depth of the node
        return depth;
    }

    public BoardNode getParent() {  //getting the Parent of the node
        return parent;
    }

    public int[][] getMatrix() { //getting the state in array form
        return matrix;
    }

    public int getCost() { //getting the cost of last move
        return this.cost;
    }

    public List<BoardNode> getChildren() { //getting the children
        return children;
    }

    public void setChildren(List<BoardNode> childrens) { //setting the children
        this.children = childrens;
    }


    public boolean isGoal() {                //checking if node is goal node
        boolean result = false;
        int[][] goal = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
        BoardNode goalNode = new BoardNode(goal);
        result = this.equals(goalNode);
        return result;
    }

    @Override
    // we consider two nodes as equals if they have the same matrix
    public boolean equals(Object object) {
        if (!(object instanceof BoardNode)) {
            return false;
        }
        BoardNode other = (BoardNode) object;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] != other.matrix[i][j])
                    return false;
            }
        }
        return true;
    }


    public void setCost(int i) {                    //setting cost
        this.cost = i;
    }

    // search x row and col in matrix
    public static Cell searchElement(int[][] matrix, int x) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == x) {
                    return new Cell(i, j);
                }
            }
        }
        return null; // x is not in matrix
    }

}
