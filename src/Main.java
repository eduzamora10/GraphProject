import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("<FlightDataFile>: ");
        String filghtData = scanner.nextLine();
        System.out.print("<PathsToCalculate>: ");
        String pathsToCalculate = scanner.nextLine();
        System.out.print("<OutputFile>: ");
        String outputFile = scanner.nextLine();
        readData data = new readData(filghtData);// read everything from flightData.txt
        int numLines = data.getNumLines(); // store the number of lines from flightData.txt
        String[] flightData = data.getData(); // store the rest of the data in flightData
        int number = 0; // set identifier to 0 - (we will disect the flightData.txt file first)
        Graph graph = new Graph(); // we will create a graph to store all our data from flightData.txt
        for(int i = 0; i < numLines; i++){
            gatherData getdata = new gatherData(flightData[i], number);
            String source = getdata.getSource()[0]; // get source city from the current line
            String dest = getdata.getDestination()[0];// get destination city from the current line
            int cost = getdata.getCost()[0]; // get cost of the path from source to destination from the current line
            int time = getdata.getTime()[0]; // // get time of the path from source to destination from the current line
            Edge edge = new Edge(source, dest, cost, time); // put all 4 data into one edge
            graph.addEdge(edge); // add the edge that we created to the graph
            Edge edge2 = new Edge(dest, source, cost, time); // add the exact same to obtain an undirected graph model
            graph.addEdge((edge2)); // add the edge to the graph
        }
        number = 1; // change identifier to let program the next file to disect is requestedFlights.txt
        readData request = new readData(pathsToCalculate); // read everything from requestedFlights.txt
        int numRequests = request.getNumLines(); // store the number of lines from requestedFlights.txt
        String[] flightRequest = request.getData(); // store the rest of the data in flightRequest
        for (int i = 0; i < numRequests; i++) { // for loop to go through the number of flights we need to find paths for
            gatherData print = new gatherData(flightRequest[i], number);
            String order = print.getOrder()[0]; // get the order that the paths must be ordered
            String source = print.getSource()[0]; // get the source city
            String dest = print.getDestination()[0]; // get the destination city
            FindPaths finder = new FindPaths(graph, source, dest); // find all paths from source and destination city
            List<List<Edge>> paths = finder.findAllPaths(); // store all paths into list
            List<Integer> totalTime = finder.findTotalTime(); // get the total time from each path and store it in list
            int count = 0;
            int[] timeArr = new int[numLines*2]; // an array to hold sufficient number of total times from all paths
            int[] costArr = new int[numLines*2]; // an array to hold sufficient number of total costs from all paths
            int index = 0; // index for the array above ^^
            for(int time : totalTime){
                timeArr[index] = time; // get the time from path and store it into the array
                index++; // increment the index
            }
            List<Integer> totalCost = finder.findTotalCost(); // put all paths total cost in this list
            index = 0; // put index back to 0 (we will use for bottom code)
            for(int cost : totalCost){
                costArr[index] = cost; // get the cost from path and store it into the array
                index++; // increment the index
            }
            index = 0; // put index back to 0 (we will use for bottom code)
            int flightNum = i + 1; // variable to keep track of the flights we are checking
            String[] pathArray = new String[paths.size()]; // create an array to store the strings of the valid paths
            for (List<Edge> path : paths) { // go through all paths we found from graph
                count = 0;
                if(path.get(path.size()-1).getDestination().equals(dest)) {
                    StringBuilder sb = new StringBuilder(); // use to build the path string
                    for (Edge edge : path) { // for each edge in the path...
                        sb.append(edge.getDestination()); // add the current city to string
                        count++;
                        if (count != path.size()) { // if the current city is anything other than destination
                            sb.append(" -> "); // print arrow & add to string
                        }
                        else{ // else add what's bellow to string
                            sb.append(". Time: " + timeArr[index] + " Cost: " + costArr[index]);
                        }
                    }
                    String pathString = sb.toString(); // make the string
                    pathArray[index] = pathString; // put string into array
                    index++; // increment index
                }
            }
            FileWriter toFile = new FileWriter(outputFile, true);
            if(order.equals("T")){ // if order is time...
                // display the flight, source, and destination city along with the order the flight paths will be displayed
                //System.out.println("Flight " + flightNum + ": " + source + ", " + dest + " (Time)");
                toFile.write("Flight " + flightNum + ": " + source + ", " + dest + " (Time)\n");
                toFile.close(); // write the message above in console and output.txt
                Arrays.sort(timeArr); // sort the time array
                Display display = new Display(timeArr, pathArray); // to help us print the correct order
                display.displayPaths(outputFile,0); // display the correct path order to screen (time order)
            }
            else{ // if order is cost...
                // display the flight, source, and destination city along with the order the flight paths will be displayed
                //System.out.println("Flight " + flightNum + ": " + source + ", " + dest + " (Cost)");
                toFile.write("Flight " + flightNum + ": " + source + ", " + dest + " (Cost)\n");
                toFile.close(); // write the message above in console and output.txt
                Arrays.sort(costArr); // sort the cost Array
                Display display = new Display(costArr, pathArray); // to help us print the correct order
                display.displayPaths(outputFile,1); // display the correct path order to screen (cost order)
            }
        }
        System.out.print("Wrote output to " + outputFile); // message that lets user know output has been written
        FileWriter toFile = new FileWriter(outputFile, true);
        toFile.write("*********************************************************************************************\n");
        toFile.close(); // write seperator in output.txt file to be more appealing to the eye
    }
}