// 1238

package swea.difficulty4;

import java.io.*;
import java.util.*;

public class Contact {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int from, to, answer;
        boolean[] visited = new boolean[101];
        int T = 10;
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; ++tc) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            List<List<Integer>> graph = new ArrayList<>(N + 1);
            for (int i = 0; i <= 100; ++i) {
                graph.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < (N >> 1); ++i) {
                from = Integer.parseInt(st.nextToken());
                to = Integer.parseInt(st.nextToken());
                graph.get(from).add(to);
            }

            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(start);
            Arrays.fill(visited, false);
            visited[start] = true;

            answer = 0;
            while (!queue.isEmpty()) {
                int queueSize = queue.size();
                int temp = 0;
                for (int i = 0; i < queueSize; ++i) {
                    int curr = queue.poll();
                    temp = Math.max(curr, temp);

                    for (int next: graph.get(curr)) {
                        if (visited[next])
                            continue;
                        visited[next] = true;
                        queue.add(next);
                    }
                }
                answer = temp;
            }
            sb.append("#").append(tc).append(" ")
                    .append(answer).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }
}
