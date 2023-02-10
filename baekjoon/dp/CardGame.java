// https://www.acmicpc.net/problem/11062

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class CardGame {

    private int N;
    private int[] A;
    private int[][] table;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            A = new int[N];
            table = new int[N][N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; ++i) {
                A[i] = Integer.parseInt(st.nextToken());
                Arrays.fill(table[i], -1);
            }

            bw.write("" + dp(0, N - 1, 0));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }


    // turn 0: 근우 1: 명우
    private int dp(int a, int b, int turn) {
        if (table[a][b] != -1) {
            return table[a][b];
        }

        if (turn == 1) {
            if (a == b) {
                return 0;
            }
            table[a][b] = Math.min(
                    dp(a + 1, b, 1 - turn),
                    dp(a, b - 1, 1 - turn)
            );
        } else {
            if (a == b) {
                return A[a];
            }
            table[a][b] = Math.max(
                    dp(a + 1, b, 1 - turn) + A[a],
                    dp(a, b - 1, 1 - turn) + A[b]
            );
        }
        return table[a][b];
    }

    public static void main(String[] args) throws Exception {
        new CardGame().solution();
    }
}
