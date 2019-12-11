package src;

import java.util.Arrays;

public class P10 {

    // Floyd Warshall's algorithm in O(V^3)
    public static void allPairsSP(long[][] dis){

        for(int k=0;k<dis.length;k++){
            for(int i=0;i<dis.length;i++){
                for(int j=0;j<dis.length;j++){
                    if(dis[i][j] > dis[i][k] + dis[k][j] && dis[i][k] != Integer.MAX_VALUE && dis[k][j] != Integer.MAX_VALUE)
                        dis[i][j] = dis[i][k] + dis[k][j];
                }
            }
        }

    }


    public static void main(String[] args){

 /*
             -5
          <--------
        (0)------->(3)
        |    10    /|\
    15  |           |
        |           | 10
        \|/         |
        (1)------->(2)
              3

  */


        int INF = Integer.MAX_VALUE;

        long graph[][] = { {0,   15,  INF, 10},
                {INF, 0,   3, INF},
                {INF, INF, 0,   10},
                {-5, INF, INF, 0}
        };

        allPairsSP(graph);
        for(long[] row: graph)
            System.out.println(Arrays.toString(row));

    }

}
