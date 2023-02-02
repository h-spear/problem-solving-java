// https://www.acmicpc.net/problem/1202

package baekjoon.datastructure;

import java.io.*;
import java.util.*;

public class JewelThief {

    static class Jewel {
        int m, v;

        public Jewel(int m, int v) {
            this.m = m;
            this.v = v;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N, K, M, V;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        List<Jewel> jewels = new ArrayList<>();
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            V = Integer.parseInt(st.nextToken());
            jewels.add(new Jewel(M, V));
        }
        Collections.sort(jewels, (o1, o2) -> o2.m - o1.m);

        int[] bags = new int[K];
        for (int i = 0; i < K; ++i) {
            bags[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bags);

        long answer = 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int capacity: bags) {

            while (!jewels.isEmpty() && jewels.get(jewels.size() - 1).m <= capacity) {
                heap.add(jewels.remove(jewels.size() - 1).v);
            }

            if (!heap.isEmpty()) {
                answer += heap.remove();
            }
        }

        System.out.println(answer);
        br.close();
    }
}
