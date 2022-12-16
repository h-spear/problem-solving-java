// https://www.acmicpc.net/problem/2042

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class SubSum {
    private long[] tree;

    private void init(long[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            init(arr, 2 * node, start, mid);
            init(arr, 2 * node + 1, mid + 1, end);
            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }
    }

    private long query(int node, int start, int end, int left, int right) {
        if (end < left || right < start)
            return 0;

        if (left <= start && end <= right)
            return tree[node];

        int mid = (start + end) / 2;
        return query(2 * node, start, mid, left, right)
                + query(2 * node + 1, mid + 1, end, left, right);
    }

    private void update(int node, int start, int end, int index, long diff) {
        if (index < start || index > end)
            return;

        if (start == end) {
            tree[node] = diff;
            return;
        }

        int mid = (start + end) / 2;
        update(2 * node, start, mid, index, diff);
        update(2 * node + 1, mid + 1, end, index, diff);
        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        tree = new long[4 * n];
        long[] arr = new long[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = Long.parseLong(br.readLine());
        }
        init(arr, 1, 0, n - 1);
        for (int i = 0; i < m + k; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if (a == 1) {
                update(1, 0, n - 1, b - 1, c);
            } else {
                bw.write("" + query(1, 0, n - 1, b - 1, (int)(c - 1)));
                bw.newLine();
            }
        }
        br.close();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        new SubSum().solution();
    }
}
