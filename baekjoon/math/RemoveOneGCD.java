// https://www.acmicpc.net/problem/14476

package baekjoon.math;

import java.io.*;
import java.util.*;

public class RemoveOneGCD {

    private static int N;
    private static int[] nums, LtoR, RtoL;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        nums = new int[N + 2];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; ++i) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        LtoR = new int[N + 2];
        for (int i = 1; i <= N; ++i) {
            LtoR[i] = gcd(LtoR[i - 1], nums[i]);
        }

        RtoL = new int[N + 2];
        for (int i = N; i > 0; --i) {
            RtoL[i] = gcd(RtoL[i + 1], nums[i]);
        }

        boolean find = false;
        int maxGcd = 0;
        int removed = 0;
        for (int i = 1; i <= N; ++i) {
            int gcd = gcd(LtoR[i - 1], RtoL[i + 1]);
            if (gcd > maxGcd && nums[i] % gcd != 0) {
                find = true;
                maxGcd = gcd;
                removed = nums[i];
            }
        }

        if (find) {
            System.out.println(maxGcd + " " + removed);
        } else {
            System.out.println(-1);
        }
        br.close();
    }

    private static int gcd(int a, int b) {
        int r;
        while (b != 0) {
            r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
