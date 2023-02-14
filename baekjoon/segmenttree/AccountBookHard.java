// https://www.acmicpc.net/problem/12837

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class AccountBookHard {

    private static int N, Q, S;
    private static long[] tree;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        S = 1;
        while (S < N) {
            S <<= 1;
        }
        tree = new long[S * 2];

        int c, p, q, x;
        for (int i = 0; i < Q; ++i) {
            st = new StringTokenizer(br.readLine());
            c = Integer.parseInt(st.nextToken());
            p = Integer.parseInt(st.nextToken());
            if (c == 1) {
                x = Integer.parseInt(st.nextToken());
                update(1, 0, S - 1, p - 1, x);
            } else {
                q = Integer.parseInt(st.nextToken());
                bw.write(query(1, 0, S - 1, p - 1, q - 1) + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
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

    public static void main(String[] args) throws Exception {
        new AccountBookHard().solution();
    }
}
