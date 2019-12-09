package src;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("DuplicatedCode")
public class P11 {

    boolean[] visited;
    List<List<Integer>> reverseGraph;

    // SCC using kosaraju's algorithm in O(V+E)
    public List<List<Integer>> getAllSCC(List<List<Integer>> g){

        List<List<Integer>> ans = new ArrayList();
        int n = g.size();
        if(n == 0) return ans;

        LinkedList<Integer> stack = new LinkedList();
        reverseGraph = new ArrayList();
        for(int i=0;i<n;i++) reverseGraph.add(new ArrayList());
        visited = new boolean[n];
        for(int i=0;i<n;i++)
            if(!visited[i])
                reverse(i, g, stack);
        visited = new boolean[n];
        while(!stack.isEmpty())
        {
            int top = stack.pollLast();

            if(!visited[top])
            {
                List<Integer> temp = new ArrayList();
                DFS(top, reverseGraph, temp);
                ans.add(temp);
            }

        }


        return ans;
    }

    private void reverse(int from, List<List<Integer>> g, LinkedList<Integer> stack){

        visited[from] = true;

        for(Integer to: g.get(from)){

            reverseGraph.get(to).add(from); // reverse the edge

            if(!visited[to])
                reverse(to, g, stack);

        }

        stack.addLast(from); // add it to the stack
    }



    private void DFS(int from, List<List<Integer>> g, List<Integer> temp){

        visited[from] = true; // mark as visited
        temp.add(from);

        for(Integer to: g.get(from))
            if(!visited[to])
                DFS(to, g, temp);

    }



    public static void main(String[] args){

        int n = 7;
        List<List<Integer>> g = new ArrayList();
        for(int i=0;i<n;i++) g.add(new ArrayList());

        g.get(0).add(1); // 0 ---> 1 (edge)
        g.get(1).add(2);
        g.get(2).add(3);
        g.get(3).add(0);

        g.get(2).add(4);
        g.get(4).add(5);
        g.get(5).add(4);
        g.get(5).add(6);

        System.out.println(new P11().getAllSCC(g));

    }


}
