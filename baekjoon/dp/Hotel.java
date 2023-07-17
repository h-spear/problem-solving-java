// https://www.acmicpc.net/problem/1106

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class Hotel {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] cost = new int[N + 1];
        int[] gain = new int[N + 1];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            cost[i] = Integer.parseInt(st.nextToken());
            gain[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[100001];
        for (int i = 0; i <= 100000; ++i) {
            for (int j = 0; j < N; ++j) {
                if (i >= cost[j])
                    dp[i] = Math.max(dp[i], gain[j] + dp[i - cost[j]]);
            }
            if (dp[i] >= C) {
                bw.write("" + i);
                break;
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
