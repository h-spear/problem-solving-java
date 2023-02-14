// https://www.acmicpc.net/problem/1572

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class Median {

    private static final int S = 65536;
    private static int N, K;
    private static int[] input;
    private static int[] arr = new int[S + 1];
    private static int[] tree = new int[S * 2];


    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        input = new int[N];

        for (int i = 0; i < N; ++i) {
            input[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < K - 1; ++i) {
            arr[input[i]]++;
        }

        init();

        int m = (K + 1) / 2;
        long answer = 0;
        for (int i = K - 1; i < N; ++i) {
            update(1, 0, S - 1, input[i], 1);
            answer += query(1, 0, S - 1, m);
            update(1, 0, S - 1, input[i - K + 1], -1);
        }
        bw.write(answer + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    private long query(int node, int left, int right, int prio) {
        if (left == right) {
            return left;
        } else {
            int mid = (left + right) / 2;
            if (tree[node * 2] >= prio) {
                return query(node * 2, left, mid, prio);
            } else {
                return query(node * 2 + 1, mid + 1, right, prio - tree[node * 2]);
            }
        }
    }

    private void update(int node, int left, int right, int target, int diff) {
        if (target < left || right < target) {
            return;
        }
        tree[node] += diff;
        if (left != right) {
            int mid = (left + right) / 2;
            update(node * 2, left, mid, target, diff);
            update(node * 2 + 1, mid + 1, right, target, diff);
        }
    }

    private void init() {
        for (int i = 0; i < S; ++i) {
            tree[S + i] = arr[i];
        }
        for (int i = S - 1; i > 0; --i) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    public static void main(String[] args) throws Exception {
        new Median().solution();
    }
}
