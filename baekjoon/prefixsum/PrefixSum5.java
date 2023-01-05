// https://www.acmicpc.net/problem/11660

package baekjoon.prefixsum;

import java.io.*;
import java.util.*;

public class PrefixSum5 {

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n][n];

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; ++j) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] prefixSum = new int[n+1][n+1];
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                prefixSum[i][j] = prefixSum[i][j - 1] + arr[i - 1][j - 1];
            }
        }
        for (int j = 1; j <= n; ++j) {
            for (int i = 1; i <= n; ++i) {
                prefixSum[i][j] += prefixSum[i - 1][j];
            }
        }

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            bw.write("" + (prefixSum[x2][y2] - prefixSum[x2][y1-1] - prefixSum[x1-1][y2] + prefixSum[x1-1][y1-1]));
            bw.newLine();
            bw.flush();
        }

        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new PrefixSum5().solution();
    }
}
