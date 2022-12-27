// https://www.acmicpc.net/problem/1516

package baekjoon.topologysort;

import java.io.*;
import java.util.*;

public class GameDevelop {

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] time = new int[n + 1];
        int[] inDegree = new int[n + 1];
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= n; ++i) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= n; ++i) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                int prerequisite = Integer.parseInt(st.nextToken());
                if (prerequisite == -1)
                    break;
                inDegree[i]++;
                graph.get(prerequisite).add(i);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; ++i) {
            if (inDegree[i] == 0)
                queue.add(i);
        }

        int[] answer = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            int now = queue.poll();
            answer[now] += time[now];

            for (int next: graph.get(now)) {
                inDegree[next]--;
                answer[next] = Math.max(answer[next], answer[now]);
                if (inDegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        for (int i = 1; i <= n; ++i) {
            bw.write("" + answer[i]);
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new GameDevelop().solution();
    }
}
