// https://www.acmicpc.net/problem/16978
// Offline Query : 쿼리가 주어짐과 동시에 답을 도출하지 않고 나중에 답을 도출

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class SequenceAndQuery22 {

    private static int N, M, S;
    private static int[] A;
    private static long[] tree;
    private static List<SumQuery> sumQueries = new ArrayList<>(100001);
    private static List<UpdateQuery> updateQueries = new ArrayList<>(100001);

    class UpdateQuery {
        int i, v;

        public UpdateQuery(int i, int v) {
            this.i = i;
            this.v = v;
        }
    }

    class SumQuery implements Comparable<SumQuery> {
        int k, i, j, indices;

        public SumQuery(int k, int i, int j, int indices) {
            this.k = k;
            this.i = i;
            this.j = j;
            this.indices = indices;
        }

        @Override
        public int compareTo(SumQuery o2) {
            return this.k - o2.k;
        }

        @Override
        public String toString() {
            return "SumQuery{" +
                    "k=" + k +
                    ", i=" + i +
                    ", j=" + j +
                    ", indices=" + indices +
                    '}';
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        A = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        S = 1;
        while (S < N) {
            S <<= 1;
        }
        tree = new long[S * 2];

        M = Integer.parseInt(br.readLine());

        int c;
        for (int q = 0; q < M; ++q) {
            st = new StringTokenizer(br.readLine());
            c = Integer.parseInt(st.nextToken());
            if (c == 1) {
                updateQueries.add(new UpdateQuery(
                        Integer.parseInt(st.nextToken()) - 1,
                        Integer.parseInt(st.nextToken())
                ));
            } else {
                sumQueries.add(new SumQuery(
                        Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()) - 1,
                        Integer.parseInt(st.nextToken()) - 1,
                        sumQueries.size()
                ));
            }
        }

        Collections.sort(sumQueries);

        treeInitialize();

        long[] answer = new long[sumQueries.size()];

        int updateCount = 0;
        for (SumQuery sq: sumQueries) {
            while (sq.k > updateCount) {
                UpdateQuery uq = updateQueries.get(updateCount++);
                update(1, 0, S - 1, uq.i, uq.v);
            }
            answer[sq.indices] = query(1, 0, S - 1, sq.i, sq.j);
        }

        for (int i = 0; i < answer.length; ++i) {
            bw.write(answer[i] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private void treeInitialize() {
        for (int i = 0; i < N; ++i) {
            tree[S + i] = A[i];
        }

        for (int i = S - 1; i > 0; --i) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
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
                query(node * 2 + 1, mid + 1, right,queryLeft, queryRight);
    }

    private void update(int node, int left, int right, int target, long value) {
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

    public static void main(String[] args) throws Exception {
        new SequenceAndQuery22().solution();
    }
}
