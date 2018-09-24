import big.data.DataSource;

import java.util.*;

/**
 * this is the RoadCalculator class which performs
 */
public class RoadCalculator {
    private static HashMap<String, Node> graph;
    private static LinkedList<Edge> mst;
    public static String[] cityNames;
    public static ArrayList<Edge> allE = new ArrayList<Edge>();

    public static void main(String args[]){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter graph URL:");
        String url = input.nextLine();
        graph=buildGraph(url);
        buildMST();
        System.out.println("\nMST: ");
        for (int i = 0; i < mst.size() ; i++) {
            System.out.println(mst.get(i));
        }
        while(true) {
            try {
                System.out.println();
                System.out.println("Enter a starting point for shortest path or Q to quit:");
                String j = input.nextLine();
                if (j.toUpperCase().equals("Q")) {
                    System.exit(0);
                }
                System.out.println("Enter a destination:");
                String k = input.nextLine();
                int f = Djikstra(graph, j, k);
                System.out.println("Distance: " + f);
                System.out.println("Path: ");
                String print = "";
                for (int i = 0; i < graph.get(k).getPath().size(); i++) {
                    print += graph.get(k).getPath().get(i) + ",";
                }
                System.out.println(print);
                reloadPath();

            }catch(Exception e){
                System.out.println("Invalid input");
            }

        }
    }
    public static HashMap<String, Node> buildGraph(String location)
    {
        HashMap<String, Node> graph2 = new HashMap<>();
        //http://www3.cs.stonybrook.edu/~cse214/hw/hw7-images/hw7.xml
        DataSource ds = DataSource.connectXML(location);
        ds.load();
        String cityNamesStr=ds.fetchString("cities");
        cityNames=cityNamesStr.substring(1,cityNamesStr.length()-1).replace("\"","").split(",");
        String roadNamesStr=ds.fetchString("roads");
        String[] roadNames=roadNamesStr.substring(2,roadNamesStr.length()-2).split("\",\"");


        System.out.println();
        System.out.println("Cities:");
        for (int i = 0; i < cityNames.length; i++) {
            System.out.println(cityNames[i]);
            Node x = new Node(cityNames[i]);
            graph2.put(cityNames[i],x);
        }

        System.out.println();
        System.out.println("Roads:");
        for (int i = 0; i < roadNames.length; i++) {
            String arr[] = roadNames[i].split(",");
            System.out.println(arr[1]+" to "+arr[0]+" "+arr[2]);
            Edge e = new Edge(graph2.get(arr[0]),graph2.get(arr[1]),Integer.parseInt(arr[2]));
            allE.add(e);
            Edge e2 = new Edge(graph2.get(arr[1]),graph2.get(arr[0]),Integer.parseInt(arr[2]));
            graph2.get(arr[0]).edges.add(e);
            graph2.get(arr[1]).edges.add(e2);
            Collections.sort(allE,new edgeComparator());
        }
    return graph2;
    }
    public static int Djikstra(HashMap<String,Node> graph2, String source, String dest) {
        int k = 0;
        graph2.get(source).setDistance(0);
        //graph2.get(source).getPath().add(source);
        for (int i = 0; i < cityNames.length; i++) {
            graph2.get(cityNames[i]).setVisited(false);
        }
        graph2.get(source).setVisited(true);
        ArrayList<String> unvisited = new ArrayList<String>(Arrays.asList(cityNames));
        ArrayList<String> notI = new ArrayList<>();
        for (int i = 0; i < cityNames.length; i++) {
            notI.add(cityNames[i]);
        }
        int loop = unvisited.size() - 1;
        for (int i = 1; i < loop; i++) {
            int next = minKey2(unvisited,graph2);
            // need to  figure the path graph2.get(source).getPath().add(unvisited.get(next));

            for (Edge e : graph2.get(unvisited.get(next)).edges) {
                if (graph2.get(unvisited.get(next)).getDistance() + e.getCost() < e.getB().getDistance()) {
                    e.getB().setDistance(graph2.get(unvisited.get(next)).getDistance() + e.getCost());
                    e.getB().getPath().addAll(e.getA().getPath());
                    e.getB().getPath().add(e.getA().getName());
                }
            }
            unvisited.remove(next);
        }
        //graph2.get(source).getPath().remove(graph2.get(source).getPath().size()-1);

        return graph2.get(dest).getDistance();
    }
    public static int minKey2(ArrayList<String> notI,HashMap<String,Node> graph2)
    {
        int min = Integer.MAX_VALUE, min_index=-1;

        for (int v = 0; v < notI.size(); v++)
            if (graph2.get(notI.get(v)).getDistance()< min)
            {
                min = graph2.get(notI.get(v)).getDistance();
                min_index = v;
            }

        return min_index;
    }

    public static void reloadPath(){
        for (int i = 0; i < cityNames.length; i++) {
            graph.get(cityNames[i]).getPath().clear();
            graph.get(cityNames[i]).setDistance(Integer.MAX_VALUE);
        }
    }

    public static void buildMST(){
        mst = new LinkedList<>();
        allE.get(0).getA().setVisited(true);
        allE.get(0).getB().setVisited(true);
        mst.add(allE.get(0));
        while(mst.size()<cityNames.length-1){
            boolean isAdded = false;
            for (int i = 0; i < allE.size() && !isAdded ; i++) {
                if(allE.get(i).getB().getVisited()!=allE.get(i).getA().getVisited()){
                    allE.get(i).getB().setVisited(true);
                    allE.get(i).getA().setVisited(true);
                    mst.add(allE.get(i));
                    isAdded=true;
                }
            }
        }
    }


}