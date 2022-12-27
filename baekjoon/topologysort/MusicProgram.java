// https://www.acmicpc.net/problem/2623

package baekjoon.topologysort;

import java.io.*;
import java.util.*;

public class MusicProgram {

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
            int cnt = Integer.parseInt(st.nextToken());
            int[] order = new int[cnt];
            for (int j = 0; j < cnt; ++j) {
                order[j] = Integer.parseInt(st.nextToken());
            }
            for (int j = 0; j < cnt - 1; ++j) {
                graph.get(order[j]).add(order[j + 1]);
                inDegree[order[j + 1]]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; ++i) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        boolean find = true;
        int[] answer = new int[n];
        for (int i = 0; i < n; ++i) {
            if (queue.isEmpty()) {
                find = false;
                break;
            }

            int now = queue.poll();
            answer[i] = now;
            for (int next: graph.get(now)) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        if (find) {
            for (int i = 0; i < n; ++i) {
                bw.write("" + answer[i]);
                bw.newLine();
            }
        } else {
            bw.write("0");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new MusicProgram().solution();
    }
}
