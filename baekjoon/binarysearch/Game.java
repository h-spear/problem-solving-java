// https://www.acmicpc.net/problem/1072

package baekjoon.binarysearch;

import java.io.*;
import java.util.*;

public class Game {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        long X = Long.parseLong(st.nextToken());
        long Y = Long.parseLong(st.nextToken());
        long target = simul(X, Y) + 1;
        long left = 0;
        long right = (long) 1e9;
        long answer = Integer.MAX_VALUE;

        while (left <= right) {
            long mid = (left + right) / 2;

            if (simul(X + mid, Y + mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
                answer = Math.min(answer, mid);
            }
        }

        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
        br.close();
    }

    private static long simul(long X, long Y) {
        return (Y * 100 / X) + 1;
    }
}
