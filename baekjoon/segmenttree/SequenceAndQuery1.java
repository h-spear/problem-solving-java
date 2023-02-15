// https://www.acmicpc.net/problem/13537
// merge sort tree

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class SequenceAndQuery1 {

    private static int N, S;
    private static int[] arr;
    private static List<Integer>[] tree;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        S = 1;
        while (S < N) {
            S <<= 1;
        }
        tree = new List[S * 2];

        initialize();

        int M = Integer.parseInt(br.readLine());

        int i, j, k;
        for (int q = 0; q < M; ++q) {
            st = new StringTokenizer(br.readLine());
            i = Integer.parseInt(st.nextToken());
            j = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            bw.write(query(1, 0, S - 1, i - 1, j - 1, k) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private int query(int node, int left, int right, int queryLeft, int queryRight, int value) {
        if (queryRight < left || right < queryLeft) {
            return 0;
        }
        if (queryLeft <= left && right <= queryRight) {
            return tree[node].size() - upperBound(tree[node], value);
        }
        int mid = (left + right) / 2;
        return query(node * 2, left, mid, queryLeft, queryRight, value) +
                query(node * 2 + 1, mid + 1, right, queryLeft, queryRight, value);
    }

    private int upperBound(List<Integer> list, int value) {
        int l = 0;
        int r = list.size() - 1;
        int mid, res = 0;

        while (l <= r) {
            mid = (l + r) / 2;
            if (list.get(mid) > value) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private void initialize() {
        for (int i = 0; i < S * 2; ++i) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; ++i) {
            update(1, 0, S - 1, i, arr[i]);
        }
        for (int i = 0; i < S * 2; ++i) {
            Collections.sort(tree[i]);
        }
    }

    private void update(int node, int left, int right, int target, int value) {
        if (target < left || right < target) {
            return;
        }
        tree[node].add(value);
        if (left != right) {
            int mid = (left + right) / 2;
            update(node * 2, left, mid, target, value);
            update(node * 2 + 1, mid + 1, right, target, value);
        }
    }

    public static void main(String[] args) throws Exception {
        new SequenceAndQuery1().solution();
    }
}
