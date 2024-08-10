public class Edge {
    private String source; // variables to store the respected information
    private String destination;
    private int cost;
    private int time;

    public Edge(String source, String destination, int cost, int time) {
        this.source = source; // set out variables to what is in our parameters
        this.destination = destination;
        this.cost = cost;
        this.time = time;
    }

    public String getSource() {
        return source; // return the source
    }

    public String getDestination() {
        return destination; // return the destination
    }

    public int getCost() {
        return cost; // return the cost
    }

    public int getTime() {
        return time; // return the time
    }
}
