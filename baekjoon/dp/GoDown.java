// https://www.acmicpc.net/problem/2096

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class GoDown {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][3];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; ++j) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] maxTemp = new int[3];
        int[] minTemp = new int[3];
        int[] maxDp = Arrays.copyOfRange(arr[0], 0, 3);
        int[] minDp = Arrays.copyOfRange(arr[0], 0, 3);
        int[] temp;
        for (int i = 1; i < N; ++i) {
            maxTemp[0] = max(maxDp[0], maxDp[1]) + arr[i][0];
            maxTemp[1] = max(maxDp[0], maxDp[1], maxDp[2]) + arr[i][1];
            maxTemp[2] = max(maxDp[1], maxDp[2]) + arr[i][2];
            minTemp[0] = min(minDp[0], minDp[1]) + arr[i][0];
            minTemp[1] = min(minDp[0], minDp[1], minDp[2]) + arr[i][1];
            minTemp[2] = min(minDp[1], minDp[2]) + arr[i][2];

            temp = maxDp;
            maxDp = maxTemp;
            maxTemp = temp;
            temp = minDp;
            minDp = minTemp;
            minTemp = temp;
        }
        System.out.println(max(maxDp) + " " + min(minDp));
        br.close();
    }

    private static int max(int... nums) {
        int res = Integer.MIN_VALUE;
        for (int num: nums) {
            res = Math.max(res, num);
        }
        return res;
    }

    private static int min(int... nums) {
        int res = Integer.MAX_VALUE;
        for (int num: nums) {
            res = Math.min(res, num);
        }
        return res;
    }
}
