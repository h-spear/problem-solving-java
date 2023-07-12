// 15623

package swea.difficulty4;

import java.io.*;
import java.util.*;

public class DoubleShortestPath {

    private static class Node {
        int idx, x, y;

        Node(int idx, int x, int y) {
            this.idx = idx;
            this.x = x;
            this.y = y;
        }
    }

    private static List<List<Node>> graph;
    private static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; ++test_case) {
            try {
                st = new StringTokenizer(br.readLine());
                N = Integer.parseInt(st.nextToken());
                M = Integer.parseInt(st.nextToken());

                graph = new ArrayList<>();
                for (int i = 0; i <= N; ++i) {
                    graph.add(new ArrayList<>());
                }

                int A, B, X, Y;
                for (int i = 0; i < M; ++i) {
                    st = new StringTokenizer(br.readLine());
                    A = Integer.parseInt(st.nextToken());
                    B = Integer.parseInt(st.nextToken());
                    X = Integer.parseInt(st.nextToken());
                    Y = Integer.parseInt(st.nextToken());
                    graph.get(A).add(new Node(B, X, Y));
                    graph.get(B).add(new Node(A, X, Y));
                }
                bw.write("#" + test_case + " " + dijkstra(1, 2) + "\n");
            } catch (Exception e) {
                --test_case;    // 문제에 입력 오류 있음
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int dijkstra(int start, int end) {
        PriorityQueue<Node> heap = new PriorityQueue<>((o1, o2) -> o1.x * o1.y - o2.x * o2.y);
        int[] distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
        heap.add(new Node(start, 0, 0));
        while (!heap.isEmpty()) {
            Node currNode = heap.poll();

            if (distance[currNode.idx] < currNode.x * currNode.y) {
                continue;
            }

            for (Node nextNode: graph.get(currNode.idx)) {
                int cost = (currNode.x + nextNode.x) * (currNode.y + nextNode.y);
                if (distance[nextNode.idx] > cost) {
                    distance[nextNode.idx] = cost;
                    heap.add(new Node(nextNode.idx, currNode.x + nextNode.x, currNode.y + nextNode.y));
                }
            }
        }
        return distance[end] != Integer.MAX_VALUE ? distance[end] : -1;
    }

}