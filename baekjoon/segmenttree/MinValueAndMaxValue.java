// https://www.acmicpc.net/problem/2357

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class MinValueAndMaxValue {

    private static final int INF = Integer.MAX_VALUE;

    private int N, M, S;
    private int[] arr;
    private int[] minTree;
    private int[] maxTree;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];

        S = 1;
        while (S < N) {
            S <<= 1;
        }
        minTree = new int[S * 2];
        maxTree = new int[S * 2];

        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        init();

        int a, b;
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            bw.write(minQuery(1, 0, S - 1, a - 1, b - 1) + " " +
                    maxQuery(1, 0, S - 1, a - 1, b - 1));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private int minQuery(int node, int left, int right, int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft) {
            return INF;
        }
        if (queryLeft <= left && right <= queryRight) {
            return minTree[node];
        }
        int mid = (left + right) / 2;
        return Math.min(minQuery(node * 2, left, mid, queryLeft, queryRight),
                minQuery(node * 2 + 1, mid + 1, right, queryLeft, queryRight));
    }

    private int maxQuery(int node, int left, int right, int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft) {
            return -INF;
        }
        if (queryLeft <= left && right <= queryRight) {
            return maxTree[node];
        }
        int mid = (left + right) / 2;
        return Math.max(maxQuery(node * 2, left, mid, queryLeft, queryRight),
                maxQuery(node * 2 + 1, mid + 1, right, queryLeft, queryRight));
    }

    private void init() {
        for (int i = 0; i < N; ++i) {
            minTree[S + i] = arr[i];
            maxTree[S + i] = arr[i];
        }

        for (int i = S - 1; i > 0; --i) {
            minTree[i] = Math.min(minTree[i * 2], minTree[i * 2 + 1]);
            maxTree[i] = Math.max(maxTree[i * 2], maxTree[i * 2 + 1]);
        }
    }

    public static void main(String[] args) throws Exception {
        new MinValueAndMaxValue().solution();
    }
}
