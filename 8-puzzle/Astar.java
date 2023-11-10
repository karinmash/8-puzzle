import java.util.*;

public class Astar {
    private BoardNode initialNode;
    private Heuristics heuristic;
    private BoardNode lastNode;

    public Astar(BoardNode initialNode, int i) {
        this.initialNode = initialNode;
        heuristic = new Heuristics(i); // the correct heuristic function
    }


    public boolean aStarTreeSearch() {
        Frontier frontier = new Frontier(heuristic); // the open list
        frontier.add(initialNode);

        while (!frontier.isEmpty()) {
            BoardNode currentNode = frontier.pop();
            if (currentNode.isGoal()) { // check if we finished the search
                lastNode = currentNode;
                return true;
            }

            currentNode.createChildren();
            List<BoardNode> successors = currentNode.getChildren();
            for (BoardNode currentSuccessor : successors) {
                BoardNode currentSuccessorInFrontier;
                currentSuccessorInFrontier = frontier.find(currentSuccessor);
                if (currentSuccessorInFrontier == null ||
                        currentSuccessorInFrontier.getCost() > currentSuccessor.getCost()) {
                    // we add only if there isn't any better version already in the frontier
                    frontier.add(currentSuccessor);
                }
            }
        }

        System.out.println("We could not reach the goal state");
        return false;
    }

    public boolean aStarGraphSearch() {
        Frontier frontier = new Frontier(heuristic); // the open list
        frontier.add(initialNode);
        ArrayList<BoardNode> visited = new ArrayList<>(); // the close list

        while (!frontier.isEmpty()) {
            BoardNode currentNode = frontier.pop();
            if (currentNode.isGoal()) { // check if we finished the search
                lastNode = currentNode;
                return true;
            }

            currentNode.createChildren();
            List<BoardNode> successors = currentNode.getChildren();
            for (BoardNode currentSuccessor : successors) {
                BoardNode currentSuccessorInFrontier, currentSuccessorInVisited;
                if ((currentSuccessorInFrontier = frontier.find(currentSuccessor)) != null) {
                    if (currentSuccessorInFrontier.getCost() <= currentSuccessor.getCost())
                        continue; // currentSuccessor already in the frontier in a better path
                } else if (visited.contains(currentSuccessor)) {
                    currentSuccessorInVisited = visited.get(visited.indexOf(currentSuccessor)); // find in the closed list
                    if (currentSuccessorInVisited.getCost() <= currentSuccessor.getCost())
                        continue;
                    // the currentSuccessor is better than the older one
                    visited.remove(currentSuccessor);
                    frontier.add(currentSuccessor);
                } else {
                    frontier.add(currentSuccessor);
                }
            }

            visited.add(currentNode); // we finished checking all the current node's sons
        }

        System.out.println("We could not reach the goal state");
        return false;
    }

    public BoardNode getInitialNode() {
        return initialNode;
    }

    public BoardNode getLastNode() {
        return lastNode;
    }

}
