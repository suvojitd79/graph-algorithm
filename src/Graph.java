package src;

import java.util.*;

public class Graph {

    public static Map<String, List<String>> getGraph(){

        Map<String, List<String>> graph = new HashMap();
        graph.put("suvojit", new ArrayList<>(Arrays.asList("pinak","prottay","suryans","soumit")));
        graph.put("soumit", new ArrayList<>(Arrays.asList("suvojit")));
        graph.put("pinak", new ArrayList<>(Arrays.asList("suvojit","prottay","suryans","shreya")));
        graph.put("prottay", new ArrayList<>(Arrays.asList("pinak","suvojit","suryans","shreya")));
        graph.put("suryans", new ArrayList<>(Arrays.asList("pinak","prottay","shreya")));
        graph.put("shreya", new ArrayList<>(Arrays.asList("pinak","prottay","suryans")));
        graph.put("alex", new ArrayList<>());
        return graph;
    }

    public static Map<String, List<String>> getDirectedGraph(){

        return null;

    }


}
