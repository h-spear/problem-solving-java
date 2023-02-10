// https://www.acmicpc.net/problem/10714

package baekjoon.dp;

import java.io.*;

public class CakeCutting2 {

    private int N;
    private int[] A;
    private long[][] table;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        table = new long[N * 2][N * 2];
        A = new int[N * 2];
        for (int i = 0; i < N; ++i) {
            A[i] = Integer.parseInt(br.readLine());
        }
        for (int i = N; i < N * 2; ++i) {
            A[i] = A[i - N];
        }

        long answer = 0;

        if (N == 1) {
            answer = A[0];
        } else {
            for (int i = 0; i < N; ++i) {
                answer = Math.max(answer, A[i] + dp(i + 1, i + N - 1, 1));
            }
        }

        bw.write(answer + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    // turn 0: JOI, 1: IOI
    private long dp(int a, int b, int turn) {
        if (table[a][b] != 0) {
            return table[a][b];
        }

        if (turn == 0) {
            // JOI는 양쪽 중 최대가 되도록 함
            if (a == b) {
                return A[a];
            }
            table[a][b] = Math.max(
                    A[a] + dp(a + 1, b, 1 - turn),
                    A[b] + dp(a, b - 1, 1 - turn)
            );
        } else {
            if (a == b) {
                return 0;
            }
            if (A[a] > A[b]) {
                table[a][b] = dp(a + 1, b, 1 - turn);
            } else {
                table[a][b] = dp(a, b - 1, 1 - turn);
            }
        }
        return table[a][b];
    }

    public static void main(String[] args) throws Exception {
        new CakeCutting2().solution();
    }
}
