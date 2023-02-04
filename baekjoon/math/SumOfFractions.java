// https://www.acmicpc.net/problem/1735

package baekjoon.math;

import java.io.*;
import java.util.*;

public class SumOfFractions {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int A, B, C, D;

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        A *= D;
        C *= B;

        B *= D;
        D *= B;

        int top = A + C;
        int bottom = B;
        int gcd = gcd(top, bottom);
        System.out.println(top / gcd + " " + bottom / gcd);
        br.close();
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
