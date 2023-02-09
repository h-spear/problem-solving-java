// https://www.acmicpc.net/problem/7578

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class Factory {

    private int N, S;
    private int[] arr;
    private int[] tree;
    private Map<Integer, Integer> map = new HashMap<>();

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

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            map.put(Integer.parseInt(st.nextToken()), i);
        }

        S = 1;
        while (S < N) {
            S <<= 1;
        }
        tree = new int[S * 2];

        long answer = 0;
        for (int num: arr) {
            answer += query(1, 0, S - 1, map.get(num) + 1, N - 1);
            update(1, 0, S - 1, map.get(num));
        }
        bw.write(answer + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    private void update(int node, int left, int right, int target) {
        if (target < left || right < target) {
            return;
        }
        if (left == right) {
            tree[node] = 1;
        } else {
            int mid = (left + right) / 2;
            update(node * 2, left, mid, target);
            update(node * 2 + 1, mid + 1, right, target);
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }

    private long query(int node, int left, int right, int queryLeft, int queryRight) {
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
        new Factory().solution();
    }
}
