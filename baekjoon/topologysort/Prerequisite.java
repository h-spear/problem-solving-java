// https://www.acmicpc.net/problem/14567

package baekjoon.topologysort;

import java.io.*;
import java.util.*;

public class Prerequisite {

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= n; ++i) {
            graph.add(new ArrayList<>());
        }

        int[] inDegree = new int[n + 1];
        int[] time = new int[n + 1];

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            ++inDegree[b];
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; ++i) {
            if (inDegree[i] == 0) {
                queue.add(i);
                time[i] = 1;
            }
        }

        for (int i = 0; i < n; ++i) {
            int now = queue.poll();

            for (int next: graph.get(now)) {
                --inDegree[next];
                time[next] = Math.max(time[next], time[now] + 1);

                if (inDegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        for (int i = 1; i <= n; ++i) {
            bw.write(time[i] + " ");
        }
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new Prerequisite().solution();
    }
}
