// https://www.acmicpc.net/problem/2252

package baekjoon.topologysort;

import java.io.*;
import java.util.*;

public class LineUp {

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] inDegree = new int[n + 1];
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= n; ++i) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            inDegree[b]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= n; ++i) {
            if (inDegree[i] == 0)
                queue.add(i);
        }

        for (int i = 0; i < n; ++i) {
            if (queue.isEmpty())
                throw new Exception();

            int now = queue.poll();
            bw.write(now + " ");

            for (int next: graph.get(now)) {
                inDegree[next] -= 1;
                if (inDegree[next] == 0)
                    queue.add(next);
            }
        }
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        new LineUp().solution();
    }
}
