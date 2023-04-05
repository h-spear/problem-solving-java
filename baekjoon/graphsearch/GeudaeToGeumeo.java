// https://www.acmicpc.net/problem/14496

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class GeudaeToGeumeo {

    private static int N, M;
    private static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int a, b, answer = -1;

        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; ++i) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            graph.get(c).add(d);
            graph.get(d).add(c);
        }

        // bfs
        int[] visited = new int[N + 1];
        visited[a] = 1;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(a);

        while (queue.size() > 0) {
            int now = queue.poll();

            if (now == b) {
                answer = visited[now] - 1;
                break;
            }

            for (int next: graph.get(now)) {
                if (visited[next] != 0) {
                    continue;
                }
                visited[next] = visited[now] + 1;
                queue.add(next);
            }
        }
        bw.write("" + answer);
        bw.flush();
        bw.close();
        br.close();
    }
}
