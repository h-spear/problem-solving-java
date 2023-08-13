// https://www.acmicpc.net/problem/1939

package baekjoon.binarysearch;

import java.io.*;
import java.util.*;

public class WeightLimit {

    private static class Node {
        int idx, weight;

        public Node(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }
    }

    private static int N;
    private static int origin, destination;
    private static List<List<Node>> graph;
    private static boolean[] visited;
    private static Queue<Integer> queue;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int A, B, C, maxWeight = 0;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>(N + 1);
        queue = new ArrayDeque<>(13);
        visited = new boolean[N + 1];
        for (int i = 0; i <= N; ++i)
            graph.add(new ArrayList<>());

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            graph.get(A).add(new Node(B, C));
            graph.get(B).add(new Node(A, C));
            maxWeight = Math.max(maxWeight, C);
        }

        st = new StringTokenizer(br.readLine());
        origin = Integer.parseInt(st.nextToken());
        destination = Integer.parseInt(st.nextToken());

        int left, mid, right, answer = 0;
        left = 0;
        right = maxWeight;
        while (left <= right) {
            mid = (left + right) >> 1;
            if (bfs(mid)) {
                left = mid + 1;
                answer = mid;
            } else {
                right = mid - 1;
            }
            bw.flush();
        }
        bw.write("" + answer);
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean bfs(int w) {
        Arrays.fill(visited, false);
        visited[origin] = true;
        queue.clear();
        queue.add(origin);
        while (!queue.isEmpty()) {
            int curr = queue.poll();

            if (curr == destination)
                return true;

            for (Node next: graph.get(curr)) {
                if (next.weight < w)
                    continue;
                if (visited[next.idx])
                    continue;
                visited[next.idx] = true;
                queue.add(next.idx);
            }
        }
        return false;
    }
}
