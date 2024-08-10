import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class readData {
    private int numLines;
    private String[] data;

    public readData(String filename) throws IOException{
        BufferedReader read = new BufferedReader(new FileReader(filename)); // allows us to read file

        // get the number from flightData.txt and store it here
        numLines = Integer.parseInt(read.readLine());

        // create the data string array with numLines size
        data = new String[numLines];

        for(int i = 0; i < numLines; i++){
            data[i] = read.readLine(); // read the lines of data and store it in array
        }

        read.close(); // close the file we opened
    }

    public int getNumLines(){
        return numLines;
    } // return the number of lines

    public String[] getData(){
        return data;
    } // return the rest of the data after number of lines
}
