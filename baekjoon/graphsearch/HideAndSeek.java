// https://www.acmicpc.net/problem/1697

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class HideAndSeek {

    private static final int N = 200001;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        int[] visited = new int[N];
        while (!queue.isEmpty()) {
            int x = queue.poll();

            if (x == k)
                break;

            if (x + 1 < N && visited[x + 1] == 0) {
                visited[x + 1] = visited[x] + 1;
                queue.add(x + 1);
            }
            if (x - 1 >= 0 && visited[x - 1] == 0) {
                visited[x - 1] = visited[x] + 1;
                queue.add(x - 1);
            }
            if (2 * x < N && visited[2 * x] == 0) {
                visited[2 * x] = visited[x] + 1;
                queue.add(2 * x);
            }
        }
        System.out.println(visited[k]);
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new HideAndSeek().solution();
    }
}
