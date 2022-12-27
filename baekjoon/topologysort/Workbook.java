// https://www.acmicpc.net/problem/1766

package baekjoon.topologysort;

import java.io.*;
import java.util.*;

public class Workbook {

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[n + 1];
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

        // topology sort
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 1; i <= n; ++i) {
            if (inDegree[i] == 0)
                heap.add(i);
        }

        while (!heap.isEmpty()) {
            int now = heap.remove();
            bw.write(now + " ");

            for (int next: graph.get(now)) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    heap.add(next);
                }
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new Workbook().solution();
    }
}
