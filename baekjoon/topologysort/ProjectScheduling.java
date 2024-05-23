// https://www.acmicpc.net/problem/14907

package baekjoon.topologysort;

import java.io.*;
import java.util.*;

public class ProjectScheduling {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[] inDegree = new int[26];
        int[] day = new int[26];
        List<List<Integer>> graph = new ArrayList<>(26);
        for (int i = 0; i < 26; ++i) {
            graph.add(new ArrayList<>());
        }

        while (true) {
            try {
                st = new StringTokenizer(br.readLine());
                int x = st.nextToken().charAt(0) - 'A';
                day[x] = Integer.parseInt(st.nextToken());
                if (st.hasMoreTokens()) {
                    char[] prev = st.nextToken().toCharArray();
                    inDegree[x] += prev.length;
                    for (char c: prev) {
                        graph.get(c - 'A').add(x);
                    }
                }
            } catch (Exception e) {
                break;
            }
        }

        // topology sort
        int[] completeTime = new int[26];
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < 26; ++i) {
            if (inDegree[i] == 0) {
                queue.add(i);
                completeTime[i] = day[i];
            }
        }

        for (int i = 0; i < 26; ++i) {
            assert !queue.isEmpty();
            int now = queue.poll();

            for (int next: graph.get(now)) {
                completeTime[next] = Math.max(completeTime[next], completeTime[now] + day[next]);
                if (--inDegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        int answer = 0;
        for (int t: completeTime) {
            answer = Math.max(answer, t);
        }
        System.out.println(answer);
        br.close();
    }
}
