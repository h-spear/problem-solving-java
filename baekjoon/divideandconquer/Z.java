// https://www.acmicpc.net/problem/1074

package baekjoon.divideandconquer;

import java.io.*;
import java.util.*;

public class Z {

    private static int N, r, c;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        System.out.println(func(0, 0, 1 << N, 0));
        br.close();
    }

    private static int func(int x, int y, int l, int count) {
        if (l == 1) {
            return count;
        }

        int l2 = l >> 1;
        if (r < x + l2) {
            if (c < y + l2) {
                return func(x, y, l2, count);
            } else {
                return func(x, y + l2, l2, count + l2 * l2);
            }
        } else {
            if (c < y + l2) {
                return func(x + l2, y, l2, count + 2 * (l2 * l2));
            } else {
                return func(x + l2, y + l2, l2, count + 3 * (l2 * l2));
            }
        }
    }
}