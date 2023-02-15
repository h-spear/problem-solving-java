// https://www.acmicpc.net/problem/3006

package baekjoon.segmenttree;

import java.io.*;

public class TurboSort {

    private static int N, S;
    private static int[] arr;
    private static int[] tree;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        for (int i = 0; i < N; ++i) {
            arr[Integer.parseInt(br.readLine()) - 1] = i;
        }

        S = 1;
        while (S < N) {
            S <<= 1;
        }
        tree = new int[S * 2];

        initialize();

        int low, high;
        low = 0;
        high = N - 1;
        for (int i = 1; i <= N; ++i) {
            // 홀수번째
            if ((i & 1) == 1) {
                bw.write(query(1, 0, S - 1, 0, arr[low] - 1) + "\n");
                update(1, 0, S - 1, arr[low], 0);
                low++;
            // 짝수번째
            } else {
                bw.write(query(1, 0, S - 1, arr[high] + 1, N - 1) + "\n");
                update(1, 0, S - 1, arr[high], 0);
                high--;
            }
        }
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

    private void initialize() {
        for (int i = 0; i < N; ++i) {
            tree[S + i] = 1;
        }
        for (int i = S - 1; i > 0; --i) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    public static void main(String[] args) throws Exception {
        new TurboSort().solution();
    }
}
