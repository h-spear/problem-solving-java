// https://www.acmicpc.net/problem/13913

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class HideAndSeek4 {

    private static final int N = 200001;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        int[] visited = new int[N];
        for (int i = 0; i < N; ++i)
            visited[i] = -1;

        int time = 0;
        visited[n] = 0;
        boolean find = false;
        while (!queue.isEmpty()) {

            int queueSize = queue.size();

            for (int i = 0; i < queueSize; ++i) {
                int x = queue.poll();

                if (x == k)
                    find = true;

                if (x + 1 < N && visited[x + 1] == -1) {
                    queue.add(x + 1);
                    visited[x + 1] = x;
                }
                if (x - 1 >= 0 && visited[x - 1] == -1) {
                    queue.add(x - 1);
                    visited[x - 1] = x;
                }
                if (2 * x < N && visited[2 * x] == -1) {
                    queue.add(2 * x);
                    visited[2 * x] = x;
                }
            }
            if (find)
                break;
            ++time;
        }
        int[] path = new int[time + 1];
        int j = k;
        for (int i = 0; i <= time; ++i) {
            path[i] = j;
            j = visited[j];
        }

        bw.write("" + time);
        bw.newLine();
        for (int i = 0; i <= time; ++i) {
            bw.write(path[time - i] + " ");
        }
        bw.flush();
        br.close();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        new HideAndSeek4().solution();
    }
}
