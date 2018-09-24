import java.util.HashSet;
import java.util.LinkedList;

/**
 * this is the Node class
 */
public class Node {
    /**
     * returns the name of the node
     * @return String
     */
    public String getName() {
        return name;
    }

    String name="";
    HashSet<Edge> edges;

    /**
     * returns path
     * @return the path linkedList
     */
    public LinkedList<String> getPath() {
        return path;
    }


    LinkedList<String> path= new LinkedList<>();

    /**
     * returns the int distance
     * @return int
     */
    public int getDistance() {
        return distance;
    }

    /**
     * sets the distance
     * @param distance, int
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    int distance=Integer.MAX_VALUE;
    boolean visited;

    /**
     * constructor that creates a Node with just name
     * @param name, String
     */
    public Node(String name){
        edges = new HashSet<>();
        this.name=name;
        visited=false;
//        this.path.add(this.name);
    }

    /**
     * sets the boolean value
     * @param visited, boolean
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * returns visited
     * @return visited, boolean
     */
    public boolean getVisited() {
        return this.visited;
    }


}
