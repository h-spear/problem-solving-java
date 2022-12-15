// https://www.acmicpc.net/problem/1504

package baekjoon.dijkstra;

import java.io.*;
import java.util.*;

public class SpecificShortestPath {
    private static final int INF = Integer.MAX_VALUE;

    class Node {
        int idx;
        int cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    private void dijkstra(List<List<Node>> graph, int[] distance, int x) {
        PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        heap.add(new Node(x, 0));
        distance[x] = 0;
        while (!heap.isEmpty()) {
            Node currNode = heap.remove();

            if (currNode.cost > distance[currNode.idx])
                continue;

            for (Node nextNode: graph.get(currNode.idx)) {
                int cost = currNode.cost + nextNode.cost;
                if (distance[nextNode.idx] > cost) {
                    distance[nextNode.idx] = cost;
                    heap.add(new Node(nextNode.idx, cost));
                }
            }
        }
    }

    private void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        List<List<Node>> graph = new ArrayList<>();

        for (int i = 0; i <= n; ++i) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < e; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int[] distanceV1 = new int[n + 1];
        int[] distanceV2 = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            distanceV1[i] = INF;
            distanceV2[i] = INF;
        }

        dijkstra(graph, distanceV1, v1);
        dijkstra(graph, distanceV2, v2);

        int v1v2 = INF;
        int v2v1 = INF;
        if (distanceV1[1] != INF && distanceV1[v2] != INF && distanceV2[n] != INF)
            v1v2 = distanceV1[1] + distanceV1[v2] + distanceV2[n];
        if (distanceV2[1] != INF && distanceV2[v1] != INF && distanceV1[n] != INF)
            v2v1 = distanceV2[1] + distanceV2[v1] + distanceV1[n];

        int answer = Math.min(v1v2, v2v1);
        if (answer >= INF)
            System.out.println(-1);
        else
            System.out.println(Math.min(v1v2, v2v1));
    }

    public static void main(String[] args) throws Exception {
        new SpecificShortestPath().solution();
    }
}
