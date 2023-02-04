// https://www.acmicpc.net/problem/2960

package baekjoon.math;

import java.io.*;
import java.util.*;

public class SieveOfEratosthenes {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N, K;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        boolean find = false;
        boolean[] checked = new boolean[N + 1];

        int answer = 0, count = 0;
        for (int i = 2; i <= N; ++i) {
            if (checked[i] == true) {
                continue;
            }
            for (int j = i; j <= N; j += i) {
                if (checked[j] == false) {
                    checked[j] = true;
                    count++;
                }
                if (count == K) {
                    find = true;
                    answer = j;
                    break;
                }
            }
            if (find)
                break;
        }

        System.out.println(answer);
        br.close();
    }
}
