// https://www.acmicpc.net/problem/1922

package baekjoon.mst;

import java.io.*;
import java.util.*;

public class NetworkConnection_Prim {

    private static int N, M;
    private static List<List<Node>> graph;

    static class Node {
        int v, cost;

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; ++i) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }


        bw.write("" + prim(1));
        bw.flush();
        bw.close();
        br.close();
    }

    private static int prim(int start) {
        boolean[] visited = new boolean[N + 1];

        PriorityQueue<Node> heap = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        heap.add(new Node(start, 0));

        int res = 0;
        while (!heap.isEmpty()) {
            Node currNode = heap.poll();
            if (visited[currNode.v]) {
                continue;
            }
            visited[currNode.v] = true;
            res += currNode.cost;

            for (Node nextNode: graph.get(currNode.v)) {
                if (!visited[nextNode.v]) {
                    heap.add(nextNode);
                }
            }
        }
        return res;
    }
}
