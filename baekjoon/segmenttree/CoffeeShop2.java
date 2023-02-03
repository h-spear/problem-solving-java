// https://www.acmicpc.net/problem/1275

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class CoffeeShop2 {

    private static int N, Q, S;
    private static long[] arr;
    private static long[] tree;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        arr = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        S = 1;
        while (S < N) {
            S <<= 1;
        }
        tree = new long[S * 2];

        init();

        int _x, _y, x, y, a, b;
        for (int i = 0; i < Q; ++i) {
            st = new StringTokenizer(br.readLine());

            _x = Integer.parseInt(st.nextToken());
            _y = Integer.parseInt(st.nextToken());
            x = Math.min(_x, _y);
            y = Math.max(_x, _y);
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            bw.write("" + query(1, 0, S - 1, x - 1, y - 1));
            bw.newLine();
            update(1, 0, S - 1, a - 1, b - tree[S + a - 1]);
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private void init() {
        for (int i = 0; i < N; ++i) {
            tree[S + i] = arr[i];
        }

        for (int i = S - 1; i > 0; --i) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    private long query(int node, int left, int right, int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft) {
            return 0;
        }
        if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }
        int mid = (left + right) / 2;
        return query(node * 2, left, mid, queryLeft, queryRight) +
                query(node * 2 + 1, mid + 1, right, queryLeft, queryRight);
    }

    private void update(int node, int left, int right, int target, long diff) {
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

    public static void main(String[] args) throws Exception {
        new CoffeeShop2().solution();
    }
}
