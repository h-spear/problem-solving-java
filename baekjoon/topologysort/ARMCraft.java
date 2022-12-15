// https://www.acmicpc.net/problem/1005

package baekjoon.topologysort;

import java.io.*;
import java.util.*;

public class ARMCraft {

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; ++tc) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] d = new int[n + 1];
            int[] inDegree = new int[n + 1];
            List<List<Integer>> graph = new ArrayList<>();

            for (int i = 0; i <= n; ++i) {
                graph.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; ++i) {
                d[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < k; ++i) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                graph.get(x).add(y);
                inDegree[y]++;
            }

            int w = Integer.parseInt(br.readLine());

            // topology sort
            int[] result = new int[n + 1];
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 1; i <= n; ++i) {
                if (inDegree[i] == 0) {
                    queue.add(i);
                    result[i] = d[i];
                }
            }

            for (int i = 0; i < n; ++i) {
                if (queue.isEmpty()) {
                    throw new Exception();
                }

                int node = queue.remove();
                for (int next : graph.get(node)) {
                    inDegree[next] -= 1;
                    result[next] = Math.max(result[next], result[node] + d[next]);
                    if (inDegree[next] == 0) {
                        queue.add(next);
                    }
                }
            }
            System.out.println(result[w]);
        }
        br.close();;
    }

    public static void main(String[] args) throws Exception {
        new ARMCraft().solution();
    }
}
