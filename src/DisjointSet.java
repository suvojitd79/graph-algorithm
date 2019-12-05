package src;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisjointSet <T>{

    private Map<T, Node> map;

    class Node{
        T data;
        int rank; // depth
        Node parent;
        Node(T data){
            this.data = data;
            this.rank = 0;
            this.parent = this;
        }
    }

    public DisjointSet() {
        map = new HashMap<>();
    }

    public void makeSet(List<T> keys){
        keys.forEach(e->map.put(e, new Node(e)));
    }

    public void makeSet(T e){
        map.put(e, new Node(e));
    }

    public void union(T x,T y){

        Node x1 = findSet(x);
        Node y1 = findSet(y);

        if(x1 == null || y1 == null)
            throw new IllegalArgumentException("Data invalid");
        else if(x1 == y1) return; // done
        else {

            if(x1.rank >= y1.rank){
                x1.rank = x1.rank == y1.rank ? x1.rank + 1: x1.rank;
                y1.parent = x1;
            }else
                x1.parent = y1;

        }

    }

    public Node findSet(T x){
        if(!map.containsKey(x)) return null;
        return findSet(map.get(x));
    }


    public Node findSet(Node x){
        if(x.parent == x) return x;
        x.parent = findSet(x.parent);
        return x.parent;
    }

    public static void main(String[] args){

        DisjointSet<Integer> ds = new DisjointSet();
        ds.makeSet(Arrays.asList(1,2,3));
        ds.union(1,2);
        ds.union(1,3);
        System.out.println(ds.findSet(3).data);
    }

}
