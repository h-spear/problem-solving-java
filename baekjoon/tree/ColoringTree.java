// https://www.acmicpc.net/problem/24230

package baekjoon.tree;

import java.io.*;
import java.util.*;

public class ColoringTree {

    private static int N, answer = 0;
    private static int[] C;
    private static List<List<Integer>> graph;
    private static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        C = new int[N + 1];
        visited = new boolean[N + 1];
        graph = new ArrayList<>(N + 1);

        for (int i = 0; i <= N; ++i)
            graph.add(new ArrayList<>());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; ++i) {
            C[i] = Integer.parseInt(st.nextToken());
        }

        int a, b;
        for (int i = 0; i < N - 1; ++i) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        if (C[1] != 0)  // 1번 노드가 흰색이 아닌 경우 주의해야 함.
            ++answer;
        dfs(1);
        bw.write("" + answer);
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int curr) {
        visited[curr] = true;
        for (int next: graph.get(curr)) {
            if (visited[next])
                continue;
            if (C[next] != C[curr])
                ++answer;
            dfs(next);
        }
    }
}

class ColoringTree_MySolution {

    private static class Pair {
        int num, depth;

        public Pair(int num, int depth) {
            this.num = num;
            this.depth = depth;
        }
    }

    private static int N;
    private static int[] C;
    private static int[] parent;
    private static int[] depth;
    private static int[] color;
    private static List<List<Integer>> graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        C = new int[N + 1];
        graph = new ArrayList<>(N + 1);
        depth = new int[N + 1];
        parent = new int[N + 1];
        color = new int[N + 1];

        for (int i = 0; i <= N; ++i)
            graph.add(new ArrayList<>());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; ++i) {
            C[i] = Integer.parseInt(st.nextToken());
        }

        int a, b;
        for (int i = 0; i < N - 1; ++i) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        bfs(1, 1);

        Pair[] pairs = new Pair[N];
        for (int i = 0; i < N; ++i)
            pairs[i] = new Pair(i + 1, depth[i + 1]);
        Arrays.sort(pairs, Comparator.comparingInt(o -> o.depth));

        int num, answer = 0;
        for (Pair pair: pairs) {
            num = pair.num;
            if (color[num] != C[num]) {
                if (color[parent[num]] != C[num])
                    ++answer;
            }
            color[num] = C[num];
        }
        bw.write("" + answer);
        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs(int curr, int dep) {
        Queue<Integer> queue = new ArrayDeque<>(10);
        queue.add(curr);
        depth[curr] = dep;
        while (!queue.isEmpty()) {
            curr = queue.poll();

            for (int next: graph.get(curr)) {
                if (depth[next] > 0)
                    continue;
                depth[next] = depth[curr] + 1;
                parent[next] = curr;
                queue.add(next);
            }
        }
    }
}
