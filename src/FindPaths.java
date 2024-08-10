import java.util.*;

public class FindPaths {
    private Graph graph;
    private String source;
    private String dest;
    private int cost;

    public FindPaths(Graph graph, String source, String dest){
        this.graph = graph;
        this.source = source;
        this.dest = dest;
    }


    public List<List<Edge>> findAllPaths() {
        List<List<Edge>> allPaths = new ArrayList<>(); // list to store all paths
        Stack<List<Edge>> stack = new Stack<>(); // create a stack to store the paths

        List<Edge> startPath = new ArrayList<>(); // create a list to store the first path
        startPath.add(new Edge(source, source, 0, 0)); // first path to begin with
        stack.push(startPath); // add this path to stack

        while (!stack.empty()) {
            List<Edge> currentPath = stack.pop(); // put our path from above here
            Edge currentEdge = currentPath.get(currentPath.size() - 1); // get the current edge from path
            String current = currentEdge.getDestination(); // get the destination city from this edge

            if (current.equals(dest)) { // if this city equals to our actual destination city
                allPaths.add(currentPath); // we add this path to all paths list
            } else {
                LinkedList<LinkedList<Edge>> edges = graph.getEdges(current); // get all edges from the current city
                for(LinkedList<Edge> edgeList : edges) { // for each list of edges
                    for (Edge edge : edgeList) { // for each edge
                        boolean inPath = false; // edge is not in the all paths list
                        for (Edge e : currentPath) { // for each edge in current path
                            if (e.getDestination().equals(edge.getDestination())) {// if edges are equal
                                inPath = true; // edge is already in all paths list
                                break;
                            }
                        }
                        if (!inPath) { // if the edge is not in all paths list
                            List<Edge> newPath = new ArrayList<>(currentPath); // create a new list to store new path
                            newPath.add(edge); // add the path to new list
                            stack.push(newPath); // push the path to stack
                        }
                    }
                }
            }
        }
        return allPaths; // return the all paths list containing all paths from source to destination
    }

    // findTotalCost & findTotalTime are the same as findAllPaths, with a few exceptions...
    public List<Integer> findTotalCost(){
        List<Integer> totalCost = new ArrayList<>(); // create list hold the valid path's costs
        Stack<List<Edge>> stack = new Stack<>();
        List<Edge> startPath = new ArrayList<>();
        startPath.add(new Edge(source, source, 0, 0));
        stack.push(startPath);

        while (!stack.empty()) {
            List<Edge> currentPath = stack.pop();
            Edge currentEdge = currentPath.get(currentPath.size() - 1);
            String current = currentEdge.getDestination();

            if (current.equals(dest)) { // if path is found
                int cost = 0;
                for (Edge edge : currentPath) {
                    cost += edge.getCost(); // we calculate the total cost of the path
                }
                totalCost.add(cost); // add that cost to the list
            } else {
                LinkedList<LinkedList<Edge>> edges = graph.getEdges(current); // get all edges from the current city
                for(LinkedList<Edge> edgeList : edges) { // for each list of edges
                    for (Edge edge : edgeList) {
                        boolean inPath = false;
                        for (Edge e : currentPath) {
                            if (e.getDestination().equals(edge.getDestination())) {
                                inPath = true;
                                break;
                            }
                        }
                        if (!inPath) {
                            List<Edge> newPath = new ArrayList<>(currentPath);
                            newPath.add(edge);
                            stack.push(newPath);
                        }
                    }
                }
            }
        }
        return totalCost; // return the list containing the total costs of each path
    }


    public List<Integer> findTotalTime(){
        List<Integer> totalTime = new ArrayList<>(); // create list to store the total time of each valid path
        Stack<List<Edge>> stack = new Stack<>();
        List<Edge> startPath = new ArrayList<>();
        startPath.add(new Edge(source, source, 0, 0));
        stack.push(startPath);

        while (!stack.empty()) {
            List<Edge> currentPath = stack.pop();
            Edge currentEdge = currentPath.get(currentPath.size() - 1);
            String current = currentEdge.getDestination();

            if (current.equals(dest)) {
                int time = 0;
                for (Edge edge : currentPath) {
                    time += edge.getTime(); // calculate the total time of each valid path
                }
                totalTime.add(time); // put that total time into the list
            } else {
                //List<Edge> edges = graph.getEdges(current);
                LinkedList<LinkedList<Edge>> edges = graph.getEdges(current); // get all edges from the current city
                for(LinkedList<Edge> edgeList : edges) { // for each list of edges
                    for (Edge edge : edgeList) {
                        boolean inPath = false;
                        for (Edge e : currentPath) {
                            if (e.getDestination().equals(edge.getDestination())) {
                                inPath = true;
                                break;
                            }
                        }
                        if (!inPath) {
                            List<Edge> newPath = new ArrayList<>(currentPath);
                            newPath.add(edge);
                            stack.push(newPath);
                        }
                    }
                }
            }
        }
        return totalTime; // return the list of total times for each valid path
    }
}
