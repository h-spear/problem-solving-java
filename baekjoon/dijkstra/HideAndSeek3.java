// https://www.acmicpc.net/problem/13549

package baekjoon.dijkstra;

import java.io.*;
import java.util.*;

public class HideAndSeek3 {
    private static final int N = 100001;

    class Pair {
        int x;
        int t;

        public Pair(int x, int t) {
            this.x = x;
            this.t = t;
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] distance = new int[N];
        for (int i = 0; i < N; ++i)
            distance[i] = Integer.MAX_VALUE;

        distance[n] = 0;
        PriorityQueue<Pair> heap = new PriorityQueue<>((a, b) -> a.t - b.t);
        heap.add(new Pair(n, 0));
        while (!heap.isEmpty()) {
            Pair pair = heap.remove();
            int x = pair.x;
            int t = pair.t;
            if (x == k)
                break;

            if (x - 1 >= 0 && distance[x - 1] > t + 1) {
                distance[x - 1] = t + 1;
                heap.add(new Pair(x - 1, t + 1));
            }
            if (x + 1 < N && distance[x + 1] > t + 1) {
                distance[x + 1] = t + 1;
                heap.add(new Pair(x + 1, t + 1));
            }
            if (2 * x < N && distance[2 * x] > t) {
                distance[2 * x] = t;
                heap.add(new Pair(2 * x, t));
            }
        }
        System.out.println(distance[k]);
    }

    public static void main(String[] args) throws Exception {
        new HideAndSeek3().solution();
    }
}
