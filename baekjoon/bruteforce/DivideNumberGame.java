// https://www.acmicpc.net/problem/27172

package baekjoon.bruteforce;

import java.util.*;
import java.io.*;

public class DivideNumberGame {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] x = new int[N];
        boolean[] exist = new boolean[1000001];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            x[i] = Integer.parseInt(st.nextToken());
            exist[x[i]] = true;
        }

        int[] score = new int[1000001];
        for (int num: x) {
            int i = 1;
            while (num * i < 1000001) {
                if (exist[num * i]) {
                    --score[num * i];
                    ++score[num];
                }
                ++i;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int num: x) {
            sb.append(score[num]).append(" ");
        }
        System.out.println(sb.toString());
        br.close();
    }
}