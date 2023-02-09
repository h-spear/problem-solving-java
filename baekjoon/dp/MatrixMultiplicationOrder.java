// https://www.acmicpc.net/problem/11049

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class MatrixMultiplicationOrder {

    static class Size {
        int r, c;

        public Size(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static int N;
    private static int[][] table;
    private static Size[] matrix;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        matrix = new Size[N + 1];

        for (int i = 1; i <= N; ++i) {
            st = new StringTokenizer(br.readLine());
            matrix[i] = new Size(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        table = new int[N + 1][N + 1];
        bw.write("" + dp(1, N));
        bw.flush();
        bw.close();
        br.close();
    }

    private static int dp(int a, int b) {
        if (a >= b) {
            return 0;
        }
        if (table[a][b] != 0) {
            return table[a][b];
        }
        table[a][b] = Integer.MAX_VALUE;
        for (int k = a; k < b; ++k) {
            table[a][b] = Math.min(table[a][b],
                    (dp(a, k) + matrix[a].r * matrix[k].c * matrix[b].c) + dp(k + 1, b));
        }
        return table[a][b];
    }
}
