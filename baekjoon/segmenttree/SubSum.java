// https://www.acmicpc.net/problem/2042

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class SubSum {

    private static int N, M, K, S;
    private static long[] arr;
    private static long[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        S = 1;

        arr = new long[N];

        while (S < N) {
            S <<= 1;
        }
        tree = new long[S * 2];

        for (int i = 0; i < N; ++i) {
            arr[i] = Long.parseLong(br.readLine());
        }

        init();

        for (int i = 0; i < M + K; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                update(1, 0, S - 1, b - 1, c - tree[S + b - 1]);
            } else {
                bw.write("" + query(1, 0, S - 1, b - 1, (int)c - 1));
                bw.newLine();
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }


    // bottom up
    private static void init() {
        // leaf
        for (int i = 0; i < N; ++i) {
            tree[S + i] = arr[i];
        }

        // inner
        for (int i = S - 1; i > 0; --i) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    private static long query(int node, int left, int right, int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft) {
            return 0;
        }

        else if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }

        else {
            int mid = (left + right) / 2;
            return query(node * 2, left, mid, queryLeft, queryRight) +
                    query(node * 2 + 1, mid + 1, right, queryLeft, queryRight);
        }
    }

    private static void update(int node, int left, int right, int target, long diff) {
        if (target < left || right < target) {
            return;
        }

        tree[node] += diff;

        // 리프 노드가 아니라면 update 전파
        if (left != right) {
            int mid = (left + right) / 2;
            update(node * 2, left, mid, target, diff);
            update(node * 2 + 1, mid + 1, right, target, diff);
        }
    }

}
