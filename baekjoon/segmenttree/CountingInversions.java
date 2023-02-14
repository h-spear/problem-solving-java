// https://www.acmicpc.net/problem/10090

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class CountingInversions {

    private static int N, S;
    private static int[] tree;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int num;

        N = Integer.parseInt(br.readLine());

        S = 1;
        while (S < N) {
            S <<= 1;
        }
        tree = new int[S * 2];

        long answer = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            num = Integer.parseInt(st.nextToken()) - 1;
            answer += query(1, 0, S - 1, num + 1, N - 1);
            update(1, 0, S - 1, num, 1);
        }
        bw.write(answer + "");

        bw.flush();
        bw.close();
        br.close();
    }

    private void update(int node, int left, int right, int target, int value) {
        if (target < left || right < target) {
            return;
        }
        if (left == right) {
            tree[node] = value;
        } else {
            int mid = (left + right) / 2;
            update(node * 2, left, mid, target, value);
            update(node * 2 + 1, mid + 1, right, target, value);
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }

    private int query(int node, int left, int right, int queryLeft, int queryRight) {
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

    public static void main(String[] args) throws Exception {
        new CountingInversions().solution();
    }
}
