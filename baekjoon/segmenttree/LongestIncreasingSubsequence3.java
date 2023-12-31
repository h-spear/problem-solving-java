// https://www.acmicpc.net/problem/12738

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class LongestIncreasingSubsequence3 {

    private static int N, S;
    private static int[] A;
    private static int[] tree;
    private static Map<Integer, Integer> map = new HashMap<>();

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

        int[] keys = map.keySet().stream().sorted().mapToInt(Integer::intValue).toArray();
        int idx = 0;
        for (int key: keys) {
            map.put(key, idx++);
        }

        for (int i = 0; i < N; ++i) {
            A[i] = map.get(A[i]);
        }

        S = 1;
        while (S <= 1000000) {
            S <<= 1;
        }
        tree = new int[S * 2];

        int answer = 0;
        for (int i = 0; i < N; ++i) {
            int max = query(1, 0, S - 1, 0, A[i] - 1) + 1;
            answer = Math.max(answer, max);
            update(1, 0, S - 1, A[i], max);
        }
        bw.write("" + answer);

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
            int mid = (left + right) >> 1;
            update(node * 2, left, mid, target, value);
            update(node * 2 + 1, mid + 1, right, target, value);
            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }
    }

    private int query(int node, int left, int right, int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft) {
            return 0;
        }
        if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }
        int mid = (left + right) >> 1;
        return Math.max(query(node * 2, left, mid, queryLeft, queryRight),
                query(node * 2 + 1, mid + 1, right, queryLeft, queryRight));
    }

    public static void main(String[] args) throws Exception {
        new LongestIncreasingSubsequence3().solution();
    }
}
