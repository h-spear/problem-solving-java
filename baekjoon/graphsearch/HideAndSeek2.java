// https://www.acmicpc.net/problem/12851

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class HideAndSeek2 {

    private static final int N = 200001;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        int[] answer = {0, 0};
        int[] visited = new int[N];
        boolean find = false;
        while (!queue.isEmpty()) {
            int queueSize = queue.size();

            for (int i = 0; i < queueSize; ++i) {
                int x = queue.poll();

                visited[x] = 1;
                if (x == k) {
                    ++answer[1];
                    find = true;
                }

                if (x + 1 < N && visited[x + 1] == 0) {
                    queue.add(x + 1);
                }
                if (x - 1 >= 0 && visited[x - 1] == 0) {
                    queue.add(x - 1);
                }
                if (2 * x < N && visited[2 * x] == 0) {
                    queue.add(2 * x);
                }
            }
            if (find)
                break;
            ++answer[0];
        }
        System.out.println(answer[0]);
        System.out.println(answer[1]);
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new HideAndSeek2().solution();
    }
}
