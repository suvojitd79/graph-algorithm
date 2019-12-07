package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    Critical Connections in a Network / cut edges

    There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections
    forming a network where connections[i] = [a, b] represents a connection between servers a and b.
    Any server can reach any other server directly or indirectly through the network.

    A critical connection is a connection that, if removed, will make some server unable to reach some
    other server.

    Return all critical connections in the network in any order.

    Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
    Output: [[1,3]]

    Explanation: [[3,1]] is also accepted.

 */


class Solution {

    private List<List<Integer>> ans;
    private boolean[] visited;
    private int[] id;
    private int[] low_link;
    private int time = 0;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {


        ans = new ArrayList();
        visited = new boolean[n];
        Arrays.fill(visited, false);
        id = new int[n];
        low_link = new int[n];
        time = 0;

        List<List<Integer>> graph = new ArrayList();

        for(int i=0;i<n;i++) graph.add(new ArrayList());

        for(int i=0;i<connections.size();i++){

            int from = connections.get(i).get(0);
            int to = connections.get(i).get(1);

            graph.get(from).add(to);
            graph.get(to).add(from);

        }


        for(int i=0;i<n;i++){

            if(!visited[i])
                dfs(graph, i, i);

        }


        return ans;
    }

    public void dfs(List<List<Integer>> graph, int curr, int parent){

        visited[curr] = true;
        id[curr] = time;
        low_link[curr] = time;
        time += 1;


        for(Integer x: graph.get(curr)){

            if(!visited[x]){

                dfs(graph, x, curr);

                low_link[curr] = Math.min(low_link[curr], low_link[x]);

                if(low_link[x] > id[curr])
                    ans.add(Arrays.asList(curr, x));

            }else if(visited[x] && x != parent)
                low_link[curr] = Math.min(low_link[curr], low_link[x]);

        }


    }


}



public class  P16{

    // testing
    public static void main(String[] args) {

//        Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]

//        6
//        [[0,1],[1,2],[2,0],[1,3],[3,4],[4,5],[5,3]]


        Solution sol = new Solution();
        int n = 6;
        List<List<Integer>> input = new ArrayList();

        input.add(Arrays.asList(0, 1));
        input.add(Arrays.asList(1, 2));
        input.add(Arrays.asList(2, 0));
        input.add(Arrays.asList(1, 3));
        input.add(Arrays.asList(3, 4));
        input.add(Arrays.asList(4, 5));
        input.add(Arrays.asList(5, 3));



        System.out.println(sol.criticalConnections(n, input));

    }

}
