package src;

import java.util.*;

public class P5{

    static class Edge<T>{
        T v1,v2;
        int val;
        Edge(T v1, T v2, int val){
            this.v1 = v1;
            this.v2 = v2;
            this.val = val;
        }
    }

    // O(Elog(E) + E)
    public static int minCost(List<Edge<Character>> edges, List<Character> vertices){

        Collections.sort(edges, (e1, e2)-> e1.val-e2.val);

        DisjointSet<Character> ds = new DisjointSet();

        ds.makeSet(vertices);

        int cost = 0;


        for(Edge<Character> edge: edges){

            if(ds.findSet(edge.v1)  == ds.findSet(edge.v2) && ds.findSet(edge.v2) != null)
                continue;

            ds.union(edge.v1, edge.v2);

            cost += edge.val;


        }
        return cost;
    }



    public static void main(String[] args){


    /*
          10  9
        A - B - E
    130 |   | 20
        C - D
          5

     */

        List<Edge<Character>> edges = new ArrayList();
        edges.add(new Edge('A','B',10));
        edges.add(new Edge('C','D',5));
        edges.add(new Edge('A','C',130));
        edges.add(new Edge('B','D',20));
        edges.add(new Edge('B','E',9));

        List<Character> vertices = new ArrayList<>(Arrays.asList('A','B','C','D','E'));


        System.out.println(minCost(edges, vertices));

    }

}
