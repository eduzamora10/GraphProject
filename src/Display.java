import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Display {
    int[] orderArr;
    String[] pathArray;

    public Display(int[] orderArr, String[] pathArray){
        this.orderArr = orderArr;
        this.pathArray = pathArray;
    }

    public void displayPaths(String output, int identifier){
        String label;
        int x = 1;
        if(identifier == 0){ // if 0, then the order is will be from Time
            label = "Time: ";
        }
        else{ // else the order will be from cost
            label = "Cost: ";
        }

        try {
            if (pathArray.length == 0) { // if no paths were found, then display message
                //System.out.println("There are no flight plans between these 2 cities.");
                FileWriter toFile = new FileWriter(output, true);
                toFile.write("There are no flight plans between these 2 cities.\n");
                toFile.close(); // write the message to console and output.txt file
            }
            else {
                for (int order : orderArr) { // looking for the lowest time or cost in string
                    for (String path : pathArray) {
                        if (path.contains(label + order)) { // when found display it to the screen
                            //System.out.println("Path " + x + " : " + path);
                            FileWriter toFile = new FileWriter(output, true);
                            toFile.write("Path " + x + " : " + path + "\n");
                            toFile.close(); // write the message to console and output.txt file
                            x++;
                        }
                        if(x == 4){
                            break; // if there is more than 3 paths break inner loop
                        }
                    }
                    if(x == 4){
                        break; // break outer loop
                    }
                }
            }
        }
        catch (IOException exception){
            exception.printStackTrace();
        }
    }


}
