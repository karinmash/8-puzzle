import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Frontier {
    private PriorityQueue<BoardNode> frontier;
    private Heuristics heuristic;

    // f(n) = g(n) +  h(n)
    private int f(BoardNode node) {
        return node.getCost() + heuristic.heuristic(node); // f(n) = g(n) + h(n)
    }

    private class fComparator implements Comparator<BoardNode> {  //comparator for tiles misplaced heuristic that will be used in Priority Queue
        public int compare(BoardNode a, BoardNode b) {
            return f(a) - f(b); // negative if f(a) < f(b) - then a is better
        }
    }

    public Frontier(Heuristics heuristic) {
        // PriorityQueue by the function f(n) = g(n) + h(n)
        this.heuristic = heuristic;
        frontier = new PriorityQueue<BoardNode>(new fComparator());
    }

    public boolean isEmpty() {
        return frontier.isEmpty();
    }

    public BoardNode pop() {
        return frontier.poll();
    }

    public BoardNode top() {
        return frontier.peek();
    }

    public void add(BoardNode node) {
        frontier.add(node);
    }

    // finds a specific element in the frontier
    public BoardNode find(BoardNode node) {
        for (BoardNode current : frontier) {
            if (current.equals(node)) {
                return current;
            }
        }
        return null;
    }

}
