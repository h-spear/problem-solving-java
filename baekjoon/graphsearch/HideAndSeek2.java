// https://www.acmicpc.net/problem/12851

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class HideAndSeek2 {

    public static void main (String[]args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        boolean[] visited = new boolean[100001];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);
        visited[N] = true;

        int[] ans = {0, 0};
        boolean flag = false;
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; ++i) {

                int now = queue.poll();
                visited[now] = true;

                if (now == K) {
                    ++ans[1];
                    flag = true;
                }

                int[] candidate = {now - 1, now + 1, now * 2};
                for (int next : candidate) {
                    if (0 <= next && next <= 100000 && visited[next] == false) {
                        queue.add(next);
                    }
                }
            }
            if (flag)
                break;
            ++ans[0];
        }
        bw.write(ans[0] + "\n" + ans[1]);
        bw.flush();
        bw.close();
        br.close();
    }
}
