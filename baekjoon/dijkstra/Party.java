// https://www.acmicpc.net/problem/1238

package baekjoon.dijkstra;

import java.io.*;
import java.util.*;

public class Party {
    private static final int INF = Integer.MAX_VALUE;

    class Node {
        int idx;
        int cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    private void dijkstra(int x, List<List<Node>> graph, int[] distance) {
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

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        List<List<Node>> graph = new ArrayList<>();
        List<List<Node>> reverseGraph = new ArrayList<>();

        for (int i = 0; i <= n; ++i) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, t));
            reverseGraph.get(b).add(new Node(a, t));
        }

        int[] distance = new int[n + 1];
        int[] reverseDistance = new int[n + 1];

        for (int i = 1; i <= n; ++i) {
            distance[i] = INF;
            reverseDistance[i] = INF;
        }
        dijkstra(x, graph, distance);
        dijkstra(x, reverseGraph, reverseDistance);

        int answer = 0;

        for (int i = 1; i <= n; ++i) {
            int t = distance[i] + reverseDistance[i];
            answer = answer > t ? answer : t;
        }
        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception {
        new Party().solution();
    }
}
