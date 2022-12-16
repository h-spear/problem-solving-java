// https://www.acmicpc.net/problem/10868

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class MinValue {
    private int[] tree;

    private void init(int[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            init(arr, 2 * node, start, mid);
            init(arr, 2 * node + 1, mid + 1, end);
            tree[node] = Math.min(tree[2 * node], tree[2 * node + 1]);
        }
    }

    private int query(int node, int start, int end, int left, int right) {
        if (left > end || right < start)
            return Integer.MAX_VALUE;

        if (left <= start && end <= right)
            return tree[node];

        int mid = (start + end) / 2;
        return Math.min(query(2 * node, start, mid, left, right),
                query(2 * node + 1, mid + 1, end, left, right));
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        tree = new int[n * 4];
        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        init(arr, 1, 0, n - 1);
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            bw.write("" + query(1, 0, n - 1, a - 1, b - 1));
            bw.newLine();
            bw.flush();
        }
        br.close();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        new MinValue().solution();
    }
}
