// https://www.acmicpc.net/problem/1697

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class HideAndSeek {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] visited = new int[100001];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);
        visited[N] = 1;
        while (!queue.isEmpty()) {
            int now = queue.poll();

            if (now == K) {
                break;
            }

            int[] candidate = {now - 1, now + 1, now * 2};
            for (int next: candidate) {
                if (0 <= next && next <= 100000 && visited[next] == 0) {
                    queue.add(next);
                    visited[next] = visited[now] + 1;
                }
            }
        }
        bw.write("" + (visited[K] - 1));
        bw.flush();
        bw.close();
        br.close();
    }
}
