package src;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class P1 {

    private static void DFS(Map<String, List<String>> graph, String key, Set<String> visited){

        visited.add(key); // mark

        System.out.print(key+" ");

        for(String friend: graph.get(key)){

            if(!visited.contains(friend))
                DFS(graph, friend, visited);

        }

    }

    public static void main(String[] args) {

        Map<String, List<String>> graph = Graph.getGraph();
        Set<String> visited = new HashSet();
        for(String friend: graph.keySet()){

            if(!visited.contains(friend))
                DFS(graph, friend, visited);
        }

    }

}