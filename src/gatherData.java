public class gatherData {
    private String[] source; // variables to store their respected information
    private String[] destination;
    private int[] cost;
    private int[] time;
    private String[] order;

    public gatherData(String line, int identifier){
        String[] str = line.split("\\|"); // remove the pipe '|' from string

        source = new String[]{
                str[0] // put 1st string to source array
        };

        destination = new String[]{
                str[1] // put 2nd string to destination array
        };

        if(identifier == 0) { // if 0, we are reading flightData.txt
            cost = new int[]{
                    Integer.parseInt(str[2]) // get cost from line
            };

            time = new int[]{
                    Integer.parseInt(str[3]) // get time from line
            };
        }
        else{
            order = new String[]{
                    str[2] // get the 3rd string from line
            };
        }
    }

    public String[] getSource() {
        return source;
    }

    public String[] getDestination() {
        return destination;
    }

    public int[] getCost() {
        return cost;
    }

    public int[] getTime() {
        return time;
    }

    public String[] getOrder() {
        return order;
    }

}
