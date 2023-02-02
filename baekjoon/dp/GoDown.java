package baekjoon.dp;// https://www.acmicpc.net/problem/2096

import java.io.*;
import java.util.*;

public class GoDown {

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][3];
        int[][] minDp = new int[N][3];
        int[][] maxDp = new int[N][3];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
                maxDp[i][j] = Integer.MIN_VALUE;
                minDp[i][j] = Integer.MAX_VALUE;
            }
        }

        // 첫 번째 줄
        for (int j = 0; j < 3; ++j) {
            minDp[0][j] = map[0][j];
            maxDp[0][j] = map[0][j];
        }

        for (int i = 1; i < N; ++i) {
            for (int j = 0; j < 3; ++j) {
                for (int k = -1; k <= 1; ++k) {
                    if (j + k < 0 || j+k >= 3) {
                        continue;
                    }
                    minDp[i][j] = Math.min(minDp[i][j], minDp[i-1][j+k] + map[i][j]);
                    maxDp[i][j] = Math.max(maxDp[i][j], maxDp[i-1][j+k] + map[i][j]);
                }
            }
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < 3; ++j) {
            max = Math.max(max, maxDp[N-1][j]);
            min = Math.min(min, minDp[N-1][j]);
        }

        System.out.println(max + " " + min);
        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new GoDown().solution();
    }
}
