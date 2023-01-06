// https://www.acmicpc.net/problem/18352

package baekjoon.dijkstra;

import java.io.*;
import java.util.*;

public class FindCityOfSpecificDistance {

    private static final int INF = Integer.MAX_VALUE;

    class Node {
        int idx, dist;

        public Node(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= n; ++i) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
        }

        int[] distance = new int[n + 1];
        for (int i = 0; i <= n; ++i) {
            distance[i] = INF;
        }
        distance[x] = 0;
        PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> a.dist - b.dist);
        heap.add(new Node(x, 0));
        while (!heap.isEmpty()) {
            Node currNode = heap.poll();

            if (currNode.dist > distance[currNode.idx]) {
                continue;
            }

            for (int nextNodeIdx: graph.get(currNode.idx)) {
                int cost = currNode.dist + 1;
                if (distance[nextNodeIdx] > cost) {
                    distance[nextNodeIdx] = cost;
                    heap.add(new Node(nextNodeIdx, cost));
                }
            }
        }

        boolean exist = false;
        for (int i = 1; i <= n; ++i) {
            if (distance[i] != k)
                continue;
            exist = true;
            bw.write("" + i);
            bw.newLine();
            bw.flush();
        }
        if (!exist) {
            bw.write("" + -1);
            bw.flush();
        }
        bw.close();
        br.close();

    }

    public static void main(String[] args) throws Exception {
        new FindCityOfSpecificDistance().solution();
    }
}
