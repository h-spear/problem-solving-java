// https://www.acmicpc.net/problem/2056

package baekjoon.topologysort;

import java.io.*;
import java.util.*;

public class Work {

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] workingTime = new int[n + 1];
        int[] inDegree = new int[n + 1];
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= n; ++i) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= n; ++i) {
            st = new StringTokenizer(br.readLine());
            workingTime[i] = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            inDegree[i] += t;
            for (int j = 0; j < t; ++j) {
                int pre = Integer.parseInt(st.nextToken());
                graph.get(pre).add(i);
            }
        }

        // topology sort
        int[] completeTime = new int[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; ++i) {
            if (inDegree[i] == 0) {
                queue.add(i);
                completeTime[i] = workingTime[i];
            }
        }

        for (int i = 0; i < n; ++i) {
            int now = queue.poll();

            for (int next: graph.get(now)) {
                completeTime[next] = Math.max(completeTime[next], completeTime[now] + workingTime[next]);
                --inDegree[next];
                if (inDegree[next] == 0)
                    queue.add(next);
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; ++i) {
            answer = Math.max(answer, completeTime[i]);
        }
        System.out.println(answer);
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new Work().solution();
    }
}
