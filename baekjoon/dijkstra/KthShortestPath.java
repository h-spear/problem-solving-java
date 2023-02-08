// https://www.acmicpc.net/problem/1854

package baekjoon.dijkstra;

import java.io.*;
import java.util.*;

public class KthShortestPath {

    private static int n, m, k;
    private static List<List<Node>> graph = new ArrayList<>();

    class Node {
        int idx, cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; ++i) {
            graph.add(new ArrayList<>());
        }

        int a, b, c;
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, c));
        }

        // dijkstra
        PriorityQueue<Integer>[] distancePQ = new PriorityQueue[n + 1];
        for (int i = 0; i <= n; ++i) {
            distancePQ[i] = new PriorityQueue<>(Collections.reverseOrder());
        }

        PriorityQueue<Node> heap = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        heap.add(new Node(1, 0));
        distancePQ[1].add(0);

        while (!heap.isEmpty()) {
            Node currNode = heap.poll();

            for (Node nextNode: graph.get(currNode.idx)) {
                int cost = currNode.cost + nextNode.cost;
                if (distancePQ[nextNode.idx].size() < k) {
                    distancePQ[nextNode.idx].add(cost);
                    heap.add(new Node(nextNode.idx, cost));
                } else if (distancePQ[nextNode.idx].peek() > cost) {
                    distancePQ[nextNode.idx].poll();
                    distancePQ[nextNode.idx].add(cost);
                    heap.add(new Node(nextNode.idx, cost));
                }
            }
        }

        for (int i = 1; i <= n; ++i) {
            if (distancePQ[i].size() < k) {
                bw.write(-1 + "\n");
            } else {
                bw.write(distancePQ[i].peek() + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new KthShortestPath().solution();
    }
}
