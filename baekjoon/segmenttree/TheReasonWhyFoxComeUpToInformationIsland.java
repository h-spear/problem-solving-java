// https://www.acmicpc.net/problem/17131

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class TheReasonWhyFoxComeUpToInformationIsland {

    class Coordinate implements Comparable<Coordinate> {
        int x, y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        // y좌표 내림차순, x좌표 내림차순
        public int compareTo(Coordinate o2) {
            return o2.y - this.y;
        }

        @Override
        public String toString() {
            return "Coordinate{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    private static final long MOD = 1000000007;
    private static final int K = 400001;
    private static final int S = 524288;
    private static int[] tree = new int[S * 2];
    private static Coordinate[] coordinates;



    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;


        int N = Integer.parseInt(br.readLine());
        int x, y;

        coordinates = new Coordinate[N];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken()) + 200000;
            y = Integer.parseInt(st.nextToken()) + 200000;
            coordinates[i] = new Coordinate(x, y);
        }

        Arrays.sort(coordinates);

        int cIdx = 0;
        long s, u;
        long answer = 0;
        while (cIdx < N) {
            Coordinate coordinate;
            int tIdx = cIdx;
            int prevY = -1;
            
            // y좌표가 같은 별자리를 한번에 계산
            while (tIdx < N) {
                if (prevY != -1 && prevY != coordinates[tIdx].y) {
                    break;
                }
                coordinate = coordinates[tIdx];
                s = query(1, 0, S - 1, 0, coordinate.x - 1) % MOD;
                u = query(1, 0, S - 1, coordinate.x + 1, K) % MOD;
                answer += (s * u) % MOD;
                answer %= MOD;

                prevY = coordinate.y;
                tIdx++;
            }

            // 업데이트는 계산 후에 진행
            for (; cIdx < tIdx; ++cIdx) {
                update(1, 0, S - 1, coordinates[cIdx].x, 1);
            }
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private void update(int node, int left, int right, int target, int diff) {
        if (target < left || right < target) {
            return;
        }
        tree[node] += diff;
        if (left != right) {
            int mid = (left + right) >> 1;
            update(node * 2, left, mid, target, diff);
            update(node * 2 + 1, mid + 1, right, target, diff);
        }
    }

    private long query(int node, int left, int right, int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft) {
            return 0;
        }
        if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }
        int mid = (left + right) >> 1;
        return query(node * 2, left, mid, queryLeft, queryRight) +
                query(node * 2 + 1, mid + 1, right, queryLeft, queryRight);
    }

    public static void main(String[] args) throws Exception {
        new TheReasonWhyFoxComeUpToInformationIsland().solution();
    }
}
