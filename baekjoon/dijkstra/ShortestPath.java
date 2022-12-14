// https://www.acmicpc.net/problem/1753
/**
 * LinkedList를 사용하면 시간초과
 *
 * 조회가 빈번하다면 ArrayList
 * 추가, 삭제가 빈번하다면 LinkedList
 */

package baekjoon.dijkstra;

import java.io.*;
import java.util.*;

public class ShortestPath {
    class Node {
        int idx;
        int cost;

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
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());

        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= v; ++i) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < e; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, c));
        }

        int[] distance = new int[v + 1];
        for (int i = 0; i <= v; ++i)
            distance[i] = Integer.MAX_VALUE;

        PriorityQueue<Node> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        heap.add(new Node(k, 0));
        distance[k] = 0;
        while (!heap.isEmpty()) {
            Node currNode = heap.remove();

            if (currNode.cost > distance[currNode.idx])
                continue;

            for (Node nextNode : graph.get(currNode.idx)) {
                int cost = currNode.cost + nextNode.cost;
                if (distance[nextNode.idx] > cost) {
                    distance[nextNode.idx] = cost;
                    heap.add(new Node(nextNode.idx, cost));
                }
            }
        }

        for (int i = 1; i <= v; ++i) {
            if (distance[i] == Integer.MAX_VALUE)
                bw.write("INF");
            else
                bw.write("" + distance[i]);
            bw.newLine();
        }
        br.close();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        new ShortestPath().solution();
    }
}
