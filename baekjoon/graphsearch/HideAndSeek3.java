// https://www.acmicpc.net/problem/13549
// 0-1 BFS

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class HideAndSeek3 {

    private static final int[] dt = {0, 1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] visited = new int[100001];
        Deque<Integer> deque = new LinkedList<>();
        deque.add(N);
        visited[N] = 1;

        while (!deque.isEmpty()) {
            int now = deque.poll();
            if (now == K) {
                break;
            }

            int[] candidate = {now * 2, now - 1, now + 1};  // 순서 중요.
            for (int i = 0; i < 3; ++i) {
                int next = candidate[i];
                if (0 <= next && next <= 100000 && visited[next] == 0) {
                    visited[next] = visited[now] + dt[i];
                    if (i == 0) {
                        deque.addFirst(next);
                    } else {
                        deque.addLast(next);
                    }
                }
            }
        }
        bw.write("" + (visited[K] - 1));
        bw.flush();
        bw.close();
        br.close();
    }
}