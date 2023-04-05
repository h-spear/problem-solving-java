// https://www.acmicpc.net/problem/10819

package baekjoon.backtracking;

import java.io.*;
import java.util.*;

public class DifferenceToMaximum {

    private static int N, visited, fullVisit;
    private static int[] arr = new int[10];
    private static int[] ordered = new int[10];
    private static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        fullVisit = (1 << N) - 1;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dfs(0);
        bw.write("" + answer);

        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int depth) {
        if (visited == fullVisit) {
            answer = Math.max(answer, calc());
        }

        for (int i = 0; i < N; ++i) {
            if ((visited & (1 << i)) != 0) {
                continue;
            }
            ordered[depth] = arr[i];
            visited |= (1 << i);
            dfs(depth + 1);
            visited &= ~(1 << i);
        }
    }

    private static int calc() {
        int res = 0;
        for (int i = 0; i < N - 1; ++i) {
            res += Math.abs(ordered[i] - ordered[i + 1]);
        }
        return res;
    }
}
