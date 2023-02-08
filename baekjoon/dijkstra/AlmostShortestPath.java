// https://www.acmicpc.net/problem/5719

package baekjoon.dijkstra;

import java.io.*;
import java.util.*;

public class AlmostShortestPath {

    private static final int INF = Integer.MAX_VALUE;
    private static int N, M, S, D;
    private static long[] distance;
    private static List<List<Node>> graph;
    private static List<List<Node>> revGraph;
    private static boolean[][] removed;

    class Node {
        int idx;
        long cost;

        public Node(int idx, long cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "idx=" + idx +
                    ", cost=" + cost +
                    '}';
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int U, V, P;

        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) {
                break;
            }

            // 초기화
            graph = new ArrayList<>();
            revGraph = new ArrayList<>();
            distance = new long[N];
            removed = new boolean[N][N];
            for (int i = 0; i < N; ++i) {
                graph.add(new ArrayList<>());
                revGraph.add(new ArrayList<>());
                distance[i] = INF;
            }

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            for (int i = 0; i < M; ++i) {
                st = new StringTokenizer(br.readLine());
                U = Integer.parseInt(st.nextToken());
                V = Integer.parseInt(st.nextToken());
                P = Integer.parseInt(st.nextToken());
                graph.get(U).add(new Node(V, P));
                revGraph.get(V).add(new Node(U, P));
            }

            distance[S] = 0;
            PriorityQueue<Node> heap = new PriorityQueue<>((o1, o2) -> (int)(o1.cost - o2.cost));
            heap.add(new Node(S, 0));
            while (!heap.isEmpty()) {
                Node curr = heap.poll();

                if (distance[curr.idx] > curr.cost) {
                    continue;
                }

                for (Node next: graph.get(curr.idx)) {
                    long cost = distance[curr.idx] + next.cost;
                    if (distance[next.idx] > cost) {
                        distance[next.idx] = cost;
                        heap.add(new Node(next.idx, cost));
                    }
                }
            }

            // D -> S 역추적
            // on 으로 체크(중복으로 노드가 들어가지 않도록)
            boolean[] on = new boolean[N];
            Queue<Integer> queue = new LinkedList<>();
            queue.add(D);
            on[D] = true;
            while (!queue.isEmpty()) {
                int curr = queue.poll();
                for (Node nextNode: revGraph.get(curr)) {
                    if (distance[curr] - distance[nextNode.idx] == nextNode.cost) {
                        removed[nextNode.idx][curr] = true;
                        if (on[nextNode.idx] == false) {
                            on[nextNode.idx] = true;
                            queue.add(nextNode.idx);
                        }
                    }
                }
            }

            // distance 초기화
            for (int i = 0; i < N; ++i) {
                distance[i] = INF;
            }
            distance[S] = 0;
            heap = new PriorityQueue<>((o1, o2) -> (int)(o1.cost - o2.cost));
            heap.add(new Node(S, 0));

            while (!heap.isEmpty()) {
                Node curr = heap.poll();

                if (distance[curr.idx] > curr.cost) {
                    continue;
                }

                for (Node next: graph.get(curr.idx)) {
                    if (removed[curr.idx][next.idx]) {
                        continue;
                    }

                    long cost = distance[curr.idx] + next.cost;
                    if (distance[next.idx] > cost) {
                        distance[next.idx] = cost;
                        heap.add(new Node(next.idx, cost));
                    }
                }
            }

            if (distance[D] == INF) {
                bw.write(-1 + "\n");
            } else {
                bw.write(distance[D] + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new AlmostShortestPath().solution();
    }
}
