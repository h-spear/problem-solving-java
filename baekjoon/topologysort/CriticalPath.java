// https://www.acmicpc.net/problem/1948

package baekjoon.topologysort;

import java.io.*;
import java.util.*;

public class CriticalPath {

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
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[] inDegree = new int[n + 1];
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
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, c));
            reverseGraph.get(b).add(new Node(a, c));
            inDegree[b]++;
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());


        // topology sort
        Queue<Integer> queue = new LinkedList<>();
        int[] result = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            if (inDegree[i] == 0)
                queue.add(i);
        }

        for (int i = 1; i <= n; ++i) {
            if (queue.isEmpty())
                throw new Exception();

            int now = queue.poll();
            for (Node nextNode: graph.get(now)) {
                inDegree[nextNode.idx]--;
                result[nextNode.idx] = Math.max(result[nextNode.idx], result[now] + nextNode.cost);
                if (inDegree[nextNode.idx] == 0) {
                    queue.add(nextNode.idx);
                }
            }
        }

        // reverse topology
        int[] done = new int[n + 1];
        int count = 0;
        queue.clear();
        queue.add(e);

        // 역추적할 때에는 done 배열 사용
        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (Node nextNode: reverseGraph.get(now)) {
                if (result[now] - result[nextNode.idx] == nextNode.cost) {
                    count++;
                    if (done[nextNode.idx] == 0) {
                        done[nextNode.idx] = 1;
                        queue.add(nextNode.idx);
                    }
                }
            }
        }

        System.out.println(result[e]);
        System.out.println(count);
    }

    public static void main(String[] args) throws Exception {
        new CriticalPath().solution();
    }
}
