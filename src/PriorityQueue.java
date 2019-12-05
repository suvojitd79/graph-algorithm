package src;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
    map + heap

    operations ->
    contains - O(1)
    add - O(lg(n))
    removeTop - O(lg(n))
    modify - O(lg(n))

 */


class Node<T>{
    T data;
    int cost;
    Node(){
        this.cost = Integer.MAX_VALUE;
    }
    @Override
    public String toString(){
        return String.format("{ \"data\" :"+data+" ,  \"cost\" :"+cost+"}");
    }
}


public class PriorityQueue<T>{

    private Map<T, Integer> map;
    private Node[] minHeap;
    private int size;


    // 0th index would be the source
    public PriorityQueue(Class<Node> type, List<T> vertices) {

        this.map = new HashMap<>();
        this.minHeap = (Node[]) Array.newInstance(type, vertices.size());
        this.size = vertices.size();

        map.put(vertices.get(0), 0);
        minHeap[0] = new Node();
        minHeap[0].data = vertices.get(0);
        minHeap[0].cost = 0; // cost of source is 0

        for(int i=1;i<vertices.size();i++)
        {
            map.put(vertices.get(i), i);
            minHeap[i] = new Node();
            minHeap[i].data = vertices.get(i);
        }

    }

    public boolean contains(T data){
        return map.containsKey(data); // O(1) operation
    }

    /*
        nodes - tree
        n - upper bound
        i - index of root

     */

    private void heapify(Node[] nodes, int n,int i){

          int root = i;
          int left = 2 * i + 1;
          int right = 2 * i + 2;

          if(left < n && nodes[root].cost > nodes[left].cost)
              root = left;
          if(right < n && nodes[root].cost > nodes[right].cost)
              root = right;

          if(root != i){

                // swap
                Node temp = nodes[root];
                nodes[root] = nodes[i];
                nodes[i] = temp;

              heapify(nodes, n, root);
          }

    }

    /*
        return - gives the node with min cost
        ~O(log(n))
     */

    public Node pollTop(){

         if(size-1 < 0) return null;

        // swap
        Node temp = minHeap[0];
        minHeap[0] = minHeap[size-1];
        minHeap[size-1] = temp;
        size--;
        map.remove(temp.data);
        heapify(minHeap, size, 0);
        return temp;

    }



    /*
        update the cost of a node with a given cost
        ~O(log(n))

     */

    public void update(T key, int cost){

        if (!map.containsKey(key)) return; // do nothing if the key is not there

        int index = map.get(key);
        minHeap[index].cost = cost;

        while (index >= 0){

            heapify(minHeap, size, index);

            if(index == 0) break;

            index = (index-1)/2;

        }

    }



    public static void main(String[] args) {

        PriorityQueue<String> pq = new PriorityQueue(Node.class, Arrays.asList("A","B","C","D"));
        pq.pollTop();
        pq.update("B",30);
        pq.update("C",5);
        pq.pollTop();
        System.out.println(Arrays.toString(pq.minHeap));
    }


}
