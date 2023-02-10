// https://www.acmicpc.net/problem/5626

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class Altar {

    private static final int P = 1000000007;
    private int N, maxHeight;
    private int[] H;
    private int[][] dp;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        H = new int[N];

        maxHeight = (N - 1) / 2;
        dp = new int[N][maxHeight + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            H[i] = Integer.parseInt(st.nextToken());
        }

        // 처음 높이가 0 or -1이여야만 함 ***********
        if (H[0] <= 0) {
            dp[0][0] = 1;
            for (int i = 1; i < N; ++i) {
                int hMax = Math.min(Math.min(i, N - i - 1), maxHeight);
                if (H[i] == -1) {
                    for (int h = 0; h <= hMax; ++h) {
                        for (int k = -1; k <= 1; ++k) {
                            if (0 <= h + k && h + k <= maxHeight) {
                                dp[i][h] = (dp[i][h] + dp[i - 1][h + k]) % P;
                            }
                        }
                    }
                } else {
                    if (H[i] > maxHeight) { // 불가능한 경우
                        break;
                    }
                    for (int k = -1; k <= 1; ++k) {
                        if (0 <= H[i] + k && H[i] + k <= maxHeight) {
                            dp[i][H[i]] = (dp[i][H[i]] + dp[i - 1][H[i] + k]) % P;
                        }
                    }
                }
            }
        }
        bw.write(dp[N - 1][0] + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new Altar().solution();
    }
}
