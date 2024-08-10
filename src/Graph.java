import java.util.*;
public class Graph {
    private Map<String, LinkedList<LinkedList<Edge>>> adjacencyList; // create adjacent list data structure
    // as a linked list of linked lists

    public Graph() {
        adjacencyList = new HashMap<>(); // initializes the list
    }

    public void addEdge(Edge edge) {
        String source = edge.getSource(); // get the source city
        adjacencyList.putIfAbsent(source, new LinkedList<>()); // add the source to list
        LinkedList<Edge> newEdgeList = new LinkedList<>();
        newEdgeList.add(edge);
        adjacencyList.get(source).add(newEdgeList);
    }

    public LinkedList<LinkedList<Edge>> getEdges(String source) {
        return adjacencyList.getOrDefault(source, new LinkedList<>());
    }
}
