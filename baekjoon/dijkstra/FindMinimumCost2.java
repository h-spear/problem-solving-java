// https://www.acmicpc.net/problem/11779

package baekjoon.dijkstra;

import java.io.*;
import java.util.*;

public class FindMinimumCost2 {

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

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        List<List<Node>> graph = new ArrayList<>();

        for (int i = 0; i <= n; ++i)
            graph.add(new ArrayList<>());

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, c));
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        int[] distance = new int[n + 1];
        int[] prev = new int[n + 1];
        for (int i = 1; i <= n; ++i)
            distance[i] = Integer.MAX_VALUE;

        PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        heap.add(new Node(s, 0));
        while (!heap.isEmpty()) {
            Node currNode = heap.remove();

            if (currNode.cost > distance[currNode.idx])
                continue;

            for (Node nextNode: graph.get(currNode.idx)) {
                int cost = currNode.cost + nextNode.cost;
                if (distance[nextNode.idx] > cost) {
                    distance[nextNode.idx] = cost;
                    heap.add(new Node(nextNode.idx, cost));
                    prev[nextNode.idx] = currNode.idx;
                }
            }
        }

        Stack<Integer> stack = new Stack<>();
        int now = e;
        stack.add(e);
        while (now != s) {
            now = prev[now];
            stack.add(now);
        }

        bw.write("" + distance[e]);
        bw.newLine();

        bw.write("" + stack.size());
        bw.newLine();

        while (!stack.isEmpty()) {
            bw.write(stack.pop() + " ");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new FindMinimumCost2().solution();
    }
}
