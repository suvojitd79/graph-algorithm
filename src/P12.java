package src;

import java.util.*;

// cycle detection algorithms
public class P12{


    public static boolean hasCycle(Map<String, List<String>> graph){

        Set<String> white = new HashSet<>(graph.keySet()); // haven't visited
        Set<String> grey = new HashSet<>(); // visiting
        Set<String> black = new HashSet<>(); // visited

        while(!white.isEmpty()){
            Iterator<String> itr = white.iterator();
            if(DFS(graph, itr.next(), white, grey, black)) return true;
        }
        return false;
    }

    private static boolean DFS(Map<String, List<String>> graph, String key, Set<String> white,Set<String> grey,Set<String> black){

        white.remove(key);
        grey.add(key);

        for (String k: graph.get(key))
        {
            if(grey.contains(k)) return true;

            if(!black.contains(k) && DFS(graph, k, white,grey, black))
                return true;

        }

        grey.remove(key);
        black.add(key);
        return false;
    }


    public static void main(String[] args) {

        Map<String,List<String>> graph = new HashMap<>();
        graph.put("A",new ArrayList<>(Arrays.asList("B")));
        graph.put("B",new ArrayList<>(Arrays.asList("C")));
        graph.put("C",new ArrayList<>(Arrays.asList("D")));
        graph.put("D",new ArrayList<>(Arrays.asList("A")));

        System.out.println(hasCycle(graph));
    }

}
