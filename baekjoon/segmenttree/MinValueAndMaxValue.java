// https://www.acmicpc.net/problem/2357

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class MinValueAndMaxValue {
    private int[] minSegment;
    private int[] maxSegment;

    private void init(int[] arr, int node, int start, int end) {
        if (start == end) {
            minSegment[node] = arr[start];
            maxSegment[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            init(arr, 2 * node, start, mid);
            init(arr, 2 * node + 1, mid + 1, end);
            minSegment[node] = Math.min(minSegment[2 * node], minSegment[2 * node + 1]);
            maxSegment[node] = Math.max(maxSegment[2 * node], maxSegment[2 * node + 1]);
        }
    }

    private int minQuery(int node, int start, int end, int left, int right) {
        if (left > end || right < start)
            return Integer.MAX_VALUE;

        if (left <= start && end <= right)
            return minSegment[node];

        int mid = (start + end) / 2;
        return Math.min(minQuery(2 * node, start, mid, left, right),
                minQuery(2 * node + 1, mid + 1, end, left, right));
    }

    private int maxQuery(int node, int start, int end, int left, int right) {
        if (left > end || right < start)
            return Integer.MIN_VALUE;

        if (left <= start && end <= right)
            return maxSegment[node];

        int mid = (start + end) / 2;
        return Math.max(maxQuery(2 * node, start, mid, left, right),
                maxQuery(2 * node + 1, mid + 1, end, left, right));
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        minSegment = new int[n * 4];
        maxSegment = new int[n * 4];
        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        init(arr, 1, 0, n - 1);
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            bw.write(minQuery(1, 0, n - 1, a - 1, b - 1) +
                    " " + maxQuery(1 , 0, n - 1, a - 1, b - 1));
            bw.newLine();
            bw.flush();
        }
        br.close();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        new MinValueAndMaxValue().solution();
    }
}
