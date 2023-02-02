// https://www.acmicpc.net/problem/2517

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class Running {

    private static int N, S;
    private static int[] arr;
    private static int[] tree;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        // 리스트 크기 지정
        List<Pair> temp = new ArrayList<>(N);
        for (int i = 0; i < N; ++i) {
            temp.add(new Pair(i, Integer.parseInt(br.readLine())));
        }
        Collections.sort(temp, (o1, o2) -> o2.val - o1.val);

        arr = new int[N];
        for (int i = 0; i < temp.size(); ++i) {
            arr[temp.get(i).idx] = i;
        }

        S = 1;
        while (S < N) {
            S <<= 1;
        }
        tree = new int[S * 2];
        
        // 숫자가 클수록 능력이 낮음
        for (int ability: arr) {
            bw.write("" + (query(1, 0, S - 1, ability - 1) + 1));
            bw.newLine();
            update(1, 0, S - 1, ability);
        }

        bw.flush();
        bw.close();
        br.close();
    }

    // queryLeft = 0
    private int query(int node, int left, int right, int queryRight) {
        if (queryRight < left || right < 0) {
            return 0;
        }
        if (0 <= left && right <= queryRight) {
            return tree[node];
        }
        int mid = (left + right) / 2;
        return query(node * 2, left, mid, queryRight)
                + query(node * 2 + 1, mid + 1, right, queryRight);
    }

    private void update(int node, int left, int right, int target) {
        if (target < left || target > right) {
            return;
        }

        tree[node] += 1;
        if (left != right) {
            int mid = (left + right) / 2;
            update(node * 2, left, mid, target);
            update(node * 2 + 1, mid + 1, right, target);
        }
    }

    class Pair {
        int idx;
        int val;

        public Pair(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }

    public static void main(String[] args) throws Exception {
        new Running().solution();
    }
}
