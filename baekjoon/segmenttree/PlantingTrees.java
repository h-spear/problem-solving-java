// https://www.acmicpc.net/problem/1280

package baekjoon.segmenttree;

import java.io.*;

public class PlantingTrees {

    private static final long P = 1000000007;
    private static final int K = 200010;
    private static int S;
    private static long[] countTree;
    private static long[] sumTree;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N, x;

        S = 1;
        while (S <= K) {
            S <<= 1;
        }
        countTree = new long[S * 2];
        sumTree = new long[S * 2];

        N = Integer.parseInt(br.readLine());

        long answer = 1;
        for (int i = 0; i < N; ++i) {
            x = Integer.parseInt(br.readLine());
            long leftCount = query(countTree, 1, 0, S - 1, 0, x - 1);
            long leftSum = query(sumTree, 1, 0, S - 1, 0, x - 1);

            long rightCount = query(countTree, 1, 0, S - 1, x + 1, K);
            long rightSum = query(sumTree, 1, 0, S - 1, x + 1, K);

            long temp = x * leftCount - leftSum;
            temp += rightSum - x * rightCount;
            temp %= P;
            if (i != 0) {
                answer = (answer * temp) % P;
            }
            update(countTree, 1, 0, S - 1, x, 1);
            update(sumTree, 1, 0, S - 1, x, x);
        }
        bw.write(answer % P + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private long query(long[] tree, int node, int left, int right, int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft) {
            return 0;
        }
        if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }
        int mid = (left + right) / 2;
        return query(tree, node * 2, left, mid, queryLeft, queryRight) +
                query(tree, node * 2 + 1, mid + 1, right, queryLeft, queryRight);
    }

    private void update(long[] tree, int node, int left, int right, int target, int diff) {
        if (target < left || right < target) {
            return;
        }
        tree[node] += diff;
        if (left != right) {
            int mid = (left + right) / 2;
            update(tree, node * 2, left, mid, target, diff);
            update(tree, node * 2 + 1, mid + 1, right, target, diff);
        }
    }

    public static void main(String[] args) throws Exception {
        new PlantingTrees().solution();
    }
}
