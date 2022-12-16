package programmers.level3;// https://school.programmers.co.kr/learn/courses/30/lessons/42861?language=java

import java.util.*;

class IslandConnection {
    class Node {
        int idx;
        int cost;

        Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    private static int find(int[] parent, int x) {
        if (parent[x] != x)
            parent[x] = find(parent, parent[x]);
        return parent[x];
    }

    private static void union(int[] parent, int a, int b) {
        a = find(parent, a);
        b = find(parent, b);
        if (a < b)
            parent[b] = a;
        else
            parent[a] = b;
    }

    public int solution(int n, int[][] costs) {
        int answer = 0;
        int m = costs.length;
        int[] parent = new int[n + 1];
        for (int i = 0; i <= n; ++i)
            parent[i] = i;

        // kruskal
        Arrays.sort(costs, (a, b) -> a[2] - b[2]);
        for (int[] cost: costs) {
            int a = cost[0];
            int b = cost[1];
            int c = cost[2];

            if (find(parent, a) == find(parent, b))
                continue;

            answer += c;
            union(parent, a, b);
        }
        return answer;
    }
}