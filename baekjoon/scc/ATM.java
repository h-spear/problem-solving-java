// https://www.acmicpc.net/problem/4013

package baekjoon.scc;

import java.io.*;
import java.util.*;

public class ATM {

    private static final Deque<Integer> stack = new ArrayDeque<>();
    private static int N;
    private static List<List<Integer>> graph;
    private static int id;
    private static int[] visited;
    private static boolean[] finished;
    private static List<List<Integer>> scc;
    private static int[] restaurant;
    private static int[] cash;

    public static void main(String[] args) throws Exception {
        int start = input();
        tarjan();
        System.out.println(solve(start));
    }

    private static int solve(int start) {
        int countOfComponents = scc.size();
        int[] sccCash = new int[countOfComponents];
        int[] sccId = new int[N + 1];

        for (int i = 0; i < countOfComponents; ++i) {
            List<Integer> component = scc.get(i);
            for (int node: component) {
                sccId[node] = i;
                sccCash[i] += cash[node];
            }
        }

        List<List<Integer>> dag = new ArrayList<>(countOfComponents);
        for (int i = 0; i < countOfComponents; ++i)
            dag.add(new ArrayList<>());

        for (int i = 1; i <= N; ++i) {
            for (int j: graph.get(i)) {
                if (sccId[i] != sccId[j])
                    dag.get(sccId[i]).add(sccId[j]);
            }
        }

        start = sccId[start];
        int[] result = new int[countOfComponents];
        result[start] = sccCash[start];
        Queue<Node> heap = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.cost, o1.cost));
        heap.add(new Node(start, sccCash[start]));

        while (!heap.isEmpty()) {
            Node currNode = heap.poll();

            for (int nextNode: dag.get(currNode.index)) {
                int cost = currNode.cost + sccCash[nextNode];

                if (result[nextNode] < cost) {
                    result[nextNode] = cost;
                    heap.add(new Node(nextNode, cost));
                }
            }
        }

        int answer = 0;
        for (int r: restaurant) {
            answer = Math.max(answer, result[sccId[r]]);
        }
        return answer;
    }

    private static int input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        id = 0;
        finished = new boolean[N + 1];
        visited = new int[N + 1];
        cash = new int[N + 1];
        scc = new ArrayList<>();
        graph = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; ++i)
            graph.add(new ArrayList<>());

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
        }
        for (int i = 1; i <= N; ++i) {
            cash[i] = Integer.parseInt(br.readLine());
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        restaurant = new int[P];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < P; ++i) {
            restaurant[i] = Integer.parseInt(st.nextToken());
        }
        br.close();
        return S;
    }

    private static void tarjan() {
        for (int i = 1; i <= N; ++i) {
            if (!finished[i])
                dfs(i);
        }
    }

    private static int dfs(int x) {
        int parent = visited[x] = ++id;
        stack.push(x);

        for (int next: graph.get(x)) {
            if (visited[next] == 0)
                parent = Math.min(parent, dfs(next));
            else if (!finished[next])
                parent = Math.min(parent, visited[next]);
        }

        if (parent == visited[x]) {
            List<Integer> component = new ArrayList<>();
            while (!stack.isEmpty()) {
                int node = stack.pop();
                component.add(node);
                finished[node] = true;
                if (node == x)
                    break;
            }
            scc.add(component);
        }
        return parent;
    }

    private static class Node {
        int index, cost;

        Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }
    }
}
