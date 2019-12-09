package src;

/*
      find all the strongly connected components of a directed graph
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class P17 {

    LinkedList<Integer> stack;
    boolean[] hasStack;
    boolean[] visited;
    int[] id;
    int[] low_link;
    int time;


    // Tarjan's algorithm, O(V+E)
    private List<List<Integer>> getAllSCC(List<List<Integer>> graph){

        List<List<Integer>> ans = new ArrayList();
        int n = graph.size(); // no of vertices

        if(n == 0) return ans;

        stack = new LinkedList();
        hasStack = new boolean[n];
        visited = new boolean[n];
        id = new int[n];
        low_link = new int[n];
        time = 0;

        for(int i=0;i<n;i++){
            if(!visited[i])
                dfs(i, graph, ans);
        }

        return ans;
    }

    private void dfs(int curr, List<List<Integer>> graph, List<List<Integer>> ans){

        visited[curr] = true;
        stack.addLast(curr);
        hasStack[curr] = true;
        id[curr] = time;
        low_link[curr] = time;
        time += 1;

        for(Integer to: graph.get(curr)){

            if(!visited[to]){

                dfs(to, graph, ans);

                if(hasStack[to]){

                    low_link[curr] = Math.min(low_link[curr], low_link[to]);

                }

                // start of loop
                if(low_link[curr] == id[curr] && hasStack[curr]){

                    List<Integer> t = new ArrayList();

                    while(!stack.isEmpty() && low_link[stack.peekLast()] == id[curr]){

                        int temp = stack.pollLast();
                        t.add(temp);
                        hasStack[temp] = false;

                    }

                    System.out.println("TEMP " + t);//
                }


            }



        }

        // signle item
        if(low_link[curr] == id[curr] && hasStack[curr]){
            ans.add(new ArrayList(Arrays.asList(curr)));
            hasStack[curr] = false;
            stack.pollLast(); // remove the item
            System.out.println("ANS-> " + ans);
        }



    }



    public static void main(String[] args){

        int n = 6; // number of vertices
        List<List<Integer>> g = new ArrayList();
        for(int i=0;i<n;i++) g.add(new ArrayList());

        g.get(0).add(1); // 0 ---> 1 (edge)
        g.get(1).add(2);
        g.get(1).add(3);
        g.get(2).add(0);
        g.get(3).add(4);
        g.get(4).add(3);
        g.get(3).add(5);

        System.out.println(new P17().getAllSCC(g));
    }


}
