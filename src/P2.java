package src;

import java.util.*;

public class P2 {


    public static void BFS(Map<String, List<String>> graph, String source, Set<String> visited){

        LinkedList<String> queue = new LinkedList<>();
        queue.add(source);

        while (!queue.isEmpty()){

            String key = queue.pollFirst();
            System.out.print(key + "  ");

            for(String friend: graph.get(key))
            {
                if(visited.add(friend)){

                    queue.addLast(friend);

                }

            }

        }

    }


    public static void main(String[] args) {

        Map<String, List<String>> graph = Graph.getGraph();
        Set<String> visited = new HashSet();
        for(String friend: graph.keySet()){
            if(visited.add(friend))
                BFS(graph, friend, visited);
        }

    }

}
