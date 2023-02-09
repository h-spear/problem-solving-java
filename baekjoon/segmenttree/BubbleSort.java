// https://www.acmicpc.net/problem/1517

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class BubbleSort {

    private int N, S;
    private int A[];
    private int tree[];
    private Map<Integer, Integer> map = new HashMap<>();

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            A[i] = Integer.parseInt(st.nextToken());
            map.put(A[i], 0);
        }

        // compress
        int[] keys = map.keySet().stream().sorted().mapToInt(Integer::intValue).toArray();
        int idx = 0;
        for (int key: keys) {
            map.put(key, idx++);
        }

        S = 1;
        while (S < N) {
            S <<= 1;
        }
        tree = new int[S * 2];

        long answer = 0;
        for (int i = N - 1; i >= 0; --i) {
            update(1, 0, S - 1, map.get(A[i]));
            answer += query(1, 0, S - 1, 0, map.get(A[i]) - 1);
        }
        bw.write(answer + "\n");

        bw.flush();
        bw.close();
        br.close();
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

    private void update(int node, int left, int right, int target) {
        if (target < left || right < target) {
            return;
        }
        tree[node] += 1;
        if (left != right) {
            int mid = (left + right) / 2;
            update(node * 2, left, mid, target);
            update(node * 2 + 1, mid + 1, right, target);
        }
    }

    public static void main(String[] args) throws Exception {
        new BubbleSort().solution();
    }
}
