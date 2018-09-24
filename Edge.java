/**
 * this is Edge class
 */
import java.util.*;
public class Edge {
    Node A;
    Node B;
    int cost;

    /**
     * this is the edge constructor
     * @param A, Node
     * @param B, Node
     * @param cost, int
     */
    public Edge(Node A, Node B, int cost){
        this.A=A;
        this.B=B;
        this.cost=cost;
    }

    /**
     * returns the node A
     * @return, A
     */
    public Node getA() {
        return A;
    }

    /**
     * sets A
     * @param a, Node
     */
    public void setA(Node a) {
        A = a;
    }

    /**
     * returns the node
     * @return,
     */
    public Node getB() {
        return B;
    }

    /**
     * returns the cost
     * @return cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * make a formatted String
     * @return String
     */
    public String toString(){
        return A.getName()+" to "+B.getName()+" "+cost;
    }

    /**
     * this is compareTo method
     * @param otherEdge, Object
     * @return int value
     */
    public int compareTo(Object otherEdge){
        Edge e = (Edge) otherEdge;
        if(this.cost>e.getCost())
        {
            return 1;
        }
        else if(this.cost<e.getCost()){
            return -1;
        }
        else{
            return 0;
        }
    }


}
