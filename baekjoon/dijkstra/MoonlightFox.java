// https://www.acmicpc.net/problem/16118

package baekjoon.dijkstra;

import java.io.*;
import java.util.*;

public class MoonlightFox {
    private static final int INF = Integer.MAX_VALUE;

    class Node {
        int idx;
        int cost;
        int fast;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        public Node(int idx, int cost, int fast) {
            this.idx = idx;
            this.cost = cost;
            this.fast = fast;
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<List<Node>> graph = new ArrayList<>();

        for (int i = 0; i <= n; ++i)
            graph.add(new ArrayList<>());

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, d));
            graph.get(b).add(new Node(a, d));
        }

        Node currNode;
        int[][] distanceWolf = new int[2][n + 1];
        int[] distanceFox = new int[n + 1];

        for (int i = 0; i <= n; ++i) {
            distanceWolf[0][i] = INF;
            distanceWolf[1][i] = INF;
            distanceFox[i] = INF;
        }

        // wolf
        PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> a.cost - b.cost);

        heap.add(new Node(1, 0, 1));
        distanceWolf[1][1] = 0;
        while (!heap.isEmpty()) {
            currNode = heap.remove();

            if (currNode.cost > distanceWolf[currNode.fast][currNode.idx])
                continue;

            for (Node nextNode: graph.get(currNode.idx)) {
                int cost = currNode.cost;
                if (currNode.fast == 0) {
                    cost += nextNode.cost * 4;
                } else {
                    cost += nextNode.cost;
                }

                if (distanceWolf[1 - currNode.fast][nextNode.idx] > cost) {
                    distanceWolf[1 - currNode.fast][nextNode.idx] = cost;
                    heap.add(new Node(nextNode.idx, cost, 1 - currNode.fast));
                }
            }
        }

        // wolf
        heap.clear();

        heap.add(new Node(1, 0));
        distanceFox[1] = 0;
        while (!heap.isEmpty()) {
            currNode = heap.remove();

            if (currNode.cost > distanceFox[currNode.idx])
                continue;

            for (Node nextNode: graph.get(currNode.idx)) {
                int cost = currNode.cost + nextNode.cost * 2;
                if (distanceFox[nextNode.idx] > cost) {
                    distanceFox[nextNode.idx] = cost;
                    heap.add(new Node(nextNode.idx, cost));
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; ++i) {
            if (distanceFox[i] < Math.min(distanceWolf[0][i], distanceWolf[1][i])) {
                answer++;
            }
        }
        System.out.println(answer);
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new MoonlightFox().solution();
    }
}
