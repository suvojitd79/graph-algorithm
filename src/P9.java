package src;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P9 {

    static class Info{
        String des;
        Long cost;
        Info(String d,Long c){
            this.des = d;
            this.cost = c;
        }
    }

    // Bellman's ford algorithm in O(EV)
    public static Map<String,Long> singleSSP(Map<String, List<Info>> graph, String source){

            Map<String, Long> dis = new HashMap();
            for(String key: graph.keySet()){
                dis.put(key, Integer.MAX_VALUE + 100l);
            }
            if(!dis.containsKey(source))
                return dis;
            dis.put(source, 0l);

            int V = graph.size();

            for(int i=1;i<=V-1;i++){

                for(String key: graph.keySet()){

                    for(Info info: graph.get(key)){

                        if(dis.get(key) + info.cost < dis.get(info.des) && dis.get(key) < Integer.MAX_VALUE)
                            dis.put(info.des, dis.get(key) + info.cost);

                    }

                }

            }

            // negative edge cycle detection

            for(String key: graph.keySet()){

                for(Info info: graph.get(key)){

                if(dis.get(key) + info.cost < dis.get(info.des) && dis.get(key) < Integer.MAX_VALUE)
                    try {
                        throw new Exception("-ve weight cycle");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }


            return dis;
    }


    public static void main(String[] args){

        Map<String,List<Info>> graph = new HashMap();
        graph.put("A", Arrays.asList(new Info("B",-2l)));
        graph.put("B", Arrays.asList(new Info("C",-3l)));
        graph.put("C", Arrays.asList(new Info("A",-3l)));



        System.out.println(singleSSP(graph, "A"));

    }

}
