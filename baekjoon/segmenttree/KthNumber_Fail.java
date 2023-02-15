// https://www.acmicpc.net/problem/7469
// 시간 초과로 실패한 코드

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class KthNumber_Fail {

    private static int N, S;
    private static int[] arr;
    private static int[][] tree;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int M;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        S = 1;
        while (S < N) {
            S <<= 1;
        }
        tree = new int[S * 2][];
        initialize();

        int i, j, k;
        for (int q = 0; q < M; ++q) {
            st = new StringTokenizer(br.readLine());
            i = Integer.parseInt(st.nextToken());
            j = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            bw.write(Q(i - 1, j - 1, k) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private int Q(int i, int j, int k) {
        return query(1, 0, S - 1, i, j)[k - 1];
    }

    private int[] query(int node, int left, int right, int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft) {
            return new int[]{};
        }
        if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }
        int mid = (left + right) / 2;
        return merge(query(node * 2, left, mid, queryLeft, queryRight),
                query(node * 2 + 1, mid + 1, right, queryLeft, queryRight));
    }

    private void initialize() {
        for (int i = 0; i < N; ++i) {
            tree[S + i] = new int[]{arr[i]};
        }
        for (int i = N; i < S; ++i){
            tree[S + i] = new int[]{ };
        }
        for (int i = S - 1; i > 0; --i) {
            tree[i] = merge(tree[i * 2], tree[i * 2 + 1]);
        }
    }

    private int[] merge(int[] A, int[] B) {
        int[] res = new int[A.length + B.length];
        int pt1 = 0;
        int pt2 = 0;
        int idx = 0;
        while (pt1 < A.length && pt2 < B.length) {
            if (A[pt1] < B[pt2]) {
                res[idx++] = A[pt1++];
            } else {
                res[idx++] = B[pt2++];
            }
        }
        while (pt1 < A.length) {
            res[idx++] = A[pt1++];
        }
        while (pt2 < B.length) {
            res[idx++] = B[pt2++];
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        new KthNumber_Fail().solution();
    }
}
