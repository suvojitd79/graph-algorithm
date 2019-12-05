package src;

import java.util.*;

public class P3 {

    public static void topSort(Map<String, List<String>> graph, String source, Stack<String> stack, Set<String> visited){


        for (String s: graph.get(source)){

            if (visited.add(s))
                topSort(graph, s, stack, visited);

        }

        stack.push(source);
    }


    public static void main(String[] args) {

        Map<String, List<String>> graph = Graph.getDirectedGraph();
        Stack<String> stack = new Stack<>();
        Set<String> visited = new HashSet<>();

        for (String source: graph.keySet()){

            if(visited.add(source))
                topSort(graph, source, stack, visited);

        }

        while (!stack.isEmpty())
            System.out.print(stack.pop() + " ");
    }
}
