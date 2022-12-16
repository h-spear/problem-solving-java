// https://www.acmicpc.net/problem/11505
// 오버플로우 발생 -> long 배열 사용

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class SubMultiplication {
    private long[] tree;
    private static final long p = 1000000007L;

    private void init(int[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            init(arr, 2 * node, start, mid);
            init(arr, 2 * node + 1, mid + 1, end);
            tree[node] = (tree[2 * node] * tree[2 * node + 1]) % p;
        }
    }

    private void update(int node, int start, int end, int index, int value) {
        if (index > end || index < start)
            return;

        if (start == end) {
            tree[node] = value;
            return;
        }

        int mid = (start + end) / 2;
        update(2 * node, start, mid, index, value);
        update(2 * node + 1, mid + 1, end, index, value);
        tree[node] = (tree[2 * node] * tree[2 * node + 1]) % p;
    }

    private long query(int node, int start, int end, int left, int right) {
        if (left > end || right < start)
            return 1;

        if (left <= start && end <= right)
            return tree[node];

        int mid = (start + end) / 2;
        return (query(2 * node, start, mid, left, right) *
                query(2 * node + 1, mid + 1, end, left, right)) % p;
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        tree = new long[n * 4];
        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        init(arr, 1, 0, n - 1);
        for (int i = 0; i < m + k; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a == 1) {
                update(1, 0, n - 1, b - 1, c);
            } else {
                bw.write("" + query(1, 0, n - 1, b - 1, c - 1));
                bw.newLine();
                bw.flush();
            }
        }
        br.close();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        new SubMultiplication().solution();
    }
}
